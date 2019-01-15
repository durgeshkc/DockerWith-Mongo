package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@PropertySource("startupApplication.properties")
public class ApplicationListenerStartup implements ApplicationListener<ContextRefreshedEvent> {

    TrackRepository trackRepository;
   // private static final Logger log = LoggerFactory.getLogger(ApplicationListenerStartup.class);
   private static final Logger log = LoggerFactory.getLogger(ApplicationListenerStartup.class);

    @Value("${id1}")
    private int idOne;
    @Value("${name1}")
    private String nameOne;
    @Value("${comment1}")
    private String commentOne;


    public ApplicationListenerStartup(TrackRepository trackRepository)
    {
        this.trackRepository  = trackRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        log.info("Here we will enter the initial data to our database");

        Track Track1= new Track();
        Track1.setId(idOne);
        Track1.setName(nameOne);
        Track1.setComment(commentOne);
        trackRepository.save(Track1);

        log.info("Seed values feeded in the databse");
    }
}

