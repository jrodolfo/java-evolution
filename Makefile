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

.PHONY: help check-java-27 show-versions run-tests clean-tests run-demos run-app generate-docs clean-docs audit-docs check-links check-docs check-build check-release

help:
	@echo "available targets:"
	@echo "  make check-java-27 verify the active Java and Maven runtimes use Java 27"
	@echo "  make show-versions show the Java and Maven versions"
	@echo "  make run-tests     run the test suite"
	@echo "  make clean-tests   clean the build and run the test suite"
	@echo "  make run-demos     run the focused practical demo tests"
	@echo "  make run-app       run the Spring Boot application"
	@echo "  make generate-docs generate JavaDoc under target/site/apidocs"
	@echo "  make clean-docs    remove generated JavaDoc"
	@echo "  make audit-docs    check documentation navigation consistency"
	@echo "  make check-links   check Markdown links with lychee; pass VERBOSE=-v or VERBOSE=-vv for lychee details"
	@echo "  make check-docs    run documentation audit, JavaDoc generation, and link check"
	@echo "  make check-build   show versions and run the test suite"
	@echo "  make check-release run documentation, full test, and practical demo gates"

check-java-27:
	@java scripts/CheckJava27.java

show-versions: check-java-27
	$(JAVA_CMD) --version
	$(MVNW) --version

run-tests: check-java-27
	$(MVNW) test

clean-tests: check-java-27
	$(MVNW) clean test

run-demos: check-java-27
	$(MVNW) "-Dtest=SimpleStaticFileServerTest" test
	$(MVNW) "-Dtest=JavaDocSnippetExamplesTest" test
	$(MVNW) "-Dtest=KeyEncapsulationExchangeTest" test
	$(MVNW) "-Dtest=ForeignFunctionExamplesTest" test
	$(MVNW) "-Dtest=StreamGatherersExamplesTest" test
	$(MVNW) "-Dtest=ClassFileInspectorTest" test
	$(MVNW) "-Dtest=ModuleLatticeCryptoExamplesTest" test
	$(MVNW) "-Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest" test
	$(MVNW) "-Dtest=HkdfKeyDerivationExampleTest" test

run-app: check-java-27
	$(MVNW) spring-boot:run

generate-docs: check-java-27
	$(MVNW) javadoc:javadoc

clean-docs:
	rm -rf target/site/apidocs

audit-docs:
	@node scripts/check-doc-navigation.mjs

check-links:
	lychee $(VERBOSE) --config .lychee.toml README.md "docs/**/*.md" "src/main/java/**/README.md"

check-docs: check-java-27 audit-docs generate-docs check-links

check-build: check-java-27 show-versions run-tests

check-release: check-java-27 check-docs check-build run-demos
