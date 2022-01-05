package com.g3code.jukebox.services;

import com.g3code.jukebox.models.Jukebox;
import com.g3code.jukebox.models.NameComponent;
import com.g3code.jukebox.models.Setting;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.Name;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class JukeboxSearchServiceTest {

    @InjectMocks
    JukeboxSearchService jukeboxSearchService;

    @Mock
    JukeBoxDataService jukeBoxDataService;

    @Mock
    SettingsDataService settingsDataService;

    @BeforeEach
    public void setup(){
        NameComponent pcb = new NameComponent("pcb");
        NameComponent amplifier = new NameComponent("amplifier");
        NameComponent speaker = new NameComponent("speaker");

        Mockito.when(jukeBoxDataService.getJukeBoxesFromExternalAPI())
                .thenReturn(
                        Arrays.asList(
                                new Jukebox("123", Arrays.asList(pcb, amplifier, speaker),"PAS")
                                ,new Jukebox("234", Arrays.asList(amplifier, speaker),"AS")
                        )
                );
    }

    @Test
    void containsAllRequiredComponents() {
        String settingId = "b43f247a-8478-4f24-8e28-792fcfe539f5";

        Mockito.when(settingsDataService.getComponentsForSettingByID(any())).thenReturn(Arrays.asList("pcb"));

        assertEquals("PAS"
                , jukeboxSearchService.performSearch(settingId,"", 0, 100).get(0).getModel());
    }

    @Test
    public void testCountWhenMultipleMatch(){
        String settingId = "b43f247a-8478-4f24-8e28-792fcfe539f5";
        Mockito.when(settingsDataService.getComponentsForSettingByID(any())).thenReturn(Arrays.asList("amplifier"));

        assertEquals(2
                , jukeboxSearchService.performSearch(settingId,"", 0, 100).size());
    }
}
