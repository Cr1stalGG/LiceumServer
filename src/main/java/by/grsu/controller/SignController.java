package by.grsu.edu.banking.controller;

import by.grsu.edu.banking.dto.auth.AuthenticationRequest;
import by.grsu.edu.banking.dto.auth.AuthenticationResponse;
import by.grsu.edu.banking.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
@RequiredArgsConstructor
public class SignController {
    private final AccountServiceImpl accountService;

    @PostMapping("/in")
    public AuthenticationResponse authentication(@RequestBody AuthenticationRequest request){
        return accountService.authenticate(request);
    }

}
