package com.neos.tusa;

import com.neos.tusa.dto.PartyCreateRequest;
import com.neos.tusa.exception.UserNotFoundException;
import com.neos.tusa.model.Party;
import com.neos.tusa.model.User;
import com.neos.tusa.repository.PartyRepository;
import com.neos.tusa.repository.UserRepository;
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
                .orElseThrow(() -> new UserNotFoundException(request.adminUserId()));
        Party party = new Party();
        party.setName(request.name());
        party.setAdmin(user);
        return partyRepository.save(party);
    }
}
