#!/usr/bin/env bash
# First give the execute permission to current user to run gradlew
# chmod +x gradlew

RED='\033[0;31m'
GREEN='\033[0;32m'
JAR='api/build/libs/easy-blogger-0.0.1-SNAPSHOT.jar'
case "$1" in
    "-bd")
        echo "${GREEN}## Building with Gradle and deploying jar"
        ./gradlew clean build && java -jar ${JAR}
    ;;
    "-d")
        echo "${GREEN}## Deploying jar"
        java -jar ${JAR}
    ;;
    "--debug")
        echo "${GREEN}## Deploying jar"
        java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar ${JAR}
    ;;
    "-xtest")
        echo "${GREEN}## Building with Gradle and Skipping tests"
        ./gradlew clean build -x test
    ;;
    *)
        echo "${GREEN}## Building with Gradle"
        ./gradlew clean build
    ;;
esac