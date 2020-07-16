FROM openjdk:14-jdk-buster

COPY dist dist/
COPY nemo.sh nemo.sh

RUN chmod +x nemo.sh

VOLUME workspace
VOLUME tasks

ENTRYPOINT ["/nemo.sh"]