FROM tomcat:8.5.100-jdk17
# Install ant
ENV ANT_VERSION=1.7.1
RUN wget http://archive.apache.org/dist/ant/binaries/apache-ant-${ANT_VERSION}-bin.tar.gz \
    && tar xvfvz apache-ant-${ANT_VERSION}-bin.tar.gz -C /usr/local \
    && ln -sfn /usr/local/apache-ant-${ANT_VERSION} /usr/local/ant \
    && sh -c 'echo ANT_HOME=/usr/local/ant >> /etc/environment' \
    && ln -sfn /usr/local/ant/bin/ant /usr/bin/ant \
    && rm apache-ant-${ANT_VERSION}-bin.tar.gz
WORKDIR /app
# Copy the project
COPY . .
# Build the project
RUN ant dist -Dj2ee.server.home=/usr/local/tomcat 
# Copy the war file to the tomcat webapps folder
RUN cp ./dist/WorkStationAlpha.war /usr/local/tomcat/webapps/ws.war
# Run the tomcat server
CMD ["catalina.sh", "run"]

