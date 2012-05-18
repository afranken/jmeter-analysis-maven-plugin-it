package com.lazerycode.jmeter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.junit.Test;

/**
 * Integration test for jmeter-analysis-maven-plugin
 */
public class JMeterMojoIntegrationTest extends TestCase {

  private static final String GROUP_ID = "com.lazerycode.jmeter";

  public void testJMeterMojo() throws Exception {

      Verifier verifier;

      // The testdir is computed from the location of this file.
      File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/jmeter-analysis-maven-plugin-it-run");

      /**
       * We must first make sure that any artifact created
       * by this test has been removed from the local
       * repository. Failing to do this could cause
       * unstable test results. Fortunately, the verifier
       * makes it easy to do this.
       */
      verifier = new Verifier( testDir.getAbsolutePath() );
      //verifier.deleteArtifacts(GROUP_ID);

      /**
       * The Command Line Options (CLI) are passed to the
       * verifier as a list. This is handy for things like
       * redefining the local repository if needed. In
       * this case, we use the -N flag so that Maven won't
       * recurse.
       */
      List<String> cliOptions = new ArrayList<String>();
      cliOptions.add( "-N" );
      verifier.setCliOptions( cliOptions );

      //call "mvn clean verify" for jmeter-maven-plugin
      verifier.executeGoal( "clean" );
      verifier.executeGoal( "verify" );

      //make sure that all expected files are created, see expected-results.txt
      verifier.verify(true);

      //log should state that 790 requests are present in the JMeter test results file
      verifier.verifyTextInLog("requests:             790");
    }
}