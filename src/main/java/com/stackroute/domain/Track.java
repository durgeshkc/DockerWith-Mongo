package com.stackroute.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.*;
import java.util.Collections;

@Document(collection = "Track")
public class Track {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    //@Column
    private String name;
    //@Column
    private String comment;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
