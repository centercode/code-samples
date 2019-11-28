# Maven插件常用配置

## Maven-assembly-plugin

### `single`目标

使用自定义的描述文件：

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-assembly-plugin</artifactId>
  <version>3.1.0</version>
  <configuration>
    <finalName>${project.artifactId}</finalName>
    <descriptors>
      <descriptor>${assembly.descriptor}</descriptor>
    </descriptors>
  </configuration>
</plugin>
```

引用内置描述文件（旧版本`assembly`目标）：

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-assembly-plugin</artifactId>
  <version>2.4.1</version>
  <configuration>
    <!--打包文件名去掉描述符后缀-->
    <appendAssemblyId>false</appendAssemblyId>
    <descriptorRefs>
      <!--连同依赖打成一个uber jar-->
      <descriptorRef>jar-with-dependencies</descriptorRef>
    </descriptorRefs>
    <archive>
      <manifest>
        <!--可以java -jar方式执行-->
        <mainClass>org.sample.App</mainClass>
      </manifest>
    </archive>
  </configuration>
</plugin>
```

参考：

- [描述符格式](http://maven.apache.org/plugins/maven-assembly-plugin/assembly.html)
- [内置描述文件](http://maven.apache.org/plugins/maven-assembly-plugin/descriptor-refs.html#)