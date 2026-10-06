package com.shortthirdman.primekit;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("CoderByte JUnit Platform Suite")
@SelectPackages("com.shortthirdman.primekit.coderbyte")
@IncludeClassNamePatterns(".*Test")
class PrimeKitCoderByteSuite {
}
