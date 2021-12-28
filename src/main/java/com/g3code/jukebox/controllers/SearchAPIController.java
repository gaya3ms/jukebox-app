package com.g3code.jukebox.controllers;

import com.g3code.jukebox.models.APIResponseWrapper;
import com.g3code.jukebox.models.Jukebox;
import com.g3code.jukebox.models.Setting;
import com.g3code.jukebox.models.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchAPIController{

    @Autowired
    RestTemplate restTemplate;

    @Value("${external.jukeboxapi}")
    String JUKEBOXAPIPATH;

    @Value("${external.jukeboxsettingapi}")
    String JUKEBOXSETTINGSAPIPATH;

    @GetMapping("/{id}")
    public APIResponseWrapper performSearch(@PathVariable("id") String id){
        // Get Data
        ResponseEntity<Jukebox[]> jukeResponse = restTemplate.getForEntity(JUKEBOXAPIPATH, Jukebox[].class);
        ResponseEntity<Settings> settingResponse = restTemplate.getForEntity(JUKEBOXSETTINGSAPIPATH, Settings.class);

        // Put it into a faster data structure
        HashMap<String, String[]> settingsMap = new HashMap<>();
        Arrays.stream(settingResponse.getBody().getSettings()).forEach(setting -> {
            settingsMap.put(setting.getId(), setting.getRequires());
        });

        // Find the matching jukeboxes
//        Jukebox[] jukeboxes = jukeResponse.getBody().toArray(String[]::new);
//        String[] requiredComponents = settingsMap.get(id);
//        Jukebox[] filteredJukeBoxes = jukeboxes.

        return new APIResponseWrapper(id);
    }
}
