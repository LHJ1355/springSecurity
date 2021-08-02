package spring.study.security.form;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import spring.study.security.domain.Account;
import spring.study.security.service.AccountService;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("account/{username}/{password}/{role}")
    public Account create(@ModelAttribute Account account) {
        accountService.create(account);
        return account;
    }
}
