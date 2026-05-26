package org.emailapp.controllers;

import jakarta.validation.Valid;
import org.emailapp.dto.AccountResponse;
import org.emailapp.dto.CreateAccountRequest;
import org.emailapp.models.Account;
import org.emailapp.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // GET
    @GetMapping("/all")
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // POST
    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request
    ) {
        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
