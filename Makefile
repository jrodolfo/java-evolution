JAVA_27_HOME := $(shell /usr/libexec/java_home -v 27 2>/dev/null)
JAVA_CMD := java
MVNW := ./mvnw

ifeq ($(OS),Windows_NT)
MVNW := cmd.exe /c mvnw.cmd
endif

ifeq ($(strip $(JAVA_HOME)),)
ifneq ($(JAVA_27_HOME),)
export JAVA_HOME := $(JAVA_27_HOME)
export PATH := $(JAVA_HOME)/bin:$(PATH)
JAVA_CMD := $(JAVA_HOME)/bin/java
endif
else
JAVA_CMD := $(JAVA_HOME)/bin/java
endif

.PHONY: help check-java-27 java-version test clean-test demos run docs clean-docs docs-audit links docs-check check release-check

help:
	@echo "available targets:"
	@echo "  make check-java-27 verify the active Java and Maven runtimes use Java 27"
	@echo "  make java-version  show the Java and Maven versions"
	@echo "  make test          run the test suite"
	@echo "  make clean-test    clean the build and run the test suite"
	@echo "  make demos         run the focused practical demo tests"
	@echo "  make run           run the Spring Boot application"
	@echo "  make docs          generate JavaDoc under target/site/apidocs"
	@echo "  make clean-docs    remove generated JavaDoc"
	@echo "  make docs-audit    check documentation navigation consistency"
	@echo "  make links         check Markdown links with lychee; pass VERBOSE=-v or VERBOSE=-vv for lychee details"
	@echo "  make docs-check    run documentation audit, JavaDoc generation, and link check"
	@echo "  make check         show versions and run the test suite"
	@echo "  make release-check run documentation, full test, and practical demo gates"

check-java-27:
	@java scripts/CheckJava27.java

java-version: check-java-27
	$(JAVA_CMD) --version
	$(MVNW) --version

test: check-java-27
	$(MVNW) test

clean-test: check-java-27
	$(MVNW) clean test

demos: check-java-27
	$(MVNW) "-Dtest=SimpleStaticFileServerTest" test
	$(MVNW) "-Dtest=JavaDocSnippetExamplesTest" test
	$(MVNW) "-Dtest=KeyEncapsulationExchangeTest" test
	$(MVNW) "-Dtest=ForeignFunctionExamplesTest" test
	$(MVNW) "-Dtest=StreamGatherersExamplesTest" test
	$(MVNW) "-Dtest=ClassFileInspectorTest" test
	$(MVNW) "-Dtest=ModuleLatticeCryptoExamplesTest" test
	$(MVNW) "-Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest" test
	$(MVNW) "-Dtest=HkdfKeyDerivationExampleTest" test

run: check-java-27
	$(MVNW) spring-boot:run

docs: check-java-27
	$(MVNW) javadoc:javadoc

clean-docs:
	rm -rf target/site/apidocs

docs-audit:
	@node scripts/check-doc-navigation.mjs

links:
	lychee $(VERBOSE) --config .lychee.toml README.md "docs/**/*.md" "src/main/java/**/README.md"

docs-check: check-java-27 docs-audit docs links

check: check-java-27 java-version test

release-check: check-java-27 docs-check check demos
