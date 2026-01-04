#!/bin/sh

./gradlew :membership-service:jibDockerBuild
./gradlew :banking-service:jibDockerBuild
./gradlew :money-service:jibDockerBuild
./gradlew :remittance-service:jibDockerBuild

./gradlew :logging-consumer:jibDockerBuild
./gradlew :task-consumer:jibDockerBuild
