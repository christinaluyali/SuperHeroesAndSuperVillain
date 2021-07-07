Pre-requisites
- JDK 1.8 or higher
- Maven
- Eclipse with Cucumber for Java plugin

1. Clone this repository and set up a local workspace  (File>Import>Maven>Existing Maven Projects>Next)
2. Run src/test/java/runner/Runner.java to execute the Cucumber tests. Update tags to @Firefox, @Chrome or @InternetExplorer to run tests on different browsers.
3. HTML/JSON reports will be generated under target/cucumber-reports and target/cucumber folders respectively. 