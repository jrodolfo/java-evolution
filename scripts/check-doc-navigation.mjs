import fs from "node:fs";
import path from "node:path";

const versions = Array.from({ length: 26 }, (_, index) => `java${String(index + 1).padStart(2, "0")}`);
const root = "src/main/java/net/jrodolfo/java_evolution";

function markdownFilesIn(directory) {
  const files = [];

  for (const entry of fs.readdirSync(directory, { withFileTypes: true })) {
    const currentPath = path.join(directory, entry.name);

    if (entry.isDirectory()) {
      files.push(...markdownFilesIn(currentPath));
    } else if (entry.name.endsWith(".md")) {
      files.push(currentPath);
    }
  }

  return files;
}

const markdownFiles = [
  "README.md",
  ...markdownFilesIn("docs"),
  ...versions.map((version) => path.join(root, version, "README.md")),
];

const errors = [];
const testClasses = new Set();

function walk(directory) {
  for (const entry of fs.readdirSync(directory, { withFileTypes: true })) {
    const currentPath = path.join(directory, entry.name);

    if (entry.isDirectory()) {
      walk(currentPath);
    } else if (entry.name.endsWith("Test.java")) {
      testClasses.add(path.basename(entry.name, ".java"));
    }
  }
}

walk("src/test/java");

function testClassesFromText(text) {
  const classes = new Set();

  for (const match of text.matchAll(/["']?-Dtest=([^"'\s]+)["']?\s+test/g)) {
    for (const testClass of match[1].split(",")) {
      classes.add(testClass);
    }
  }

  return classes;
}

function makeDemosTargetText() {
  const makefile = fs.readFileSync("Makefile", "utf8");
  const lines = makefile.split("\n");
  const start = lines.findIndex((line) => line === "demos: check-java-25");

  if (start === -1) {
    errors.push("Makefile is missing demos target");
    return "";
  }

  const targetLines = [];
  for (const line of lines.slice(start + 1)) {
    if (/^[A-Za-z0-9_-]+:/.test(line)) {
      break;
    }
    targetLines.push(line);
  }

  return targetLines.join("\n");
}

for (const version of versions) {
  const readme = path.join(root, version, "README.md");
  const packageInfo = path.join(root, version, "package-info.java");

  if (!fs.existsSync(readme)) {
    errors.push(`missing version README: ${readme}`);
  }

  if (!fs.existsSync(packageInfo)) {
    errors.push(`missing package-info.java: ${packageInfo}`);
  }
}

for (const file of markdownFiles) {
  if (!fs.existsSync(file)) {
    errors.push(`missing markdown file listed for audit: ${file}`);
    continue;
  }

  const text = fs.readFileSync(file, "utf8");

  for (const staleName of ["Java23NotesTest", "Java24NotesTest", "Java25NotesTest"]) {
    if (text.includes(staleName)) {
      errors.push(`${file} references removed grouped test ${staleName}`);
    }
  }

  if (/\*NotesTest/.test(text)) {
    errors.push(`${file} contains wildcard notes test reference; prefer concrete test class names`);
  }

  for (const testClass of testClassesFromText(text)) {
    if (!testClasses.has(testClass)) {
      errors.push(`${file} references missing test class ${testClass}`);
    }
  }
}

const makeDemoTests = testClassesFromText(makeDemosTargetText());
const practicalDemoTests = testClassesFromText(fs.readFileSync("docs/practical-demos.md", "utf8"));

for (const testClass of makeDemoTests) {
  if (!practicalDemoTests.has(testClass)) {
    errors.push(`docs/practical-demos.md is missing make demos test class ${testClass}`);
  }
}

for (const testClass of practicalDemoTests) {
  if (!makeDemoTests.has(testClass)) {
    errors.push(`docs/practical-demos.md lists ${testClass}, but make demos does not run it`);
  }
}

if (errors.length > 0) {
  console.error("documentation navigation audit failed:");
  for (const error of errors) {
    console.error(`- ${error}`);
  }
  process.exit(1);
}

console.log("documentation navigation audit passed");
