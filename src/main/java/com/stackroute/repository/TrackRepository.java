package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends CrudRepository<Track, Integer> {
    @Query
    public Track findByName(String name);
}
