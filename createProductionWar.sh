#!/bin/bash

echo target file: $1

cd target
mkdir extract 
cd extract 
jar xvf ../*.war
rm -rf ../*.war 
cp ~/.ssh/application.properties WEB-INF/classes/application.properties
jar cvf ../../$1 . 
