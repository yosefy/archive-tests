FROM ubuntu:16.04
COPY . /archive-tests/
WORKDIR /archive-tests
RUN apt-get update && apt-get install wget maven maven default-jdk -y
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
RUN apt-get update && apt-get install google-chrome-stable -y
CMD mvn clean test
