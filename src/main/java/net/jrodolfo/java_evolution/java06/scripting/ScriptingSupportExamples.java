package net.jrodolfo.java_evolution.java06.scripting;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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

	public CommandResult runServiceProviderProbe(Path workspace) throws IOException, InterruptedException {
		Path sourceDirectory = workspace.resolve("src");
		Path factorySource = sourceDirectory.resolve("demo").resolve("EchoScriptEngineFactory.java");
		Path engineSource = sourceDirectory.resolve("demo").resolve("EchoScriptEngine.java");
		Path probeSource = sourceDirectory.resolve("Probe.java");
		Path classesDirectory = workspace.resolve("classes");
		Path serviceFile = classesDirectory.resolve(serviceProviderFileName());

		Files.createDirectories(factorySource.getParent());
		Files.createDirectories(serviceFile.getParent());
		Files.write(factorySource, engineFactorySource().getBytes(StandardCharsets.UTF_8));
		Files.write(engineSource, engineSource().getBytes(StandardCharsets.UTF_8));
		Files.write(probeSource, probeSource().getBytes(StandardCharsets.UTF_8));
		Files.write(serviceFile, "demo.EchoScriptEngineFactory\n".getBytes(StandardCharsets.UTF_8));

		CommandResult compilation = run(javacCommand(), "-d", classesDirectory.toString(), factorySource.toString(),
				engineSource.toString(), probeSource.toString());
		if (compilation.exitCode() != 0) {
			return compilation;
		}

		return run(javaCommand(), "-cp", classesDirectory.toString(), "Probe");
	}

	public String serviceProviderFileName() {
		return "META-INF/services/javax.script.ScriptEngineFactory";
	}

	public String engineFactorySource() {
		return """
				package demo;

				import java.util.Collections;
				import java.util.List;
				import javax.script.ScriptEngine;
				import javax.script.ScriptEngineFactory;

				public class EchoScriptEngineFactory implements ScriptEngineFactory {
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
				                program.append("\\n");
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
				""";
	}

	public String engineSource() {
		return """
				package demo;

				import java.io.IOException;
				import java.io.Reader;
				import javax.script.AbstractScriptEngine;
				import javax.script.Bindings;
				import javax.script.ScriptContext;
				import javax.script.ScriptEngineFactory;
				import javax.script.ScriptException;
				import javax.script.SimpleBindings;

				public class EchoScriptEngine extends AbstractScriptEngine {
				    private final ScriptEngineFactory factory;

				    EchoScriptEngine(ScriptEngineFactory factory) {
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
				""";
	}

	public String probeSource() {
		return """
				import javax.script.ScriptEngine;
				import javax.script.ScriptEngineManager;

				public class Probe {
				    public static void main(String[] args) throws Exception {
				        ScriptEngineManager manager = new ScriptEngineManager();
				        System.out.println("factories=" + manager.getEngineFactories().size());
				        ScriptEngine engine = manager.getEngineByName("echo");
				        System.out.println("engine=" + engine.getFactory().getEngineName());
				        engine.put("name", "Java 6");
				        System.out.println("result=" + engine.eval("hello ${name}"));
				        System.out.println("javascript=" + manager.getEngineByName("javascript"));
				    }
				}
				""";
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
		Process process = processBuilder.start();
		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));

		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			return new CommandResult(-1, output.join());
		}

		return new CommandResult(process.exitValue(), output.join());
	}

	private String readOutput(Process process) {
		try {
			return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			throw new IllegalStateException("could not read child process output", exception);
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
