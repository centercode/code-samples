#!/bin/bash
#
# Reference: https://maven.apache.org/archetypes/index.html
mvn archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-simple \
  -DarchetypeVersion=1.4

mvn archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-archetype \
  -DarchetypeVersion=1.4

mvn archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-plugin \
  -DarchetypeVersion=1.4

mvn archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4

mvn archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-webapp \
  -DarchetypeVersion=1.4

mvn archetype:generate \
  -DarchetypeGroupId=pl.codeleak \
  -DarchetypeArtifactId=spring-mvc-quickstart \
  -DarchetypeVersion=5.0.1 \
  -DgroupId=my.groupid \
  -DartifactId=my-artifactId \
  -Dversion=version \
  -DarchetypeRepository=http://kolorobot.github.io/spring-mvc-quickstart-archetype
