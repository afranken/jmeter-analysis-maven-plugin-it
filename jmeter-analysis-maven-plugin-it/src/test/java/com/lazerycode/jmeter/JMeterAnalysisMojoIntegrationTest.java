package com.lazerycode.jmeter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

/**
 * Integration test for jmeter-analysis-maven-plugin
 */
public class JMeterAnalysisMojoIntegrationTest extends TestCase {

  /**
   * Test jmeter-analysis-maven-plugin with minimal configuration
   */
  public void testJMeterMojo() throws Exception {

    // The testdir is computed from the location of this file.
    File testDir = ResourceExtractor.simpleExtractResources(getClass(),
            "/jmeter-analysis-maven-plugin-it-run");

    Verifier verifier = runTest(testDir);

    //log should state that 790 requests are present in the JMeter test results file
    verifier.verifyTextInLog("requests:             790");

  }

  /**
   * Test jmeter-analysis-maven-plugin with minimal configuration
   */
//  public void testJMeterMojoUmlauts() throws Exception {
//
//    // The testdir is computed from the location of this file.
//    File testDir = ResourceExtractor.simpleExtractResources(getClass(),
//            "/jmeter-analysis-maven-plugin-it-encoding");
//
//    Verifier verifier = runTest(testDir);
//
//    //log should state that 790 requests are present in the JMeter test results file
//    verifier.verifyTextInLog("requests:             790");
//
//  }

  /**
   * Test jmeter-analysis-maven-plugin with preserveDirectories option
   */
  public void testJMeterMojoPreserveDirs() throws Exception {

    // The testdir is computed from the location of this file.
    File testDir = ResourceExtractor.simpleExtractResources(getClass(),
            "/jmeter-analysis-maven-plugin-it-run-preservedirs");

    Verifier verifier = runTest(testDir);

    //log should state that 790 requests are present in the JMeter test results file
    verifier.verifyTextInLog("requests:             790");

  }

  /**
   * Test jmeter-analysis-maven-plugin with requestGroups option
   */
  public void testJMeterMojoRequestGroups() throws Exception {

    // The testdir is computed from the location of this file.
    File testDir = ResourceExtractor.simpleExtractResources(getClass(),
            "/jmeter-analysis-maven-plugin-it-run-requestgroups");

    Verifier verifier = runTest(testDir);

    //log should state that 132 and 658 requests are present in the JMeter test results file
    verifier.verifyTextInLog("requests:             132");
    verifier.verifyTextInLog("requests:             658");

  }

  //=====================================================================================================================

  private Verifier runTest(File testDirectory) throws Exception {
    Verifier verifier;

    verifier = new Verifier(testDirectory.getAbsolutePath());

    /**
     * The Command Line Options (CLI) are passed to the
     * verifier as a list. This is handy for things like
     * redefining the local repository if needed.
     */
    List<String> cliOptions = new ArrayList<String>();
    //produce DEBUG output in case an error occurs and one would like to take a look at the log
    cliOptions.add("-X");
    //produce execution error messages (with stacktraces)
    cliOptions.add("-e");

    verifier.setCliOptions(cliOptions);

    //call "mvn clean verify" for jmeter-analysis-maven-plugin-it-run
    verifier.executeGoal("clean");
    verifier.executeGoal("verify");

    //make sure that all expected files are created, see expected-results.txt
    //also checks that Maven log does not contain "[ERROR]" elements
    verifier.verify(true);

    return verifier;
  }

}