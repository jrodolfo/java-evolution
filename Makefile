JAVA_25_HOME := $(shell /usr/libexec/java_home -v 25 2>/dev/null)
JAVA_CMD := java

ifneq ($(JAVA_25_HOME),)
export JAVA_HOME := $(JAVA_25_HOME)
export PATH := $(JAVA_HOME)/bin:$(PATH)
JAVA_CMD := $(JAVA_HOME)/bin/java
endif

.PHONY: help check-java-25 java-version test clean-test demos run docs clean-docs docs-audit links docs-check check release-check

help:
	@echo "available targets:"
	@echo "  make check-java-25 verify the active Java and Maven runtimes use Java 25"
	@echo "  make java-version  show the Java and Maven versions"
	@echo "  make test          run the test suite"
	@echo "  make clean-test    clean the build and run the test suite"
	@echo "  make demos         run the focused practical demo tests"
	@echo "  make run           run the Spring Boot application"
	@echo "  make docs          generate JavaDoc under target/site/apidocs"
	@echo "  make clean-docs    remove generated JavaDoc"
	@echo "  make docs-audit    check documentation navigation consistency"
	@echo "  make links         check Markdown links with lychee"
	@echo "  make docs-check    run documentation audit, JavaDoc generation, and link check"
	@echo "  make check         show versions and run the test suite"
	@echo "  make release-check run documentation, full test, and practical demo gates"

check-java-25:
	@java_version="$$(java --version 2>&1 | sed -n '1p')" || exit 1; \
	case "$$java_version" in \
	  "java 25"*|"openjdk 25"*) ;; \
	  *) \
	    echo "java 25 is required, but the active java is not java 25."; \
	    echo; \
	    echo "active java:"; \
	    echo "  $$java_version"; \
	    echo; \
	    echo "run one of:"; \
	    echo "  source scripts/use-java-25-mac.sh"; \
	    echo "  source scripts/use-java-25-windows.sh"; \
	    echo "  . .\\scripts\\use-java-25-windows.ps1"; \
	    echo; \
	    echo "then verify:"; \
	    echo "  java --version"; \
	    echo "  mvn --version"; \
	    exit 1; \
	esac; \
	maven_java_version="$$(mvn --version 2>&1 | sed -n 's/^Java version: //p')" || exit 1; \
	case "$$maven_java_version" in \
	  25*|25.*) ;; \
	  *) \
	    echo "java 25 is required, but maven is not using java 25."; \
	    echo; \
	    echo "maven java:"; \
	    echo "  Java version: $$maven_java_version"; \
	    echo; \
	    echo "run one of:"; \
	    echo "  source scripts/use-java-25-mac.sh"; \
	    echo "  source scripts/use-java-25-windows.sh"; \
	    echo "  . .\\scripts\\use-java-25-windows.ps1"; \
	    echo; \
	    echo "then verify:"; \
	    echo "  java --version"; \
	    echo "  mvn --version"; \
	    exit 1; \
	esac

java-version: check-java-25
	$(JAVA_CMD) --version
	mvn --version

test: check-java-25
	mvn test

clean-test: check-java-25
	mvn clean test

demos: check-java-25
	mvn "-Dtest=SimpleWebServerNotesTest,SimpleStaticFileServerTest" test
	mvn "-Dtest=CodeSnippetJavaDocNotesTest,JavaDocSnippetExamplesTest" test
	mvn "-Dtest=KeyEncapsulationMechanismNotesTest,KeyEncapsulationExchangeTest" test
	mvn "-Dtest=ForeignFunctionMemoryApiNotesTest,ForeignFunctionExamplesTest" test
	mvn "-Dtest=StreamGatherersExamplesTest" test
	mvn "-Dtest=ClassFileApiNotesTest,ClassFileInspectorTest" test
	mvn "-Dtest=QuantumResistantCryptoNotesTest,ModuleLatticeCryptoExamplesTest" test
	mvn "-Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest" test
	mvn "-Dtest=KeyDerivationFunctionNotesTest,HkdfKeyDerivationExampleTest" test

run: check-java-25
	mvn spring-boot:run

docs: check-java-25
	mvn javadoc:javadoc

clean-docs:
	rm -rf target/site/apidocs

docs-audit:
	@node scripts/check-doc-navigation.mjs

links:
	lychee --config .lychee.toml README.md "docs/**/*.md" "src/main/java/**/README.md"

docs-check: check-java-25 docs-audit docs links

check: check-java-25 java-version test

release-check: check-java-25 docs-check check demos
