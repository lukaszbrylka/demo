# QA repository

## Overview

This repository contains the test suites for various projects that need to be tested by the QA team. It includes Unit tests, API tests, UI tests, and end-to-end (E2E) tests for ensuring the functionality and stability of the given project.

## Prerequisites

System recommended: Ubuntu 20.04

Before getting started, ensure the following dependencies are installed on your system:

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Selenium-server](https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.18.0/selenium-java-4.18.1.zip) - for testing web apps on hub with mulitple nodes and browsers - [Selenium Grid](https://www.selenium.dev/documentation/grid/)
## Development Environment

You can use any Java IDE such as [IntelliJ IDEA](https://www.jetbrains.com/help/idea/installation-guide.html) or [Eclipse](https://linux.how2shout.com/2-methods-to-install-eclipse-ide-on-ubuntu-22-04-or-20-04-lts/) for development and running tests.

## Repository folders structure

In `java` folder following packages should be added: `api`, `e2e`, `ui`, `unit` where tests classes, utils and dependencies are.
In `resources folder` resource files should be added. (e.g. app.apk)

- **Unit**: Includes unit covering created tests logic
- **API**: Contains API tests to ensure the functionality and stability of backend services.
- **UI**: Contains UI tests to validate the user interface and user interactions.
- **End-to-end (E2E)**: Includes end-to-end tests covering the entire system's functionality.

## Getting Started

To get started with QA repository, follow these steps:

1. **Clone the Repository**: Clone the QA repository to your local machine. It is recommended to use SSH instead of HTTTP.

    ```bash
    git clone ...
    ```

2. **Install Dependencies**: Use Maven to install project dependencies.

    ```bash
    cd ...
    mvn clean install
    ```

3. **Run Tests**: Execute the tests using Maven.

    ```bash
    mvn test
    ```

4. **Explore**: Feel free to explore the codebase and make modifications as needed.

## Project Structure

The project follows the following directory [structure](https://github.com/kriasoft/Folder-Structure-Conventions/blob/master/README.md)

## Contributing via Merge Requests

Thank you for considering contributing to our project! We welcome contributions from the community to help improve and grow our project.

### Getting Started


1. **Create a New Branch**: Create a new branch for your changes. Be sure to give your branch a descriptive name that reflects the purpose of your changes.

    ```bash
    git checkout -b feature-name
    ```

2. **Make Changes**: Make your desired changes to the codebase using your preferred text editor or IDE.

3. **Commit Your Changes**: Once you've made your changes, commit them to your branch with descriptive commit messages.

    ```bash
    git add .
    git commit -m ""Add feature X""
    ```

4. **Push Changes**: Push your changes to your forked repository on GitHub.

    ```bash
    git push origin feature-name
    ```

5. **Create a Merge Request**: Navigate to the GitHub page of your forked repository. You should see an option to create a new merge request (or pull request, depending on the platform). Click on it and follow the instructions to create the merge request.

### Review Process

1. **Code Review**: A member of our team will review your merge request, providing feedback and suggestions if necessary.

2. **Address Feedback**: Make any requested changes to your code based on the feedback provided.

3. **Approval**: Once your merge request has been reviewed and approved, it will be merged into the main repository.

## Jenkins

### QA View

- [QA View]

#### Test Projects

- [Test Project]
   - [API Tests]
   - [E2E Tests]
   - [UI Tests]
