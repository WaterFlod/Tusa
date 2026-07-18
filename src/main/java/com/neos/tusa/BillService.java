package com.neos.tusa;

import com.neos.tusa.dto.AddBillRequest;
import com.neos.tusa.model.Bill;
import com.neos.tusa.repository.BillRepository;
import com.neos.tusa.repository.PartyRepository;
import com.neos.tusa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final PartyRepository partyRepository;

    @Transactional
    public Bill createBill(AddBillRequest request) {
        if (!partyRepository.existsUserInParty(request.userId(), request.partyId())) {
            if (!partyRepository.existsById(request.partyId())) throw new PartyNotFoundException(request.partyId());
            if (!userRepository.existsById(request.userId())) throw new UserNotFoundException(request.userId());
            throw new UserNotInPartyException(request.userId(), request.partyId());
        }

        Bill bill = new Bill();
        bill.setAmount(request.amount());
        bill.setDescription(request.description());
        bill.setUser(userRepository.getReferenceById(request.userId()));
        bill.setParty(partyRepository.getReferenceById(request.partyId()));

        return billRepository.save(bill);
    }
}
