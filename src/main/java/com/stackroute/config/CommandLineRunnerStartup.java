package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

//import java.util.logging.Logger;

@Component

public class CommandLineRunnerStartup implements CommandLineRunner {
    TrackRepository trackRepository;

    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerStartup.class);

    @Autowired
    public CommandLineRunnerStartup (TrackRepository trackRepository)
    {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args)
    {
        log.info("Here we will enter the initial data to our database");
        trackRepository.save(new Track(2,"hum tum","Alka Yagnik"));
        log.info("Seed values feeded in the databse");
    }
}
