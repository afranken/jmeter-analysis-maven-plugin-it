# jmeter-analysis-maven-plugin-it

Integration test for the jmeter-analysis-maven-plugin.

The [Maven Verifier][1] is used to run the test.
It contains a `pom.xml` with a full configuration for the [JMeter Analysis Maven Plugin][2].

When running the test, the jmeter-analysis-maven-plugin is executed with a JMeter test XML file and produces result files.

[Maven Verifier][1] then checks whether
 * the plugin was executed successfully
 * the maven log shows any ERRORS
 * the maven log contains text that indicates that the jmeter-analysis-maven-plugin finished correctly
 * that all files listed in `expected-results.txt` are present afterwards

if any of the above is negative, the test fails.

In order to make version, dependency and plugin management as easy as possible, both the `jmeter-analysis-maven-plugin-it` module and the enclosed `jmeter-analysis-maven-plugin-it-run` module (the module that runs the actual test) inherit from `jmeter-analysis-maven-plugin-it-parent`.


[1]:    http://maven.apache.org/shared/maven-verifier/        "Maven Verifier Component"
[2]:    http://jmeter.lazerycode.com                          "JMeter Analysis Maven Plugin"
