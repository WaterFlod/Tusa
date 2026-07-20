package com.neos.tusa;

import com.neos.tusa.dto.AddBillRequest;
import com.neos.tusa.dto.PartyCreateRequest;
import com.neos.tusa.dto.UserCreateRequest;
import com.neos.tusa.model.Party;
import com.neos.tusa.model.User;
import com.neos.tusa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartyService partyService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TusaBot bot;

    private User testUser;
    private Party testParty;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setName("Test User");
        testUser.setTelegramId("telegram");
        userRepository.save(testUser);

        PartyCreateRequest partyRequest = new PartyCreateRequest("Test Party", testUser.getId());
        testParty = partyService.createParty(partyRequest);

        doNothing().when(bot).sendMessage(anyString(), anyString());
    }

    @Test
    void createUser_ShouldPersistAndReturnUser() throws Exception {
        UserCreateRequest request = new UserCreateRequest("Integration User");

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Integration User"))
                .andExpect(jsonPath("$.telegramId").value("telegram"));
    }

    @Test
    void createParty_ShouldUseRealServiceAndReturn() throws Exception {
        PartyCreateRequest request = new PartyCreateRequest("New Party", testUser.getId());

        mockMvc.perform(post("/api/parties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Party"))
                .andExpect(jsonPath("$.adminUserId").value(testUser.getId()));
    }
}
