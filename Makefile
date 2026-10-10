.PHONY: tests releases spotbugs-check

spotbugs-check:
	mvn clean compile spotbugs:check

tests:
	mvn clean install test

releases:
	mvn clean install verify test spotbugs:spotbugs jacoco:report spotbugs:spotbugs-aggregate site

# check-violations:
#	mvn checkstyle:check

# violation-report:
#	mvn checkstyle:checkstyle