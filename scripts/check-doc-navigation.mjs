import fs from "node:fs";
import path from "node:path";

const versions = Array.from({ length: 18 }, (_, index) => `java${String(index + 8).padStart(2, "0")}`);
const root = "src/main/java/net/jrodolfo/java_evolution";
const markdownFiles = [
  "README.md",
  ...fs.readdirSync("docs")
    .filter((file) => file.endsWith(".md"))
    .map((file) => path.join("docs", file)),
  ...versions.map((version) => path.join(root, version, "README.md")),
];

const errors = [];

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
}

if (errors.length > 0) {
  console.error("documentation navigation audit failed:");
  for (const error of errors) {
    console.error(`- ${error}`);
  }
  process.exit(1);
}

console.log("documentation navigation audit passed");
