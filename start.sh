#!/bin/bash

# Pull new changes
git pull

# Prepare Jar
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export JDA_TOKEN=$1
export BOT_DB_USERNAME='prod_ddbs_db_user'
export BOT_DB_PASSWORD='QWqwenolnqqpsdv234brt'

# Start new deployment
docker-compose up --build -d