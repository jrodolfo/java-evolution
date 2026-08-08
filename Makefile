JAVA_25_HOME := $(shell /usr/libexec/java_home -v 25 2>/dev/null)
JAVA_CMD := java

ifneq ($(JAVA_25_HOME),)
export JAVA_HOME := $(JAVA_25_HOME)
export PATH := $(JAVA_HOME)/bin:$(PATH)
JAVA_CMD := $(JAVA_HOME)/bin/java
endif

.PHONY: help java-version test clean-test demos run docs clean-docs docs-audit links docs-check check release-check

help:
	@echo "available targets:"
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

java-version:
	$(JAVA_CMD) --version
	mvn --version

test:
	mvn test

clean-test:
	mvn clean test

demos:
	mvn "-Dtest=SimpleWebServerNotesTest,SimpleStaticFileServerTest" test
	mvn "-Dtest=CodeSnippetJavaDocNotesTest,JavaDocSnippetExamplesTest" test
	mvn "-Dtest=KeyEncapsulationMechanismNotesTest,KeyEncapsulationExchangeTest" test
	mvn "-Dtest=ForeignFunctionMemoryApiNotesTest,ForeignFunctionExamplesTest" test
	mvn "-Dtest=StreamGatherersExamplesTest" test
	mvn "-Dtest=ClassFileApiNotesTest,ClassFileInspectorTest" test
	mvn "-Dtest=QuantumResistantCryptoNotesTest,ModuleLatticeCryptoExamplesTest" test
	mvn "-Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest" test
	mvn "-Dtest=KeyDerivationFunctionNotesTest,HkdfKeyDerivationExampleTest" test

run:
	mvn spring-boot:run

docs:
	mvn javadoc:javadoc

clean-docs:
	rm -rf target/site/apidocs

docs-audit:
	@node scripts/check-doc-navigation.mjs

links:
	lychee --config .lychee.toml README.md "docs/**/*.md" "src/main/java/**/README.md"

docs-check: docs-audit docs links

check: java-version test

release-check: docs-check check demos
