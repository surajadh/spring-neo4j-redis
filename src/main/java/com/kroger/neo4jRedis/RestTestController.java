package com.kroger.neo4jRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
public class RestTestController {

    @Autowired
    Neo4jTestRepository testRepository;

    @Autowired
    RedisCacheManager redisCacheManager;

    @RequestMapping("/getAllMovies")
    public Movie getAllMovies(@RequestParam("title") String title, @RequestParam("session") String session) {
        boolean skipRedis = false;
        try {
            Cache.ValueWrapper cache = redisCacheManager.getCache("movies").get(title);
            if(cache != null) {
                return (Movie) cache.get();
            }
        }
        catch (Exception e) {
            //Log connection or whatever exception
            skipRedis = true;
        }

        Movie result = testRepository.getMoviesTitle(title);

        if(!skipRedis) {
            redisCacheManager.setCacheNames(Arrays.asList("movies"));
            redisCacheManager.getCache("movies").put(title, result);
        }

        return result;
    }
}
