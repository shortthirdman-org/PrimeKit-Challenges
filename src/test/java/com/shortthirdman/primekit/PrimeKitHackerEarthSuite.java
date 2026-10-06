package com.shortthirdman.primekit;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("HackerEarth JUnit Platform Suite")
@SelectPackages("com.shortthirdman.primekit.hackerearth")
@IncludeClassNamePatterns(".*Test")
class PrimeKitHackerEarthSuite {
}
