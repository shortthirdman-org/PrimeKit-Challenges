.PHONY: tests releases

tests:
	mvn clean install test

releases:
	mvn clean install verify test jacoco:report