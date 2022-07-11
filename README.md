# API Automation Framework

### Table of Contents

- [Background](#background-block)
- [Resources](#resources)

---------------------------
<div id='background-block'></div>

## Background 

This is an API Automation Framework coded in Java with **[RestAssured.io](https://github.com/rest-assured/rest-assured)**. 

The purpose of this automation framework is to assist QA Test Engineers in their test automation projects in sending and receiving API requests and responses, that they don't need to connect and learn a new API test framework in their automation project.

---------------------------

## <a name="resources"></a>
## Resources

Git Repo: https://github.com/bnard-spn/restassured-api-automation

Sample Cucumber Test Automation using this framework: https://github.com/bnard-spn/restassured-cucumber-api-project

Mock API: https://github.com/bnard-spn/js-express-api

---------------------------

## How to connect

The initial requirements for you to connect with this test automation framework is to have a test automation project coded in Java and uses Maven as a dependency management tool with a _pom.xml_ file in the test automation project. 

It is also possible to add this test automation framework to your project if you have Gradle as your dependency management tool, but it has not been tested yet.

Kindly follow the steps below if you meet the requirement above:

### 1. Cloning the Git Project

Go to the project folder where your test automation is stored and clone this git project at that same directory by using this command:

```
  git clone https://github.com/bnard-spn/restassured-api-automation.git
```

That will copy the git project in your local folder.

### 2. Build project

Go to your terminal and go to the directory where this automation framework is saved:

```
  cd <path_to_project_directory>/restassured-api-automation/
```

Once inside the project folder, you can build the project using this command:

```
  mvn install
```

The terminal should log this maven build similar to this:

![MavenBuild](src/test/resources/docs/MavenBuild.PNG)

This command will build the project and will create it's _.jar_ file that can be used by other java projects, specifically your test automation project. 

### 3. Add Maven dependency 

In your test automation project, go to your _pom.xml_ file and put this code block in the dependency block:

```xml
<dependency>
    <groupId>com.deloitte.qa.commons</groupId>
    <artifactId>restassured-api-automation</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

After putting this in your _pom.xml_ file, you need to rebuild your project for the changes to take effect. If build is successful, you can now connect to the different public methods and classes of the automation framework.

> **Note:**
> 
> It is possible to add this API test automation git project as a maven dependency without cloning the git project itself to your local machine. This feature is still undergoing feasibility testing. This project will be updated once this feature is available.

---------------------------

[//]: # (## Features)

[//]: # ()
[//]: # (### RequestAPI class)

