package jian.he.controllers;

import jian.he.model.Owner;
import jian.he.model.Pet;
import jian.he.model.PetType;
import jian.he.services.PetService;
import jian.he.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;
    private final Map<String, String> urlVariable = new HashMap<>();
    private URI visitsUri;
    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");

    @BeforeEach
    void setUp() {
        Long petId = 1L;
        Long ownerId = 1L;
        when(petService.findById(anyLong())).thenReturn(
                Pet.builder()
                        .id(petId)
                        .name("Cutie")
                        .birthDate(LocalDate.of(2018,11,13))
                        .visits(new HashSet<>())
                        .owner(Owner.builder()
                                .id(ownerId)
                                .lastName("Doe")
                                .firstName("Joe")
                                .build())
                        .petType(PetType.builder()
                                .name("Dog")
                                .build())
                        .build()
        );
        urlVariable.clear();
        urlVariable.put("ownerId", ownerId.toString());
        urlVariable.put("petId",petId.toString());
        visitsUri = visitsUriTemplate.expand(urlVariable);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

    }



    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get(visitsUri))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post(visitsUri)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("date","2018-11-11")
                    .param("description",YET_ANOTHER_VISIT_DESCRIPTION))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS_1))
                .andExpect(model().attributeExists("visit"));
    }
}