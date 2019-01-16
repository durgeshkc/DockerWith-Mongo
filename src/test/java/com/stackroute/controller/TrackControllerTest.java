package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.service.TrackServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=TrackController.class)
public class TrackControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrackServiceImpl trackService;

    @InjectMocks
    private TrackController trackController;

    @Before
    public void setUp() {

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void saveTrack() throws Exception {

        Track track = new Track();
        track.setName("firstTrack1");
        track.setComment("firstComment1");

        Mockito.when(trackService.saveTrack(track)).thenReturn(track);

        mvc.perform(post("/api/v2/track")
                .content(asJsonString(track))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(trackService, times(1)).saveTrack(Mockito.any(Track.class));
    }

    @Test
    public void listOfTracks() throws Exception {
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

        Mockito.when(trackService.getAllTracks()).thenReturn(playlist);

        mvc.perform(get("/api/v2/tracks"))
            .andExpect(status().isOk());

        verify(trackService,times(1)).getAllTracks();

    }
    @Test
    public void getTrackByName() throws Exception{
        Track track =  new Track();
        //String name = "SearchTrack";
        track.setName("SearchTrack");
        track.setId(200);
        track.setComment("Comment");

        Mockito.when(trackService.findByName(track.getName())).thenReturn(track);

        mvc.perform(get("/api/v2/track/{name}","SearchTrack"))
            .andExpect(status().isOk());
        verify(trackService,times(1)).findByName(track.getName());

    }

    @Test
    public void deleteTrack() throws Exception{
        Track track = new Track();
        track.setName("DeleteTrack");
        track.setId(300);
        track.setComment("DeleteComment");

        Mockito.when(trackService.deleteTrack(track.getId())).thenReturn(track);

        mvc.perform(delete("/api/v2/delete/{id}", 300))
                .andExpect(status().isOk());
        verify(trackService,times(1)).deleteTrack(track.getId());
    }

    @Test
    public void updateTrack() throws Exception
    {
        Track track = new Track();
        track.setName("UpdateTrack");
        track.setId(400);
        track.setComment("Updatedcomment");

        Mockito.when(trackService.updateTrack(track.getId(),track.getComment())).thenReturn(track);

        mvc.perform(put("/api/v2/update/{id}",400)
            .content(track.getComment())
            .contentType(MediaType.ALL_VALUE))
            .andExpect(status().isOk());

        verify(trackService,times(1)).updateTrack(track.getId(),track.getComment());
    }

}