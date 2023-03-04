CODE COVERAGE WITH JACOCO
--------------------------------------------------------------------------

Measuring code coverage with the Java Code Coverage Library (JaCoCo).

--------------------------------------------------------------------------

**JaCoCo plugin added to pom.xml file:**

```

        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.5</version>
            <executions>
                <execution>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <!-- attached to Maven test phase -->
                <execution>
                    <id>report</id>
                    <phase>test</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>

```

--------------------------------------------------------------------------

After JaCoco plugin is added, the test maven phase is executed.

![TestPhase](./screenshots/maven_test_phase.png)

A report is generated in the directory target/site/jacoco.

![](./screenshots/jacoco_report_directory.png)

Right now, there are no tests available as you can see in the generated report:

![Report1](./screenshots/jacoco_report_1.png)


--------------------------------------------------------------------------