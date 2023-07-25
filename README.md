# Flerchlympics team app
This generates teams based on number of teams desired, or number of people on the teams.

Example queries

http://localhost:8080/teams?numTeams=2&skippy=Debbie
Generates two teams, but excludes Debbie

Generates teams of size three, but excludes Jon
http://localhost:8080/teams?teamSize=3&skippy=Jon

All request params are optional, but nothing will happen without `teamSize` or `numTeams`

## Build and run
The following need a jdk installed
`./gradlew build` builds local
`./gradlew bootRun` runs local

This can probably get it running with just docker.
`docker run --publish 8080:8080 -t $(docker build . -q)` builds and runs with docker.
