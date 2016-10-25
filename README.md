# INLIFE_MatchMaker_Restful_WebService
A Maven project that generates RESTful web-services of the IN LIFE MatchMaker.

To build the IN LIFE Matchmaker Restful web-service:

1) Download JDK - version 7u67 or later and install it

2) Download Apache Maven and install it

3) Run "mvn clean install" in the root directory of the source code

4) Deploy the generated .war to your Apache Tomcat server

Usage example using curl:

curl -X POST -H "Content-Type: application/json" http://localhost:8080/INLIFE_Matchmaker_Restful_WebService/INLIFE/{serviceName} -d @{input.json}

Funding Acknowledgement

The research leading to these results has received funding from the EU Framework Programme for Research and Innovation Horizon 2020 under grant agreement No.643442 IN LIFE (INdependent LIving support Functions for the Elderly).
