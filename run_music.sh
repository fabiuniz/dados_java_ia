#!/bin/bash

# Navigate to the project directory
cd GeneroMusical/

# Force a clean installation and update of dependencies
mvn clean install -U

# Execute the JAR file
java -jar target/MusicGenreClassifier-1.0-SNAPSHOT.jar