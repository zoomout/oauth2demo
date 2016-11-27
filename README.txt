#This project demonstrates OAuth2.0 authorization.

#Demo consists of 3 modules:
 oauthclient   - client which fetches resource
 oauthauth     - authorization server
 oauthresource - protected resource

#To build project:
 mvn clean install

#To start application:
 java -jar oauthclient/target/oauthclient-0.0.1-SNAPSHOT.jar
 java -jar oauthauth/target/oauthauth-0.0.1-SNAPSHOT.jar
 java -jar oauthresource/target/oauthresource-0.0.1-SNAPSHOT.jar


#Open website (oauthclient):
 http://localhost:8080

#To get public info:
 Public info -> Get public resource

#To get OAuth2.0 protected resource:
 Click on 'Get Auth code' (user:password)
 Click on 'Get Access token'
 Click on 'Get Entity resource'