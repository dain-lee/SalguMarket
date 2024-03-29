package com.ApricotMarket.controller;

import com.ApricotMarket.domain.User;
import com.ApricotMarket.service.ItemService;
import com.ApricotMarket.service.UserService;
import com.ApricotMarket.service.checkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    private final ItemService itemService;
    private checkService checkService;
    // private final UserService userService;

    @GetMapping("/signin")
    public String getLogin() {
        System.out.println("controller: signinform");
        return "/signin";
    }

    @PostMapping("/signin")
    public String Login() {
        System.out.println("controller: signinform112");
        return "/home";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        System.out.println("controller: signupform");
        return "/signup";
    }

    @PostMapping("/signup")
    public String SignUp(User user) {
        userService.save(user);
        System.out.println("controller: signupform112");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String LogOut(HttpSession session){
        session.invalidate();
        checkService.UserLogout(checkService.findloguser().get().getId());
        return "redirect:/";
    }

    // Customer mypage //
    @GetMapping("/mypage/customer")
    public String mypage(Model model, Model model2){
        model.addAttribute("userList", checkService.findloguser().get());
        model2.addAttribute("itemList",itemService.findReserved());
        return "/mypage-customer";
    }

    @GetMapping("/mypage/seller")
    public String getSellerMypage() {
        return "/mypage-seller";
    }

    public UserController(UserService userService, ItemService itemService, checkService checkService) {
        this.userService = userService;
        this.itemService = itemService;
        this.checkService = checkService;
    }
}




