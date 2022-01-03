package com.g3code.jukebox.services;

import com.g3code.jukebox.models.Jukebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class JukeBoxDataService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${external.jukebox.api}")
    String jukeBoxAPIPath;

    Logger logger = LoggerFactory.getLogger(JukeBoxDataService.class);

    @Cacheable("jukeboxes")
    public List<Jukebox> getJukeBoxesFromExternalAPI(){
        ResponseEntity<Jukebox[]> responseEntity = restTemplate.getForEntity(jukeBoxAPIPath, Jukebox[].class);
        List<Jukebox> jukeboxes = Arrays.asList(responseEntity.getBody());
        logger.info("Imported juke box data from external API. ");
        return jukeboxes;
    }
}
