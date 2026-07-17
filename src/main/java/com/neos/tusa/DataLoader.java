package com.neos.tusa;

import com.neos.tusa.model.Party;
import com.neos.tusa.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PartyRepository partyRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User user1 = createUser("Alex", "1");
            User user2 = createUser("Matt", "2");
            Party party = createParty("Birtday", user1, List.of(user1, user2));

            userRepository.saveAll(List.of(user1, user2));
            partyRepository.save(party);
        }
    }

    private User createUser(String name, String telegramId) {
        User user = new User();
        user.setName(name);
        user.setTelegramId(telegramId);
        return user;
    }

    private Party createParty(String name, User admin, List<User> members) {
        Party party = new Party();
        party.setName(name);
        party.setAdmin(admin);
        party.setMembers(members);
        return party;
    }
}
