# jmeter-analysis-maven-plugin-it

Integration test for the jmeter-analysis-maven-plugin.

The [Maven Verifier][1] is used to run the test.
It contains a `pom.xml` with a full configuration for the [JMeter Analysis Maven Plugin][2].

When running the test, the jmeter-analysis-maven-plugin is executed with a JMeter test XML file and produces result files.

[Maven Verifier][1] then checks whether the plugin was executed without problems.


Run the test with `mvn clean verify`.


[1]:    http://maven.apache.org/shared/maven-verifier/        "Maven Verifier Component"
[2]:    http://jmeter.lazerycode.com                          "JMeter Analysis Maven Plugin"
