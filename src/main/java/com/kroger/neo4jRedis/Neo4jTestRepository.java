package com.kroger.neo4jRedis;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
interface Neo4jTestRepository extends GraphRepository<Movie> {

    @Query("MATCH(m:Movie {title: {title}}) return m")
    Movie getMoviesTitle(@Param("title") String title);
}
