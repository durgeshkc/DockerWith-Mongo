package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.CommentAlreadyThere;
import com.stackroute.exception.ListIsEmpty;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.exception.TrackPresentAlready;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackDBService implements TrackService {


    private TrackRepository trackRepository;
    @Autowired
    public TrackDBService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackPresentAlready {
        if(trackRepository.existsById(track.getId())){
            throw new TrackPresentAlready("already present");
        }
        else
        return trackRepository.save(track);
    }

    @Override
    public List<Track> getAllTrack() throws ListIsEmpty {

        //List<Track> trackList = (List<Track>) trackRepository.findAll();
        if(!((List<Track>) trackRepository.findAll()).isEmpty())
        return (List<Track>)trackRepository.findAll();
        else
            throw new ListIsEmpty("Playlist is Empty");
    }

    @Override
    public void deleteTrack(int id) throws TrackNotFoundException {
        if(!trackRepository.existsById(id))
        {
            throw new TrackNotFoundException("Track not found");
            //return true;
        }
        else
           trackRepository.deleteById(id);
    }

    @Override
    public Track updateComment(int id, String comment)  throws CommentAlreadyThere {
        Optional<Track> track =  trackRepository.findById(id);
        //trackRepository.deleteById(track.getId());
        if(!track.get().getComment().equals(comment))
        {
            track.get().setComment(comment);
            return trackRepository.save(track.get());
        }
        else
            throw new CommentAlreadyThere("This Comment is already present");
    }


}
