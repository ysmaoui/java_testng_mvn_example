# JAVA/Testng simple example with maven

This is a simple project that has testng tests with requirements ( or any other information) can be added added as attributes in the test execution results.

## Environment

Docker can be used to get a quick running environment that has java and maven setup.

The following command would open a bash terminal attached to a docker container with java and maven installed.
the commands to generate the project and execute mvn commands can be executed from inside the docker container.
all generated project files would be stored in the volume mapped to `/workspace` which is in this case the current working directory ( `"$PWD"`)

```sh
    docker run -it --rm -v "$PWD":/workspace -w /workspace maven:3.6.0-jdk-11-slim bash
```

## Project structure

The base project that was used here was generated ( Ref: [<https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html>] )

```sh
mvn archetype:generate -DgroupId=com.example.app -DartifactId=testng_example -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

## Build

```sh
mvn package -DsuiteXmlFile=[testng_testsuite_file.xml]
```

## run tests

```sh
mvn test -DsuiteXmlFile=[testng_testsuite_file.xml]
```

## Definition of the suiteXmlFile option in the pom file

the option suiteXmlFile provides the possibility to specify the test suite that needs to be executed

```xml
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
          <configuration>
            <!-- Suite testng xml file to consider for test execution -->
            <suiteXmlFiles>
              <suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
            </suiteXmlFiles>
            ...
          </configuration>
        </plugin>
```

otherwise the exact file name can be specified in the pom file and we wouldn't need the option `-DsuiteXmlFile` to execute the tests

for example:

```xml
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
          <configuration>
            <!-- Suite testng xml file to consider for test execution -->
            <suiteXmlFiles>
              <suiteXmlFile>testsuite_1.xml</suiteXmlFile>
              <suiteXmlFile>testsuite_2.xml</suiteXmlFile>
            </suiteXmlFiles>
            ...
          </configuration>
        </plugin>
```

## Enabling the test attributes in the xml report

Ref: [<https://github.com/cbeust/testng/issues/1673#issuecomment-362893227>]

To enable test attributes in the test report the property `generateTestResultAttributes` needs to be set to true, this can be done with mvn in the pom file as follows:

```xml
    <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.22.1</version>
        <configuration>
        ...
        <properties>
            <property>
            <name>reporter</name>
            <value>org.testng.reporters.XMLReporter:generateTestResultAttributes=true,generateGroupsAttribute=true</value>
            </property>
        </properties>
        ...
        </configuration>
    </plugin>
```

To add attributes to a test:

Ref: [<https://github.com/cbeust/testng/issues/1673#issuecomment-362893227>]

```java
    import org.testng.ITestResult;
    import org.testng.Reporter;
    import org.testng.annotations.Test;

    public class GithubIssue1673Sample {
        @Test(groups = "regression")
        public void testMethod1() {
            System.err.println("testMethod1() executed");
            ITestResult result = Reporter.getCurrentTestResult();
            result.setAttribute("name", "Krishnan");
        }
        @Test(groups = "regression")
        public void testMethod2() {
            System.err.println("testMethod2() executed");
            ITestResult result = Reporter.getCurrentTestResult();
            result.setAttribute("name", "Mahadevan");
        }

    }
```
