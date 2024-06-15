# P6-Full-Stack-reseau-dev

# MDD (Monde de Dev) 

This project is the MVP version of MDD, a social network application aiming at software developers. This version does not implement all featurs yet. So far, users can register and login to view their feed of articles related to themes that they subscribe to, they can also comment on articles, write new articles, subscribe or unsubscribe to new themes.

## Technology stack

### Front 
- Angular
- NgRx
- Angular Material
  
### Back

- Spring
- Spring Boot
- Lombok


### 1. Installation

### Front

First install the dependencies by running `npm install` then the Angular application will be available at `localhost:4200`.



### Back

Run `mvn clean install` to build the project.
Start the Spring Boot application by running `mvn spring-boot:run` or using your IDE.
The backend will be accessible at `localhost:8080`.

### 2. Database

MDD uses a MySQL database. You'll need to have a MySQL server running and accessible.
Follow the `application.properties.example` file to setup your own `application.properties`file.
Update the application.properties file in the src/main/resources directory with your database credentials `(DB_NAME, DB_USERNAME, DB_PASSWORD)`.

### 3. License
This project is licensed under the MIT License.

Let me know if you'd like any further adjustments or additions to the README!

