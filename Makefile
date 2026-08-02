JAVA_25_HOME := $(shell /usr/libexec/java_home -v 25 2>/dev/null)
JAVA_CMD := java

ifneq ($(JAVA_25_HOME),)
export JAVA_HOME := $(JAVA_25_HOME)
export PATH := $(JAVA_HOME)/bin:$(PATH)
JAVA_CMD := $(JAVA_HOME)/bin/java
endif

.PHONY: help java-version test clean-test run docs clean-docs links check

help:
	@echo "available targets:"
	@echo "  make java-version  show the Java and Maven versions"
	@echo "  make test          run the test suite"
	@echo "  make clean-test    clean the build and run the test suite"
	@echo "  make run           run the Spring Boot application"
	@echo "  make docs          generate JavaDoc under target/site/apidocs"
	@echo "  make clean-docs    remove generated JavaDoc"
	@echo "  make links         check Markdown links with lychee"
	@echo "  make check         show versions and run the test suite"

java-version:
	$(JAVA_CMD) --version
	mvn --version

test:
	mvn test

clean-test:
	mvn clean test

run:
	mvn spring-boot:run

docs:
	mvn javadoc:javadoc

clean-docs:
	rm -rf target/site/apidocs

links:
	lychee --config .lychee.toml README.md docs/*.md src/main/java/net/jrodolfo/java_evolution/java*/README.md

check: java-version test
