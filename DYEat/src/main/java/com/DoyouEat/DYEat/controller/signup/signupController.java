package com.DoyouEat.DYEat.controller.signup;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class signupController {

    private final AccountService accountService;

    @GetMapping
    public String Signup(Model model) {
        model.addAttribute(SignupDefinition.SingUpForm, new signupForm());
        return "views/signup/signup";
    }

    @PostMapping
    public String SavedSignup(@Valid signupForm form, BindingResult result) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (result.hasErrors()) {
            return "views/signup/signup";
        }

        DYE_Account account = new DYE_Account();
        account.setUsername(form.getUsername());
        account.setNickname(form.getNickname());
        account.setNewDate(form.getNewDate());
        account.setPassword(passwordEncoder.encode(form.getPassword()));
        account.setRole("ROLE_USER");
        account.setProvider("general");

        accountService.saveAccount(account);
        return "redirect:/main";
    }
}

