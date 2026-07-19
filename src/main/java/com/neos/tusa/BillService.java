package com.neos.tusa;

import com.neos.tusa.dto.AddBillRequest;
import com.neos.tusa.exception.PartyNotFoundException;
import com.neos.tusa.exception.UserNotFoundException;
import com.neos.tusa.exception.UserNotInPartyException;
import com.neos.tusa.model.Bill;
import com.neos.tusa.model.Party;
import com.neos.tusa.model.User;
import com.neos.tusa.repository.BillRepository;
import com.neos.tusa.repository.PartyRepository;
import com.neos.tusa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final PartyRepository partyRepository;
    private final TusaBot bot;

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

        bill = billRepository.save(bill);

        Party party = partyRepository.findById(request.partyId()).orElseThrow(() -> new PartyNotFoundException(request.partyId()));
        List<User> members = party.getMembers();
        for (User user : members) {
            bot.sendMessage(user.getChatId(),
                    String.format(
                            "Добавлен новый счет на сумму %s руб. \nОписание:%s",
                            bill.getAmount().toString(),
                            bill.getDescription()
                    )
            );
        }

        return bill;
    }
}
