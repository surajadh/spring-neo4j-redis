package com.kroger.neo4jRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static sun.security.timestamp.TSResponse.BAD_REQUEST;

@RestController
public class RestTestController {

    @Autowired
    Neo4jTestRepository testRepository;

    @Autowired
    RedisCacheManager redisCacheManager;

    @RequestMapping("/getAllMovies")
//    @Cacheable(value = "movies", key = "#title")
    public Movie getAllMovies(@RequestParam("title") String title, @RequestParam("session") String session) {
        boolean skipRedis = false;
        //Clear cache
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
//
//    @ExceptionHandler({ JedisConnectionException.class })
//    public void handleGetAllMoviesWhenRedisUnavailable(HttpServletResponse response, InvalidRecipeIdException e) throws IOException {
//
//    }


}
