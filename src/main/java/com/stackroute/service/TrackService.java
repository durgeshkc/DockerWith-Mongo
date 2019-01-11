package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.CommentAlreadyThere;
import com.stackroute.exception.ListIsEmpty;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.exception.TrackPresentAlready;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackPresentAlready;
    public List<Track> getAllTrack() throws ListIsEmpty;
    public void deleteTrack(int id) throws TrackNotFoundException;
    public Track updateComment(int id, String comment) throws CommentAlreadyThere;
}
