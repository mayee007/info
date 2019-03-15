FROM tomcat:8-jre8  
MAINTAINER mahesh

RUN echo "export JAVA_OPTS=\"-Dspring.profiles.active=prod\"" > /usr/local/tomcat/bin/setenv.sh  
RUN rm -rf  /usr/local/tomcat/webapps/info.war
RUN rm -rf  /usr/local/tomcat/webapps/info
RUN rm -rf  /usr/local/tomcat/webapps/ROOT
COPY ./info.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]  
