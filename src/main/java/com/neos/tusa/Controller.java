package com.neos.tusa;

import com.neos.tusa.dto.*;
import com.neos.tusa.model.Bill;
import com.neos.tusa.model.Party;
import com.neos.tusa.model.User;
import com.neos.tusa.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class Controller {

    private final UserRepository userRepository;
    private final PartyService partyService;
    private final BillService billService;

    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setTelegramId("telegram");
        userRepository.save(user);
        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getTelegramId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/parties")
    public ResponseEntity<PartyResponse> createParty(@Valid @RequestBody PartyCreateRequest request) {
        Party party = partyService.createParty(request);

        PartyResponse response = new PartyResponse(
                party.getId(),
                party.getName(),
                party.getAdmin().getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/bills")
    public ResponseEntity<BillResponse> addBill(@Valid @RequestBody AddBillRequest request) {
        Bill bill = billService.createBill(request);

        BillResponse response = new BillResponse(
                bill.getId(),
                bill.getAmount(),
                bill.getDescription(),
                bill.getUser().getId(),
                bill.getParty().getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
