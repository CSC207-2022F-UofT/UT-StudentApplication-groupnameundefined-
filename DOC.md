# UT-StudentApplication

## Project Scope & Vision

The idea is to create a student application (“the application”) designed to be integrated into student’s daily life on campus, incorporating features such as study partner finder, forum, community and club space, chat system, etc. The application aims to help students connect and develop fellowship during different stages in their study, whether they are freshman who has not had the chance to know any people or find others of similar pursuits, or senior students who wish to enrich their university life by engaging in clubs and finding opportunities. The application provides utility tools such as profile building, schedule analyzing, and communication tools such as forum and chat system. Optional/value-added features include but not limited to campus real-time traffic display / campus navigation, and tutoring system.

As of the current term, the project is divided into several blocks/distinct logical set in order to enable efficient development. Each of the team member is assigned to parts or all of the block without necessary assignment to specific technology stacks (i.e. a team member may be working on the frontend and backend at the sametime).

## Project Specification

### Type
Desktop Application

### Frameworks
Backend: Spring \
Frontend: Java Swing (wrapped in Spring) \
API Type: REST

### Architecture
The main idea about clean architecture is to divide up business logics (e.g. whether a user is allowed to view something) and application logics (e.g. parsing user login status, controlling what is shown based on result from business logic). Most of this architecture will be applied in the backend.


## For Developers

## Basics
Spring provides an application framework and a `IoC (Inversion of Control) container` that controls the instantiation and destruction of instances of objects, called `Beans`. In this application we utilizes SpringBoot to initialize the files and uses its Rest framework to expose APIs. In the frontend we also utilizes Spring's `WebClient` to carry out http requests to the backend from within the Swing application. We use Spring to handle singleton global objects through Beans.

## Backend
The backend is created through SpringBoot, dependencies for Web APIs are included.

### File Structure
 ┣ **controller** *exposes application functionality as RESTful web services* \
 ┣ **model** *defines the CRC models* \
 ┣ **repository** *abstracts persistence CRUD operations* \
 ┣ **service** *encapsulates business logic and centralizes data access* \
 ┗ **App.java** *entrypoint to the application*

### Setup
Java Version: 18 \
Simply clone the repository and run through the entrypoint (App.java). Sample files are included for the User model.

## Frontend
The frontend is created through SpringBoot with a modified entrypoint to run the Java Swing application. There will be two `JFrame` for the authentication window and the main window. Once logged in, the auth frame will close and everything further lies within the main window. Different pages of the application are created with `JPanel`

### File Structure
 ┣ components \
 ┣ events \
 ┣ frames \
 ┃ ┣ AuthFrame.java \
 ┃ ┗ MainFrame.java \
 ┣ util \
 ┃ ┗ Cookies.java \
 ┣ views \
 ┣ App.java \
 ┗ AppConfig.java

### Setup
Java Version: 18 \
Simply clone the repository and run through the entrypoint (App.java). 

