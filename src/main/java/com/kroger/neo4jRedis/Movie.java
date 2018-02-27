package com.kroger.neo4jRedis;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.io.Serializable;

@NodeEntity
public class Movie implements Serializable {
    @GraphId
    private Long id;

    private String title;

    private int released;

    private String tagline;

    public Movie() {
    }

    public Movie(String title, int released) {

        this.title = title;
        this.released = released;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getReleased() {
        return released;
    }

    public String getTagline() {
        return tagline;
    }
}
