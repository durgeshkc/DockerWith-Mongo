package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.CommentAlreadyThere;
import com.stackroute.exception.ListIsEmpty;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.exception.TrackPresentAlready;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//Use an interface that can be implemented by UserService and UserAWSService
@RequestMapping("api/v1/")
public class TrackController {


    private TrackService trackService;
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }
//for adding a track::::::::::::::::::::::::::::::::::::::::::::::
    @PostMapping(value = "track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        try {
            Track track1 = trackService.saveTrack(track);
            return new ResponseEntity<Track>(track1, HttpStatus.OK);
        }catch (TrackPresentAlready ex) {
            return new ResponseEntity<String> (ex.getMessage(),HttpStatus.CONFLICT);
        }
    }
//for viewing list of all tracks::::::::::::::::::::::::::::::::::::::::::::::::
    @GetMapping(value = "tracks")
    public ResponseEntity<?> listOfTrack() {
        try
        {
            List<Track> allTrack = trackService.getAllTrack();
            return new ResponseEntity<List<Track>>(allTrack, HttpStatus.OK);
        }catch(ListIsEmpty e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

// for deleting a track::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id )
    {
        try
        {
            trackService.deleteTrack(id);
            return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
        }
        catch (TrackNotFoundException e)
        {
            return new ResponseEntity<String >(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
//for updating comments :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @PostMapping(value = "update/{comment}/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") String id,@PathVariable("comment") String comment)
    {
        try
        {
            return new ResponseEntity<Track>(trackService.updateComment(Integer.parseInt(id),comment),HttpStatus.OK);
        }catch (CommentAlreadyThere e)
        {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
