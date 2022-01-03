package com.g3code.jukebox.services;

import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JukeBoxDataServiceTest {

    @Autowired
    JukeBoxDataService jukeBoxDataService;

    @Test
    void getJukeBoxesFromExternalAPI() {
        jukeBoxDataService.getJukeBoxesFromExternalAPI();
    }
}