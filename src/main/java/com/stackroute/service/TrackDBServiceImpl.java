package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.SameCommentExists;
import com.stackroute.exception.TracksNotAvailable;
import com.stackroute.exception.TrackNotFound;
import com.stackroute.exception.TrackAlreadyPresent;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackDBServiceImpl implements TrackServiceImpl {


    private TrackRepository trackRepository;
    @Autowired
    public TrackDBServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyPresent {
        if(trackRepository.existsById(track.getId())){
            throw new TrackAlreadyPresent("already present");
        }
        else
        return trackRepository.save(track);
    }

    @Override
    public List<Track> getAllTracks() throws TracksNotAvailable {

        //List<Track> trackList = (List<Track>) trackRepository.findAll();
        if(!((List<Track>) trackRepository.findAll()).isEmpty())
        return (List<Track>)trackRepository.findAll();
        else
            throw new TracksNotAvailable("Playlist is Empty");
    }

    @Override
    public Track deleteTrack(int id) throws TrackNotFound {
        if(!trackRepository.existsById(id))
        {
            throw new TrackNotFound("Track not found");
            //return true;
        }
        else
        {
            Track track1 =  trackRepository.findById(id);
            trackRepository.deleteById(id);
            return track1;
        }
    }

//    @Override
//    public Track updateTrack(int id, String comment)  throws SameCommentExists {
//        Optional<Track> track =  trackRepository.findById(id);
//        //trackRepository.deleteById(track.getId());
//        if(!track.get().getComment().equals(comment))
//        {
//            track.get().setComment(comment);
//            return trackRepository.save(track.get());
//        }
//        else
//            throw new SameCommentExists("This Comment is already present");
//    }
//
    @Override
    public Track updateTrack(int id, String comment)  throws SameCommentExists {
        Track track1 =  trackRepository.findById(id);
        //trackRepository.deleteById(track.getId());
        if(!track1.getComment().equals(comment))
        {
            track1.setComment(comment);
            return trackRepository.save(track1);
        }
        else
            throw new SameCommentExists("This Comment is already present");
    }


}
