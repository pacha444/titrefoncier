package com.econs.titrefoncier.controller;

import com.econs.titrefoncier.service.impl.TitreFoncierServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest
public class TitreFoncierControllerTests {

    @Autowired
    private WebMvcTest webMvcTest;

    @Mock
    private TitreFoncierServiceImpl titreFoncierService;

    @Autowired
    private ObjectMapper objectMapper;

     //Junit test for
         @DisplayName("")
         @Test
         public void givenTitreFoncierObject_whenCreateTitreFoncier_thenReturnTitreFoncierEnregistrer()
         {
             //given-precondition or setup


             //when-action or the behaviour we are going test

             //then-verify the output


         }
}
