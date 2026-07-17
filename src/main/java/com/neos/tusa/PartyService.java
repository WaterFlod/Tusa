package com.neos.tusa;

import com.neos.tusa.dto.PartyCreateRequest;
import com.neos.tusa.model.Party;
import com.neos.tusa.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;
    private final UserRepository userRepository;

    @Transactional
    public Party createParty(PartyCreateRequest request) {
        User user = userRepository.findById(request.adminUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Party party = new Party();
        party.setName(request.name());
        party.setAdmin(user);
        return partyRepository.save(party);
    }
}
