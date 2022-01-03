package com.g3code.jukebox.services;

import com.g3code.jukebox.models.Setting;
import com.g3code.jukebox.models.SettingsWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Component
public class SettingsDataService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${external.setting.api}")
    String settingAPIPath;

    Logger logger = LoggerFactory.getLogger(SettingsDataService.class);

    public List<String> getComponentsForSettingByID(String id){
        HashMap<String, List<String>> settings_map = getSettingsMap();
        List<String> components = settings_map.get(id);
        return components;
    }

    private HashMap<String, List<String>> getSettingsMap(){
        List<Setting> settings = getSettingsFromExternalAPI();
        HashMap<String, List<String>> settings_data = new HashMap<>();
        settings.forEach(setting -> settings_data.put(setting.getId(), setting.getRequires()));
        return settings_data;
    }

    public List<Setting> getSettingsFromExternalAPI(){
        ResponseEntity<SettingsWrapper> responseEntity = restTemplate.getForEntity(settingAPIPath,SettingsWrapper.class);
        List<Setting> settings = responseEntity.getBody().getSettings();
        logger.info("Imported settings data from external API. ");
        return settings;
    }
}
