package net.jrodolfo.java_evolution.java06.scripting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * Demonstrates Java 6 scripting support through JSR 223.
 */
public class ScriptingSupportExamples {

	public String problemSolved() {
		return "Java applications needed a standard way to host scripting languages without binding to one engine-specific API";
	}

	public String apiPackage() {
		return "javax.script provides ScriptEngineManager, ScriptEngine, ScriptEngineFactory, and bindings for hosted scripts";
	}

	public boolean javascriptEngineAvailable() {
		return new ScriptEngineManager().getEngineByName("javascript") != null;
	}

	public String javascriptEngineLookupResult() {
		if (javascriptEngineAvailable()) {
			return "ScriptEngineManager found a JavaScript engine supplied by this runtime or its dependencies";
		}
		return "ScriptEngineManager returned null for JavaScript because JDK 26 does not guarantee a bundled engine";
	}

	public String modernPractice() {
		return "treat a script engine as an explicit dependency and check whether ScriptEngineManager can find it";
	}

	public Object evaluateWithCustomEngine(String script, String name) throws ScriptException {
		ScriptEngine engine = new EchoScriptEngineFactory().getScriptEngine();
		engine.put("name", name);
		return engine.eval(script);
	}

	public CommandResult runServiceProviderProbe(File workspace) throws IOException, InterruptedException {
		File sourceDirectory = new File(workspace, "src");
		File factorySource = new File(new File(sourceDirectory, "demo"), "EchoScriptEngineFactory.java");
		File engineSource = new File(new File(sourceDirectory, "demo"), "EchoScriptEngine.java");
		File probeSource = new File(sourceDirectory, "Probe.java");
		File classesDirectory = new File(workspace, "classes");
		File serviceFile = new File(classesDirectory, serviceProviderFileName());

		factorySource.getParentFile().mkdirs();
		serviceFile.getParentFile().mkdirs();
		write(factorySource, engineFactorySource());
		write(engineSource, engineSource());
		write(probeSource, probeSource());
		write(serviceFile, "demo.EchoScriptEngineFactory\n");

		CommandResult compilation = run(javacCommand(), "-d", classesDirectory.getPath(), factorySource.getPath(),
				engineSource.getPath(), probeSource.getPath());
		if (compilation.exitCode() != 0) {
			return compilation;
		}

		return run(javaCommand(), "-cp", classesDirectory.getPath(), "Probe");
	}

	private void write(File file, String content) throws IOException {
		FileOutputStream output = new FileOutputStream(file);
		try {
			output.write(content.getBytes());
		}
		finally {
			output.close();
		}
	}

	public String serviceProviderFileName() {
		return "META-INF/services/javax.script.ScriptEngineFactory";
	}

	public String engineFactorySource() {
		return source(new String[] {
				"package demo;", "", "import java.util.Collections;", "import java.util.List;",
				"import javax.script.ScriptEngine;", "import javax.script.ScriptEngineFactory;", "",
				"public class EchoScriptEngineFactory implements ScriptEngineFactory {",
				"    @Override", "    public String getEngineName() {", "        return \"Echo Script Engine\";", "    }", "",
				"    @Override", "    public String getEngineVersion() {", "        return \"1.0\";", "    }", "",
				"    @Override", "    public List<String> getExtensions() {", "        return Collections.singletonList(\"echo\");", "    }", "",
				"    @Override", "    public List<String> getMimeTypes() {", "        return Collections.singletonList(\"text/x-echo\");", "    }", "",
				"    @Override", "    public List<String> getNames() {", "        return Collections.singletonList(\"echo\");", "    }", "",
				"    @Override", "    public String getLanguageName() {", "        return \"Echo\";", "    }", "",
				"    @Override", "    public String getLanguageVersion() {", "        return \"1.0\";", "    }", "",
				"    @Override", "    public Object getParameter(String key) {", "        return null;", "    }", "",
				"    @Override", "    public String getMethodCallSyntax(String object, String method, String... args) {",
				"        StringBuilder call = new StringBuilder(object).append(\".\").append(method).append(\"(\");",
				"        for (int index = 0; index < args.length; index++) {", "            if (index > 0) {",
				"                call.append(\", \");", "            }", "            call.append(args[index]);", "        }",
				"        return call.append(\")\").toString();", "    }", "",
				"    @Override", "    public String getOutputStatement(String toDisplay) {", "        return \"echo \" + toDisplay;", "    }", "",
				"    @Override", "    public String getProgram(String... statements) {", "        StringBuilder program = new StringBuilder();",
				"        for (String statement : statements) {", "            if (program.length() > 0) {", "                program.append(\"\\\\n\");", "            }",
				"            program.append(statement);", "        }", "        return program.toString();", "    }", "",
				"    @Override", "    public ScriptEngine getScriptEngine() {", "        return new EchoScriptEngine(this);", "    }", "}"
		});
	}

	public String engineSource() {
		return source(new String[] {
				"package demo;", "", "import java.io.IOException;", "import java.io.Reader;",
				"import javax.script.AbstractScriptEngine;", "import javax.script.Bindings;", "import javax.script.ScriptContext;",
				"import javax.script.ScriptEngineFactory;", "import javax.script.ScriptException;", "import javax.script.SimpleBindings;", "",
				"public class EchoScriptEngine extends AbstractScriptEngine {", "    private final ScriptEngineFactory factory;", "",
				"    EchoScriptEngine(ScriptEngineFactory factory) {", "        this.factory = factory;", "    }", "",
				"    @Override", "    public Object eval(String script, ScriptContext context) {",
				"        Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);", "        Object name = bindings.get(\"name\");",
				"        return script.replace(\"${name}\", String.valueOf(name));", "    }", "",
				"    @Override", "    public Object eval(Reader reader, ScriptContext context) throws ScriptException {", "        try {",
				"            StringBuilder script = new StringBuilder();", "            char[] buffer = new char[256];", "            int count;",
				"            while ((count = reader.read(buffer)) != -1) {", "                script.append(buffer, 0, count);", "            }",
				"            return eval(script.toString(), context);", "        } catch (IOException exception) {", "            throw new ScriptException(exception);", "        }", "    }", "",
				"    @Override", "    public Bindings createBindings() {", "        return new SimpleBindings();", "    }", "",
				"    @Override", "    public ScriptEngineFactory getFactory() {", "        return factory;", "    }", "}"
		});
	}

	public String probeSource() {
		return source(new String[] {
				"import javax.script.ScriptEngine;", "import javax.script.ScriptEngineManager;", "",
				"public class Probe {", "    public static void main(String[] args) throws Exception {",
				"        ScriptEngineManager manager = new ScriptEngineManager();",
				"        System.out.println(\"factories=\" + manager.getEngineFactories().size());",
				"        ScriptEngine engine = manager.getEngineByName(\"echo\");",
				"        System.out.println(\"engine=\" + engine.getFactory().getEngineName());",
				"        engine.put(\"name\", \"Java 6\");",
				"        System.out.println(\"result=\" + engine.eval(\"hello ${name}\"));",
				"        System.out.println(\"javascript=\" + manager.getEngineByName(\"javascript\"));",
				"    }", "}"
		});
	}

	private String source(String[] lines) {
		StringBuilder source = new StringBuilder();
		for (int index = 0; index < lines.length; index++) {
			source.append(lines[index]).append('\n');
		}
		return source.toString();
	}

	private String javacCommand() {
		String executable = isWindows() ? "javac.exe" : "javac";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private String javaCommand() {
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}

	private CommandResult run(String... command) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		final Process process = processBuilder.start();
		final StringBuffer output = new StringBuffer();
		Thread reader = new Thread(new Runnable() {
			public void run() {
				readOutput(process.getInputStream(), output);
			}
		});
		reader.start();
		int exitCode = process.waitFor();
		reader.join();
		return new CommandResult(exitCode, output.toString());
	}

	private void readOutput(InputStream input, StringBuffer output) {
		try {
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			int read;
			while ((read = input.read(buffer)) != -1) {
				bytes.write(buffer, 0, read);
			}
			output.append(new String(bytes.toByteArray()));
		}
		catch (IOException exception) {
			output.append("could not read child process output: ").append(exception);
		}
	}

	static final class EchoScriptEngineFactory implements ScriptEngineFactory {

		@Override
		public String getEngineName() {
			return "Echo Script Engine";
		}

		@Override
		public String getEngineVersion() {
			return "1.0";
		}

		@Override
		public List<String> getExtensions() {
			return Collections.singletonList("echo");
		}

		@Override
		public List<String> getMimeTypes() {
			return Collections.singletonList("text/x-echo");
		}

		@Override
		public List<String> getNames() {
			return Collections.singletonList("echo");
		}

		@Override
		public String getLanguageName() {
			return "Echo";
		}

		@Override
		public String getLanguageVersion() {
			return "1.0";
		}

		@Override
		public Object getParameter(String key) {
			return null;
		}

		@Override
		public String getMethodCallSyntax(String object, String method, String... args) {
			StringBuilder call = new StringBuilder(object).append(".").append(method).append("(");
			for (int index = 0; index < args.length; index++) {
				if (index > 0) {
					call.append(", ");
				}
				call.append(args[index]);
			}
			return call.append(")").toString();
		}

		@Override
		public String getOutputStatement(String toDisplay) {
			return "echo " + toDisplay;
		}

		@Override
		public String getProgram(String... statements) {
			StringBuilder program = new StringBuilder();
			for (String statement : statements) {
				if (program.length() > 0) {
					program.append("\n");
				}
				program.append(statement);
			}
			return program.toString();
		}

		@Override
		public ScriptEngine getScriptEngine() {
			return new EchoScriptEngine(this);
		}
	}

	static final class EchoScriptEngine extends AbstractScriptEngine {

		private final ScriptEngineFactory factory;

		private EchoScriptEngine(ScriptEngineFactory factory) {
			this.factory = factory;
		}

		@Override
		public Object eval(String script, ScriptContext context) {
			Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
			Object name = bindings.get("name");
			return script.replace("${name}", String.valueOf(name));
		}

		@Override
		public Object eval(Reader reader, ScriptContext context) throws ScriptException {
			try {
				StringBuilder script = new StringBuilder();
				char[] buffer = new char[256];
				int count;
				while ((count = reader.read(buffer)) != -1) {
					script.append(buffer, 0, count);
				}
				return eval(script.toString(), context);
			} catch (IOException exception) {
				throw new ScriptException(exception);
			}
		}

		@Override
		public Bindings createBindings() {
			return new SimpleBindings();
		}

		@Override
		public ScriptEngineFactory getFactory() {
			return factory;
		}
	}

	public static final class CommandResult {

		private final int exitCode;
		private final String output;

		private CommandResult(int exitCode, String output) {
			this.exitCode = exitCode;
			this.output = output;
		}

		public int exitCode() {
			return exitCode;
		}

		public String output() {
			return output;
		}
	}
}
