# spring-neo4j-redis
Example repo with neo4j repository and redis cache

Caching is optional, call will hit repository if redis is down. Caching TTL is 50s can be changed as per configuration file

# To Run
- Start redis, 
- Start neo4j 
- Run movie database
- ./gradlew bootRun
