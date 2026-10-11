# Selenium + Jenkins CI/CD Demo (Windows)

## What this project demonstrates
- Java + Selenium WebDriver automated browser tests
- JUnit 5 test cases
- Maven dependency/build/test management
- Jenkins Pipeline using a `Jenkinsfile`
- JUnit XML reports in Jenkins

The sample login page is started locally by the test code. It is a demonstration page only; it is not connected to a real account or database.

## Prerequisites
- JDK 17 installed and `JAVA_HOME` configured
- Apache Maven 3.9+ installed and on `PATH`
- Google Chrome installed
- Jenkins installed and running
- Git installed
- Internet access on the first Maven run so dependencies can be downloaded

Check in PowerShell:
```powershell
java -version
mvn -version
git --version
```

Selenium Manager, included with modern Selenium, normally discovers/downloads a compatible ChromeDriver automatically. The Jenkins agent must have access to Chrome and the internet or a pre-provisioned compatible driver.

## Run locally
Open PowerShell in this folder:
```powershell
mvn clean test
```
Expected: 3 tests, 0 failures. Reports are in `target/surefire-reports/`.

## Push to GitHub
Create an empty GitHub repository, then run:
```powershell
git init
git add .
git commit -m "Add Selenium Jenkins demo"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/selenium-jenkins-demo.git
git push -u origin main
```
Replace the URL with your own repository URL.

## Configure Jenkins
1. Open Jenkins at `http://localhost:8080`.
2. Install suggested plugins, including Pipeline, Git and JUnit if not already present.
3. Go to Manage Jenkins > Tools.
4. Add a JDK installation named exactly `JDK17`. If Jenkins auto-installer is unavailable, set its local installation path.
5. Add Maven named exactly `Maven3`, or adjust the names in the Jenkinsfile to match your tool names.
6. Choose New Item > Pipeline.
7. Under Pipeline, select "Pipeline script from SCM".
8. SCM: Git; Repository URL: your GitHub URL; Branch: `*/main`; Script Path: `Jenkinsfile`.
9. Save and select Build Now.
10. Open Console Output and Test Result.

## Automatic runs after a push
For a simple classroom demo, use "Build Now". To trigger on each push, configure a GitHub webhook to your reachable Jenkins URL (`https://YOUR-JENKINS-URL/github-webhook/`) and enable the GitHub hook trigger in the job. A local `localhost` Jenkins instance cannot receive webhooks directly from GitHub unless exposed through a properly secured, reachable endpoint. Never expose an unsecured Jenkins server publicly.

## Common issues
- `mvn is not recognized`: install Maven and add its `bin` folder to PATH, then reopen PowerShell.
- `JAVA_HOME` or release version error: verify JDK 17 is installed and Jenkins uses JDK 17.
- ChromeDriver/session error: update Chrome, allow Selenium Manager network access, or preinstall a compatible ChromeDriver.
- Jenkins says tool `JDK17` is not configured: define it in Manage Jenkins > Tools or change the Jenkinsfile tool name.
- No test report: check that Maven reached the test stage and inspect `target/surefire-reports/`.
- Browser cannot start on an agent: install Chrome on that agent and ensure it can run headless Chrome.

## Pipeline stages
Checkout -> Build/compile -> Selenium tests -> Publish JUnit report.

## Important
This is an educational CI pipeline demonstrating build and automated test execution. It is not deploying an application to production. Add a deployment stage only when you have a target environment and deployment credentials managed securely.
