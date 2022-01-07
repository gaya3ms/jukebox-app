package com.g3code.jukebox.services;

import com.g3code.jukebox.models.Jukebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JukeboxSearchService {

    @Autowired
    JukeBoxDataService jukeBoxDataService;

    @Autowired
    SettingsDataService settingsDataService;

    @Value("${settings.default_limit}")
    Integer defaultLimit;

    @Value("${settings.default_offset}")
    Integer defaultOffset;

    public List<Jukebox> performSearch(String setting_id, String model_name, Integer offset, Integer limit){
        List<Jukebox> jukeboxes = performSearchBySettingID(setting_id);
        if(jukeboxes.size() == 0){
           throw new SettingIdNotFound("No matching jukebox found with setting ID: " + setting_id +". Try another one");
        }
        if ( model_name != null && model_name.trim().length() > 0 ) {
            jukeboxes = performSearchByName(jukeboxes, model_name);
        }
        return paginateResults(jukeboxes, offset, limit);
    }

    private List<Jukebox> paginateResults(List<Jukebox> jukeboxes, Integer offset, Integer limit) {
        if (offset == null) offset = defaultOffset;
        if (limit == null) limit = defaultLimit;
        if((limit + offset) > jukeboxes.size()) limit = jukeboxes.size();

        return jukeboxes.subList(offset, limit);
    }

    public List<Jukebox> performSearchBySettingID(String setting_id){
        List<Jukebox> jukeboxes = jukeBoxDataService.getJukeBoxesFromExternalAPI();
        List<String> required_components = settingsDataService.getComponentsForSettingByID(setting_id);
        return jukeboxes.stream().filter(jbox -> containsAllRequiredComponents(jbox.getComponentList(), required_components))
                                    .collect(Collectors.toList());
    }

    public List<Jukebox> performSearchByName(List<Jukebox> jukeboxes, String model_name){
        return jukeboxes.stream().filter(jbox -> jbox.getModel().equals(model_name)).collect(Collectors.toList());
    }

    public boolean containsAllRequiredComponents(List<String> componentsPresent, List<String> requiredComponents){
        return componentsPresent.containsAll(requiredComponents);
    }

}
