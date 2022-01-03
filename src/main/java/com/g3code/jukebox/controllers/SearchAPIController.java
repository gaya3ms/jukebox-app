package com.g3code.jukebox.controllers;

import com.g3code.jukebox.models.Jukebox;
import com.g3code.jukebox.services.JukeboxSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchAPIController {

    @Autowired
    JukeboxSearchService jukeboxSearchService;

    @RequestMapping(value = "/api/search", method = RequestMethod.GET)
    public List<Jukebox> getJukeBoxesWithSettingID(@RequestParam String settingId
                                                   ,@RequestParam(required = false) String model
                                                   ,@RequestParam(required = false) Integer offset
                                                    ,@RequestParam(required = false) Integer limit){
        return jukeboxSearchService.performSearch(settingId, model, offset, limit);
    }
}
