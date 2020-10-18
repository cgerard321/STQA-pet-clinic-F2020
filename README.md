# STQA Project

Champlain STQA 420-521-LA Pet Clinic F2020 repo

## Structure

- Please following standard naming convention as stated in the 'story workflow' section, and don't forget to label your pull requests
- We are all contributing to the same root project. If you break something, it will affect everyone. So, working on your own BRANCH is mandatory.

### Project Structure

- There are 4 teams. The teams will self-name and come up with a four-letter acronym. The acronym will be called your TEAMTAG in these instructions.
- Each team is responsible for one or more functional areas of the codebase.

### Branch Naming

- Branches will be named according to the following convention: type/JiraID_Description
 I like to break it down into 4 'folders' or types:
  - feat/
  - bug/
  - gen/
  - conf/
- For this project, you will only need to use `feat/` and `bug/` 
- After the slash, add your TEAMTAG
- After the TEAMTAG, add a sash and then the JIRA id (it will be something like STQA-4).
- The full branch name would look like this `feat/TEAMA-SQTA-4_Add_Test_Scenario_New_Pet` and would be created and navigated to by executing the following command:

```
git checkout -b feat/TEAMTAG-SQTA-4_Add_Test_Scenario_New_Pet
```

### Pull Requests (PR) Naming

- To make it so we can easily search and find pull requests it is nice to follow a standard such as:

```
feat(TEAMTAG-JIRA-TICKET-ID): short description
```

- In that example, you would replace TEAMTAG with your team's acronym and the JIRA-TICKET-ID with the id from Jira.
- Keep the parentheses.
- Do not include any capital letters or punctuation in the description

### Pull Request Commit Naming

- This is pretty much the exact same as the Pull Request Naming except that at the end there will be an auto-generated number in parentheses. Please don't delete it. Simply add your stuff before it.

```
feat(TEAMTAG-JIRA-TICKET-ID): short description (#420)
```

## Setup

- First create an account on GitHub
- Download git https://git-scm.com/downloads
- Go to the official/ main repo https://github.com/cgerard321/STQA-pet-clinic-F2020
- Click the green button 'Code', and copy the given URL
- On your file explorer, navigate to where you want the project, right-click, and select 'git bash here'
- In the terminal window, type 'git clone' and then paste the copied url. (Do not ctrl + v to paste in the git bash terminal, it does not use standard windows encoding and will add extra invisible chars to the command causing it to error out.) It will look like this:

```
git clone https://github.com/cgerard321/STQA-pet-clinic-F2020.git
```

- The repo on your computer is known as the "local"
- The repo on GitHub is known as the "remote origin" or simply "origin"
- cd into the STQA-pet-clinic-F2020 folder on your computer

```
cd STQA-pet-clinic-F2020/
```
To see that the remote orgin has been correctly set up, type: 
```
git remote add upstream https://github.com/cgerard321/sqta-project-1.git
```
- If we type `git remote -v` we should see 4 different connections, push and fetch for our upstream and for our origin
- Now that you have setup your clone, move on to the 'story workflow section'

## Story Workflow

- So you've setup your clone of the repo and started your first story. Now what?
- We will first navigate to our project in the file explorer, right-click, and select 'git bash here'
- In the current command line, you should see in parentheses, the branch you are currently on. We want to start this 'new story process' from our origin's master branch.
```
Christine@DESKTOP-2VF5PQD MINGW64 /e/STQA-pet-clinic-F2020 (master)
```
- If it says master, great. Skip this next line. If not, type:
```
git checkout master
```

- This will simply transfer us to our origin's master branch
- Next, we will want to update our local project with any code our fellow devs have pushed while we were gone. To do this we must first 'download' the code using the following command:

```
git fetch origin master
```

- We are telling git to download the latest stuff from the master branch on our remote remote
- Then we want to actually start our story fresh with that code, so we will reset our local environment with that newly fetched code:

```
git reset --hard origin/master
```

- It is also important to note this will reset any uncommited changes you've made, so keep that in mind. If you are following along and not starting a story from scratch, you might want to rebase instead. More info on rebasing can be found in the 'useful git commands' section

- Now we will want to make a new branch to start working on our feature or bug fix. Simply type:

```
git checkout -b YOUR-BRANCH-NAME
```

- This command is broken down into 2 parts, `checkout` will move you to a given branch the `-b` modifier will create the branch
- You have now created your new branch and are on it. Check the 'structure' section for what you should write in place of YOUR-BRANCH-NAME
- Now it's time to actually write some code. So go start implementing a new feature using TDD. Then come back after you're done. 
- So now you have hopefully something done or at least the start to it and want to commit it
- First, we have to stage all edits, additions, and removals

```
git add .
```

- We can also stage specific files with a relative path

```
git add /path/to/file
```

- Next we will commit the code

```
git commit -m "A short description of what work was done in the commit"
```

- After that you might repeat the `git add .` and `git commit` a couple times before your masterpiece is done

- When you are ready to show it to everyone else or if you want to be able to access it on another computer, we have to push it with this command: (it might ask you for login creds)

```
git push
```

- Again this is the same this as saying `git push origin YOUR-BRANCH-NAME` the `origin` and `YOUR-BRANCH-NAME` are implicitly applied

- Imagine at this point that everything in the story is done, and you are ready to get it code reviewed by the other devs. We need to make a pull request to do that
- Go to your origin's github page (or project repo) and make a new pull request. At the top, verify that the branch and HEAD are all coming from and going to the correct place. Branch should be YOUR-BRANCH-NAME and HEAD should be master.
- Add a title as per the instructions in the 'structure' section, and make sure to add the label on the side bar, indicating which team you are on
- In order to merge this Pull Request (PR), we need two other people to review and approve it. You can get other peoples attention by 'requesting a review' on the side bar or by sending them a DM in slack
- Start by asking people on your team to do the review but don't hesitate to ask someone from a different team if there is an interaction
- Once you've pleased everyone, your code is in prime condition, and you have no merge conflicts you can finally hit the 'squash and merge' button and set another title. Follow the naming conventions in the 'Pull Request Commit Naming' section of 'Structure'
- Your PR is now merged and everyone can fetch and rebase or pull to see the work you've done
- Congrats. Just repeat this process until the semester is over.

## Merge Conflicts / Updating your Branch

The commands are pretty much the same whether you are updating a branch, or you are trying to fix a merge conflict, except if you are updating you will skip the `git add .` and the `git rebase --continue` because you don't have anything to fix. You will still have to `git push -f`

Here's the scenario: Oh no, you have a merge conflict! This happens when you and another dev are working on the same file and edit the same line or git can't automatically figure out how to add your code and the master code together.

Once you see this error on your pull request, or if you happen to run into it outside of a PR, just follow these easy steps:

- First download the origin master data

```
git fetch origin master
```

- Next we will use the rebase command

```
git rebase origin/master
```

- Git will now replay the commits of your branch on top of the origin master. If you have a merge conflict, the prompt will pause and tell you which files were affected. From there, just navigate to your file and update the code accordingly.

- Once you have fixed all the merge conflicts go back to your terminal and type:

```
git add .
```

- Then

```
git rebase --continue
```

- This command is telling git "ok I've fixed this conflict now move on to the next commit"

- If you have more conflicts, repeat the last couple of steps, until the rebase is complete

- Generally, you can tell the rebase is complete when you look at the branch name in your terminal, and it is the correct branch name i.e. without any extra text or random symbols

- After that, the rebase has made a new local commit with all your changes, only one step left which is to force push:

```
git push -f
```

- This is just shorthand for `git push --force`

- If you don't force push you'll get a bunch of red and yellow text, which looks like you messed up, but it's fine. It didn't actually do anything just redo the command but with the `-f`

- At this point, if you go back to your pull request, you should be able to automatically merge the branch.

## Useful Git Commands

This command lets you see any edited, added, or removed files:

```
git status
```

This will show you the differences between last commit (HEAD is master) and your local repo. Press q when you want to leave:

```
git diff HEAD .
```

This will list all your remotes:

```
git remote -v
```

This will list all your branches and there will be a star next to the branch you are currently on:

```
git branch
```

Reset your current branch to the upstream master:

```
git fetch origin master
git reset --hard origin/master
```

If you want to rebase the upstream master on top of your working branch:

```
git fetch origin master
git rebase origin/master
```

Switch to a branch:

```
git checkout BRANCH-NAME
```

Creating and switching to a branch:

```
git checkout -b BRANCH-NAME
```

Add all files ot be staged:

```
git add .
```

Remove all files from staging area:

```
git reset HEAD .
```

Commit all staged files:

```
git commit -m "My message"
```

Push code to remote repo:

```
git push
```

Push code to remote repo after rebase, use this one carefully:

```
git push --force
```

Select a specific commit and replay in onto a branch, don't include angled brackets:

```
git cherry-pick <commitId>
```

To save the stuff you have been working on if you need to quickly change branches but don't want to commit or want to transfer work from one branch to another, the basic is with git stash and there are a variety of variation you can look up but for general uses, this first command will store the data:

```
git stash
```

The next command will re-apply the data:

```
git stash pop
```

---

# Spring PetClinic Sample Application

[![Build Status](https://travis-ci.org/spring-petclinic/spring-framework-petclinic.svg?branch=master)](https://travis-ci.org/spring-petclinic/spring-framework-petclinic/)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=spring-petclinic_spring-framework-petclinic&metric=alert_status)](https://sonarcloud.io/dashboard?id=spring-petclinic_spring-framework-petclinic)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=spring-petclinic_spring-framework-petclinic&metric=coverage)](https://sonarcloud.io/dashboard?id=spring-petclinic_spring-framework-petclinic)

Approved by the Spring team, this repo is a fork of the [spring-projects/spring-petclinic](https://github.com/spring-projects/spring-petclinic).
It allows the Spring community to maintain a Petclinic version with a plain old **Spring Framework configuration**
and with a **3-layer architecture** (i.e. presentation --> service --> repository).
The "canonical" implementation is now based on Spring Boot, Thymeleaf and [aggregate-oriented domain]([https://github.com/spring-projects/spring-petclinic/pull/200).

## Understanding the Spring Petclinic application with a few diagrams

[See the presentation here](http://fr.slideshare.net/AntoineRey/spring-framework-petclinic-sample-application) (2017 update)

## Running petclinic locally

### With Maven command line

```
git clone https://github.com/spring-petclinic/spring-framework-petclinic.git
cd spring-framework-petclinic
./mvnw jetty:run-war
# For Windows : ./mvnw.cmd jetty:run-war
```

### With Docker

```
docker run -p 8080:8080 springcommunity/spring-framework-petclinic
```

You can then access petclinic here: [http://localhost:8080/](http://localhost:8080/)

<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

## In case you find a bug/suggested improvement for Spring Petclinic

Our issue tracker is available here: https://github.com/spring-petclinic/spring-framework-petclinic/issues

## Database configuration

In its default configuration, Petclinic uses an in-memory database (HSQLDB) which
gets populated at startup with data.
A similar setups is provided for MySQL and PostgreSQL in case a persistent database configuration is needed.
To run petclinic locally using persistent database, it is needed to run with profile defined in main pom.xml file.

For MySQL database, it is needed to run with 'MySQL' profile defined in main pom.xml file.

```
./mvnw jetty:run-war -P MySQL
```

Before do this, would be good to check properties defined in MySQL profile inside pom.xml file.

```
<properties>
    <jpa.database>MYSQL</jpa.database>
    <jdbc.driverClassName>com.mysql.cj.jdbc.Driver</jdbc.driverClassName>
    <jdbc.url>jdbc:mysql://localhost:3306/petclinic?useUnicode=true</jdbc.url>
    <jdbc.username>root</jdbc.username>
    <jdbc.password>petclinic</jdbc.password>
</properties>
```

You could start MySQL locally with whatever installer works for your OS, or with docker:

```
docker run -e MYSQL_USER=petclinic -e MYSQL_PASSWORD=petclinic -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:5.7.8
```

For PostgreSQL database, it is needed to run with 'PostgreSQL' profile defined in main pom.xml file.

```
./mvnw jetty:run-war -P PostgreSQL
```

Before do this, would be good to check properties defined in PostgreSQL profile inside pom.xml file.

```
<properties>
    <jpa.database>POSTGRESQL</jpa.database>
    <jdbc.driverClassName>org.postgresql.Driver</jdbc.driverClassName>
    <jdbc.url>jdbc:postgresql://localhost:5432/petclinic</jdbc.url>
    <jdbc.username>postgres</jdbc.username>
    <jdbc.password>petclinic</jdbc.password>
</properties>
```

You could also start PostgreSQL locally with whatever installer works for your OS, or with docker:

```
docker run --name postgres-petclinic -e POSTGRES_PASSWORD=petclinic -e POSTGRES_DB=petclinic -p 5432:5432 -d postgres:9.6.0
```

## Working with Petclinic in your IDE

### Prerequisites

The following items should be installed in your system:

- Java 8 or above
- Maven 3.3+ (http://maven.apache.org/install.html)
- git command line tool (https://help.github.com/articles/set-up-git)
- Jetty 9.4+ or Tomcat 9+
- Your prefered IDE
  - Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in Help -> About dialog. If m2e is not there, just follow the install process here: http://www.eclipse.org/m2e/
  - [Spring Tools Suite](https://spring.io/tools) (STS)
  - IntelliJ IDEA

### Steps:

1. On the command line

```
git clone https://github.com/spring-petclinic/spring-framework-petclinic.git
```

2. Inside Eclipse or STS

```
File -> Import -> Maven -> Existing Maven project
```

Then either build on the command line `./mvnw generate-resources` or using the Eclipse launcher (right click on project and `Run As -> Maven install`) to generate the CSS.
Configure a Jetty or a Tomcat web container then deploy the `spring-petclinic.war` file.

3. Inside IntelliJ IDEA

In the main menu, select `File > Open` and select the Petclinic [pom.xml](pom.xml). Click on the `Open` button.

CSS files are generated from the Maven build. You can either build them on the command line `./mvnw generate-resources`
or right click on the `spring-petclinic` project then `Maven -> Generates sources and Update Folders`.

Go to the `Run -> Edit Configuration` then configure a Tomcat or a Jetty web container. Deploy the `spring-petclinic.war` file.
Run the application by clicking on the `Run` icon.

4. Navigate to Petclinic

Visit [http://localhost:8080](http://localhost:8080) in your browser.

## Working with Petclinic in IntelliJ IDEA

### prerequisites

The following items should be installed in your system:

## Looking for something in particular?

| Java Config        |                                                                                                                                                                                                                            |
| ------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Java config branch | Petclinic uses XML configuration by default. In case you'd like to use Java Config instead, there is a Java Config branch available [here](https://github.com/spring-petclinic/spring-framework-petclinic/tree/javaconfig) |

| Inside the 'Web' layer                      | Files                                                                                                                                       |
| ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| Spring MVC - XML integration                | [mvc-view-config.xml](src/main/resources/spring/mvc-view-config.xml)                                                                        |
| Spring MVC - ContentNegotiatingViewResolver | [mvc-view-config.xml](src/main/resources/spring/mvc-view-config.xml)                                                                        |
| JSP custom tags                             | [WEB-INF/tags](src/main/webapp/WEB-INF/tags), [createOrUpdateOwnerForm.jsp](src/main/webapp/WEB-INF/jsp/owners/createOrUpdateOwnerForm.jsp) |
| JavaScript dependencies                     | [JavaScript libraries are declared as webjars in the pom.xml](pom.xml)                                                                      |
| Static resources config                     | [Resource mapping in Spring configuration](/src/main/resources/spring/mvc-core-config.xml#L30)                                              |
| Static resources usage                      | [htmlHeader.tag](src/main/webapp/WEB-INF/tags/htmlHeader.tag), [footer.tag](src/main/webapp/WEB-INF/tags/footer.tag)                        |
| Thymeleaf                                   | In the late 2016, the original [Spring Petclinic](https://github.com/spring-projects/spring-petclinic) has moved from JSP to Thymeleaf.     |

| 'Service' and 'Repository' layers | Files                                                                                                                                                                                                                                                                                                    |
| --------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Transactions                      | [business-config.xml](src/main/resources/spring/business-config.xml), [ClinicServiceImpl.java](src/main/java/org/springframework/samples/petclinic/service/ClinicServiceImpl.java)                                                                                                                       |
| Cache                             | [tools-config.xml](src/main/resources/spring/tools-config.xml), [ClinicServiceImpl.java](src/main/java/org/springframework/samples/petclinic/service/ClinicServiceImpl.java)                                                                                                                             |
| Bean Profiles                     | [business-config.xml](src/main/resources/spring/business-config.xml), [ClinicServiceJdbcTests.java](src/test/java/org/springframework/samples/petclinic/service/ClinicServiceJdbcTests.java), [PetclinicInitializer.java](src/main/java/org/springframework/samples/petclinic/PetclinicInitializer.java) |
| JDBC                              | [business-config.xml](src/main/resources/spring/business-config.xml), [jdbc folder](src/main/java/org/springframework/samples/petclinic/repository/jdb)                                                                                                                                                  |
| JPA                               | [business-config.xml](src/main/resources/spring/business-config.xml), [jpa folder](src/main/java/org/springframework/samples/petclinic/repository/jpa)                                                                                                                                                   |
| Spring Data JPA                   | [business-config.xml](src/main/resources/spring/business-config.xml), [springdatajpa folder](src/main/java/org/springframework/samples/petclinic/repository/springdatajpa)                                                                                                                               |

## Publishing a Docker image

This application uses [Google Jib]([https://github.com/GoogleContainerTools/jib) to build an optimized Docker image
into the [Docker Hub](https://cloud.docker.com/u/springcommunity/repository/docker/springcommunity/spring-framework-petclinic/)
repository.
The [pom.xml](pom.xml) has been configured to publish the image with a the `springcommunity/spring-framework-petclinic` image name.

Jib containerizes this WAR project by using the [distroless Jetty](https://github.com/GoogleContainerTools/distroless/tree/master/java/jetty) as a base image.

Build and push the container image of Petclinic to the Docker Hub registry:

```
mvn jib:build
```

## Interesting Spring Petclinic forks

The Spring Petclinic master branch in the main [spring-projects](https://github.com/spring-projects/spring-petclinic)
GitHub org is the "canonical" implementation, currently based on Spring Boot and Thymeleaf.

This [spring-framework-petclinic](https://github.com/spring-petclinic/spring-framework-petclinic) project is one of the [several forks](https://spring-petclinic.github.io/docs/forks.html)
hosted in a special GitHub org: [spring-petclinic](https://github.com/spring-petclinic).
If you have a special interest in a different technology stack
that could be used to implement the Pet Clinic then please join the community there.

## Interaction with other open source projects

One of the best parts about working on the Spring Petclinic application is that we have the opportunity to work in direct contact with many Open Source projects. We found some bugs/suggested improvements on various topics such as Spring, Spring Data, Bean Validation and even Eclipse! In many cases, they've been fixed/implemented in just a few days.
Here is a list of them:

| Name                                                                                          | Issue                                                                                                                       |
| --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| Spring JDBC: simplify usage of NamedParameterJdbcTemplate                                     | [SPR-10256](https://jira.springsource.org/browse/SPR-10256) and [SPR-10257](https://jira.springsource.org/browse/SPR-10257) |
| Bean Validation / Hibernate Validator: simplify Maven dependencies and backward compatibility | [HV-790](https://hibernate.atlassian.net/browse/HV-790) and [HV-792](https://hibernate.atlassian.net/browse/HV-792)         |
| Spring Data: provide more flexibility when working with JPQL queries                          | [DATAJPA-292](https://jira.springsource.org/browse/DATAJPA-292)                                                             |
| Dandelion: improves the DandelionFilter for Jetty support                                     | [113](https://github.com/dandelion/dandelion/issues/113)                                                                    |

# Contributing

The [issue tracker](/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <http://editorconfig.org>.
