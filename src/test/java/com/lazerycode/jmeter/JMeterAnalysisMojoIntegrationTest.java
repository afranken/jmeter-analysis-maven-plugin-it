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

  private static final String GROUP_ID = "com.lazerycode.jmeter";

  public void testJMeterMojo() throws Exception {

      Verifier verifier;

      // The testdir is computed from the location of this file.
      File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/jmeter-analysis-maven-plugin-it-run");

      /**
       * We must first make sure that any artifact created
       * by this test has been removed from the local
       * repository. Failing to do this could cause
       * unstable test results.
       */
      verifier = new Verifier( testDir.getAbsolutePath() );
      //verifier.deleteArtifacts(GROUP_ID);

      /**
       * The Command Line Options (CLI) are passed to the
       * verifier as a list. This is handy for things like
       * redefining the local repository if needed.
       */
      List<String> cliOptions = new ArrayList<String>();
      //produce DEBUG output in case an error occurs and one would like to take a look at the log
      cliOptions.add( "-X" );
      //produce execution error messages (with stacktraces)
      cliOptions.add( "-e" );

      verifier.setCliOptions( cliOptions );

      //call "mvn clean verify" for jmeter-analysis-maven-plugin-it-run
      verifier.executeGoal( "clean" );
      verifier.executeGoal( "verify" );

      //make sure that all expected files are created, see expected-results.txt
      //also checks that Maven log does not contain "[ERROR]" elements
      verifier.verify(true);

      //log should state that 790 requests are present in the JMeter test results file
      verifier.verifyTextInLog("requests:             790");
    }
}