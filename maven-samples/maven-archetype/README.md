### Maven项目模板

#### maven-archetype-quickstart

查看所有版本：http://repo.maven.apache.org/maven2/org/apache/maven/archetypes/maven-archetype-quickstart/maven-metadata.xml

```bash
mvn archetype:generate -B\
 -DarchetypeGroupId=org.apache.maven.archetypes \
 -DarchetypeArtifactId=maven-archetype-quickstart \
 -DarchetypeVersion=1.4 \
 -DgroupId=io.github.centercode \
 -DartifactId=quickstart-demo \
 -Dversion=1.0.0 \
 -Dpackage=io.github.centercode
```

#### maven-archetype-simple

```bash
mvn archetype:generate -B\
 -DarchetypeGroupId=org.apache.maven.archetypes \
 -DarchetypeArtifactId=maven-archetype-simple \
 -DarchetypeVersion=1.4 \
 -DgroupId=io.github.centercode \
 -DartifactId=simple-demo \
 -Dversion=1.0.0 \
 -Dpackage=io.github.centercode
```

#### maven-archetype-webapp

```bash
mvn archetype:generate -B\
 -DarchetypeGroupId=org.apache.maven.archetypes \
 -DarchetypeArtifactId=maven-archetype-webapp \
 -DarchetypeVersion=1.4 \
 -DgroupId=io.github.centercode \
 -DartifactId=web-app-demo \
 -Dversion=1.0.0 \
 -Dpackage=io.github.centercode
```

#### cocoon-22-archetype-webapp

```shell
mvn archetype:generate\
 -DarchetypeGroupId=org.apache.cocoon \
 -DarchetypeArtifactId=cocoon-22-archetype-webapp \
 -DarchetypeVersion=1.0.0 \
 -DgroupId=io.github.centercode \
 -DartifactId=webapp-demo \
 -Dversion=1.0.0 \
 -Dpackage=io.github.centercode \
 -DinteractiveMode=false
```

### 参考

- [maven-archetype - Github](https://github.com/apache/maven-archetype)
- [Maven Archetype Plugin](http://maven.apache.org/archetype/maven-archetype-plugin/)
- [how to use the Archetype Plugin to create a project](http://maven.apache.org/archetype/maven-archetype-plugin/usage.html)
- [how to use the Archetype Plugin to create an archetype from an existing project](http://maven.apache.org/archetype/maven-archetype-plugin/advanced-usage.html)