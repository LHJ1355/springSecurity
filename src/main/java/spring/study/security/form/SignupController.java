package spring.study.security.form;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.study.security.domain.Account;
import spring.study.security.service.AccountService;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignupController {
    private final AccountService accountService;
    @GetMapping
    public String signup(Model model) {
        model.addAttribute("account", new Account());
        return "signup";
    }

    @PostMapping
    public String signupProcess(@ModelAttribute Account account) {
        account.setRole("USER");
        accountService.create(account);
        return "redirect:/";
    }

}
