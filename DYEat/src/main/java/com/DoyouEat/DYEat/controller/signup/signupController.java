package com.DoyouEat.DYEat.controller.signup;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.repository.account.AccountApiRepository;
import com.DoyouEat.DYEat.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/signup")
public class signupController {

    private final AccountService accountService;
    private final AccountApiRepository accountApiRepository;

    @GetMapping
    public String Signup(Model model) {
        List<DYE_Account> accounts = accountApiRepository.findAll();
        model.addAttribute(new signupForm());
        model.addAttribute("account", accounts);
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

    @ResponseBody
    @PostMapping(value = "/api", produces = "application/json; charset=UTF-8")
    public String checkUsernameDuplicate(@RequestBody Map<String, String> map) {
        String result;
        String emailInput = map.get("EmailInput");
        DYE_Account dye_account = accountApiRepository.findByUsername(emailInput);
        if(!dye_account.getUsername().isEmpty()){
            result = "1";
        }
        return map.get("EmailInput");
    }

}

