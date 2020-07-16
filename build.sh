#!/usr/bin/env bash
rm -r dist
mvn clean package dependency:copy-dependencies -DincludeScope=runtime -DoutputDirectory=../dist
cp nemo-core/target/nemo-core-1.0-SNAPSHOT.jar dist/