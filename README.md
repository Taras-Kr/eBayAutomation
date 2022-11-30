# eBayAutomation

eBayAutomation is a Java project for the automated testing [eBay](https://www.ebay.com/) website.

Author: Taras Krasitskyi

# Functionality

## Features review

1. Simple syntax (using **Selenide**)
2. Using tests suit for running tests (using **XML** tests suits files)
3. Multi browsers support (**Chrome** and **Firefox**)
4. Test reports (**Allure Framework**)

## Prerequisites

Before installing the whole project you have to install the following:

1. [Java SDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
2. [Chrome](https://www.google.com/intl/uk_ua/chrome/) and/or [Firefox](https://www.mozilla.org/uk/firefox/new/) for
   test running
3. [Maven](https://maven.apache.org/download.cgi)
4. [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)
5. [Allure Framework](https://docs.qameta.io/allure-report/#_get_started)

### Verify you installed the correct version

In the IDEA terminal type:
<li>
<code> java -version</code> to get installed java version
</li>
<li>
<code> mvn -version</code>  to get installed maven version
</li>
<li>
<code> allure --version</code>  to get installed Allure Framework version
</li>

## Installation
1. Copy code from GitHub repository
2. In the IntelliJ IDEA create New project from Version Control

## Running tests

1. Run a single test class in the IDEA terminal type:

<code>mvn test -Dtest=ClassNameTest</code>

2. Run single test method:

<code>mvn test -Dtest=ClassNameTest#methodName</code>

3. Run test using XML site file

<li>Run default suite file

<code>mvn test</code></li>
<li>Run specified suite file</li>
<code> mvn test -DsuiteXml="test_suite_name.xml"</code>

## Multi browsers support
By default, test runs  with Chrome.
Run tests with Firefox to command line add parameter <code> -Dbrowser=firefox</code>

For example:

<code>mvn test -DsuiteXml="test_suite_name.xml" -Dbrowser=firefox</code>

## View reports
In the IDEA terminal type:

``` allure serve allure-reports```
