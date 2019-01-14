package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.SameCommentExists;
import com.stackroute.exception.TracksNotAvailable;
import com.stackroute.exception.TrackNotFound;
import com.stackroute.exception.TrackAlreadyPresent;
import com.stackroute.service.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//Use an interface that can be implemented by UserService and UserAWSService
@RequestMapping("api/v1/")
public class TrackController {


    private TrackServiceImpl trackServiceImpl;
    @Autowired
    public TrackController(TrackServiceImpl trackServiceImpl) {
        this.trackServiceImpl = trackServiceImpl;
    }
    public void setTrackServiceImpl(TrackServiceImpl trackServiceImpl) {
        this.trackServiceImpl = trackServiceImpl;
    }
//for adding a track::::::::::::::::::::::::::::::::::::::::::::::
    @PostMapping(value = "track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        try {
            Track track1 = trackServiceImpl.saveTrack(track);
            return new ResponseEntity<Track>(track1, HttpStatus.OK);
        }catch (TrackAlreadyPresent ex) {
            return new ResponseEntity<String> (ex.getMessage(),HttpStatus.CONFLICT);
        }
    }
//for viewing list of all tracks::::::::::::::::::::::::::::::::::::::::::::::::
    @GetMapping(value = "tracks")
    public ResponseEntity<?> listOfTracks() {
        try
        {
            List<Track> allTrack = trackServiceImpl.getAllTracks();
            return new ResponseEntity<List<Track>>(allTrack, HttpStatus.OK);
        }catch(TracksNotAvailable e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //for finding track by name.........
    @GetMapping(value = "track/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable("name") String name) {
        try
        {
            Track track = trackServiceImpl.findByName(name);
            return new ResponseEntity<Track>(track, HttpStatus.OK);
        }catch(TrackNotFound e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

// for deleting a track::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id )
    {
        try
        {
            Track trackDeleted = trackServiceImpl.deleteTrack(id);
           // return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
            return new ResponseEntity<Track>(trackDeleted,HttpStatus.OK);
        }
        catch (TrackNotFound e)
        {
            return new ResponseEntity<String >(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
//for updating comments :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//    @PostMapping(value = "update/{comment}/{id}")
//    public ResponseEntity<?> updateTrack(@PathVariable("id") String id, @PathVariable("comment") String comment)
//    {
//        try
//        {
//            return new ResponseEntity<Track>(trackServiceImpl.updateTrack(Integer.parseInt(id),comment),HttpStatus.OK);
//        }catch (SameCommentExists e)
//        {
//            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
//        }
//    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> updateTrack(@PathVariable("id") int id,@RequestBody String comment)
    {
        try
        {
            return new ResponseEntity<Track>(trackServiceImpl.updateTrack(id,comment),HttpStatus.OK);
        }catch (SameCommentExists e)
        {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
