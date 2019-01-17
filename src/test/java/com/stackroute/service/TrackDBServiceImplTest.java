package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrackDBServiceImplTest {
    @Mock
    TrackRepository trackRepository ;

    @InjectMocks
    TrackDBServiceImpl trackDBService;

    @Before
    public void setUp() throws Exception {
        Track track = new Track(1,"kaho na pyar hai","hrithik roshan");
    }


    @Test
    public void saveTrack() throws Exception {
        Track track = new Track(1,"kaho na pyar hai","hrithik roshan");
        when(trackRepository.save(track)).thenReturn(track);        //stubbing
        Track newTrack = trackDBService.saveTrack(track);
        assertEquals(track,newTrack);
        verify(trackRepository,times(1)).save(track);   // ensures whether a mock method is being called with required arguments or not.
    }

    @Test
    public void getAllTracks() throws Exception{
        Track track1 = new Track();
        List<Track> playlist = new ArrayList<>();
        track1.setName("Track1");
        track1.setComment("Comment1");
        track1.setId(100);
        playlist.add(track1);

        Track track2 = new Track();
        track2.setName("Track2");
        track2.setComment("Comment2");
        track2.setId(101);
        playlist.add(track2);

        Track track3 = new Track();
        track3.setName("Track3");
        track3.setComment("Comment3");
        track3.setId(103);
        playlist.add(track3);

        when(trackRepository.findAll()).thenReturn(playlist);

        List<Track> list = trackDBService.getAllTracks();

        assertEquals(list,playlist);
        verify(trackRepository,times(2)).findAll();

    }

//    @Test
//    public void deleteTrack() throws Exception{
//        Track track = new Track();
//        track.setName("Track3");
//        track.setComment("Comment3");
//        track.setId(103);
//        //trackRepository.save(track);
//
//        when(trackRepository.findById(track.getId())).thenReturn(track);
//
//        Track actual = trackDBService.deleteTrack(track.getId());
//
//        assertEquals(actual,track);
//        verify(trackRepository,times(1)).deleteById(track.getId());
//    }

    @Test
    public void updateTrack() {
    }

    @Test
    public void findByName() {
    }
}