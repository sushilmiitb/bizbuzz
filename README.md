
# App - Instatrade

Instrade is a webapp that lets traders organize product catalog, share it with their customers and do product oriented chatting. 

This repository contains following modules:-

* Registration and Login
* Product catalog management
* Customer management
* Catalog permission management
* Normal chat
* Product oriented chat

## Technologies Used

* Backend: Java, Spring, Hibernate
* Database: Mysql
* Frontend: JSP with tiles for templating
* UI Framework: Bootstrap
* Sockets: Atmosphere - for chat functionality
* Image Handling: imgscalr
* Mobile app: Cordova
* Server: Tomcat


## Getting Started

### Prerequisites


* install maven 2+ ```sudo apt-get install maven```
* install java 6+
* install git
* install mysql 5.5+ ```sudo apt-get install mysql-server```
* install eclipse jee
* [install tomcat 7+](https://www.tecmint.com/install-apache-tomcat-in-centos/)

### Running instruction

1. Checkout project using 
```git clone https://{username}@bitbucket.org/p12sushilkm/bizbuzz.git```

2. Create new schema in mysql
```create schema bizbuzz;```

3. Modify user.properties for your environment

4. Modify db.properties to change username/password for your mysql (don't commit). 
5. Also, set hibernate.hbm2ddl.auto=validate (if you don't want to recreate the tables in database from model of  MVC) or create (if you want to recreate tables. This will delete existing data and create new one. You have to set it to create for the first time).

6. Build project
```mvn clean package```
code will get deployed to your tomcat webapps

In TomcatHomeFolder/conf/server.xml add Context node in Host Node in order to make bizbuzz root app following line:

      <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">

        <!-- SingleSignOn valve, share authentication between web applications
             Documentation at: /docs/config/valve.html -->
        <!--
        <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
        -->

        <!-- Access log processes all example.
             Documentation at: /docs/config/valve.html
             Note: The pattern used is equivalent to using pattern="common" -->
        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log." suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />
		<Context path="" docBase="bizbuzz" reloadable="true">
		</Context>

      </Host>

You will have to run bizbuzz / src / test / resources / sql / test-data.sql in order to make register/personregistration form workable.

6. Code will get deployed to your tomcat webapps. [access on browser](http://localhost:8080)

## License

This project is licensed under the MIT License

## Authors
* Sushil Kumar - [Github](https://github.com/sushilmiitb)
* Chirag Patel -[Linkedin](https://www.linkedin.com/in/chirag-patel-706aa765/)
