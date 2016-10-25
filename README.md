# INLIFE_MatchMaker_Restful_WebService
A Maven project that generates RESTful web-services of the IN LIFE MatchMaker.

To build the IN LIFE Matchmaker Restful web-service:

1) Download [JDK - version 7u67] (http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) or later and install it

2) Download [Apache Maven] (http://maven.apache.org/) and install it

3) Run "mvn clean install" in the root directory of the source code

4) Deploy the generated .war to your [Apache Tomcat server](http://tomcat.apache.org/)

### Usage example using  [curl](http://curl.haxx.se/):

curl -X POST -H "Content-Type: application/json" http://localhost:8080/INLIFE_MatchMaker_Restful_WebService/INLIFE/{serviceName} -d @{input.json}

### Funding Acknowledgement

The research leading to these results has received funding from the EU Framework Programme for Research and Innovation Horizon 2020 under grant agreement No.643442 IN LIFE (INdependent LIving support Functions for the Elderly).
