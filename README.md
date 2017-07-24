# bartender-api-example
An example project to demonstrate api specs in various formats and test api validation tools. Also, this project doubles
as a functional library showcase for Kotlin.

# Overview
This project consists of a contrived Bartender example. The backend implementation is in Kotlin with the help of Jooby.
The implementation should honor the api specs which currently are written in api blueprint, raml and swagger. Dredd is
used for api spec validation against the implementation. Currently, there is no api validation tool in place for the
raml spec.

Credit is due to https://github.com/vjames19/kotlin-microservice-example as some useful extensions/helpers were used
from this project. Also, https://github.com/vjames19/kotlin-futures was used to make working with futures easier.

# Run
```
./gradlew funktionale-jooby-api:run
```

# Test
```
./gradlew test
```

# API Validation
One can manually validate the api spec by changing into the spec directory and calling dredd like below.

```
$ cd swagger
$ dredd
```

## API-Blueprint
```
./gradlew api-blueprint:validateApi
```

## Swagger
```
./gradlew swagger:validateApi
```
