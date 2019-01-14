package com.stackroute.repository;

import com.stackroute.domain.Track;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {
    @Query
     Track findByName(String name);

     Track findById(int id);
}
