# Dockerfile springboot2

Running docker build via maven command:

```bash
mvn clean package dockerfile:build
```

## Running springboot inside docker container

```docker
docker run -t -p 8080:8080 image-name:tag-name
```
