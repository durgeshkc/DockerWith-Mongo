package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.SameCommentExists;
import com.stackroute.exception.TracksNotAvailable;
import com.stackroute.exception.TrackNotFound;
import com.stackroute.exception.TrackAlreadyPresent;

import java.util.List;

public interface TrackServiceImpl {
    public Track saveTrack(Track track) throws TrackAlreadyPresent;
    public List<Track> getAllTracks() throws TracksNotAvailable;
    public void deleteTrack(int id) throws TrackNotFound;
   // public Track updateTrack(int id, String comment) throws SameCommentExists;
   public Track updateTrack(int id,String comment) throws SameCommentExists;
}
