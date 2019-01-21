# Dockerfile springboot2

Running docker build via maven command:

```bash
mvn clean package dockerfile:build
```

## Running springboot inside docker container

```docker
docker run -t -p 8080:8080 image-name:tag-name
```

## Configuration

pom.xml

```xml
<project ...>

    <build>
        <finalName>${project.artifactId}-v${project.version}</finalName>
        <plugins>
            
            <!-- plugin springboot -->
            
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>unpack</id>
                        <phase>package</phase>
                        <goals>
                            <goal>unpack</goal>
                        </goals>
                        <configuration>
                            <artifactItems>
                                <artifactItem>
                                    <groupId>${project.groupId}</groupId>
                                    <artifactId>${project.artifactId}</artifactId>
                                    <version>${project.version}</version>
                                </artifactItem>
                            </artifactItems>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>1.4.9</version>
                <configuration>
                    <!-- dockerfile configuration -->
                    <buildArgs>
                        <JAR_FILE>${project.build.finalName}.jar</JAR_FILE>
                    </buildArgs>
                    <tag>${project.version}</tag>
                    <repository>${project.groupId}/${project.artifactId}</repository>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

Dockerfile

```dockerfile
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```