package com.project.thirdlabo.controllers;

import com.project.thirdlabo.models.UserModel;
import com.project.thirdlabo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showHomePage(Model model){
        List<UserModel> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/newUser")
    public String openUserForm(Model model){
        model.addAttribute("user", new UserModel());
        model.addAttribute("pageHeader", "Add user");
        return "userForm";
    }

    @PostMapping("/saveUser")
    public String saveUser(UserModel user, Model model){
        if(userService.validateUser(user)){
            userService.save(user);
            return "redirect:/";
        }
        else{
            model.addAttribute("user", user);
            model.addAttribute("emailErrors", userService.validateEmail(user.getEmailAddress()));
            model.addAttribute("phoneNumberErrors", userService.validatePhoneNumber(user.getPhoneNumber()));
            model.addAttribute("passwordErrors", userService.validatePassword(user.getPassword()));
            model.addAttribute("pageHeader", "Reenter credentials");
            return "userForm";
        }
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("pageHeader", "Edit user");
        return "userForm";
    }

    @GetMapping("/getUser")
    public String openSearchForm(Model model){
        model.addAttribute("user", new UserModel());
        return "searchForm";
    }

    @PostMapping("/findUser")
    public String findUserById(UserModel user, Model model){
        UserModel foundUser = userService.getUser(user.getId());
        model.addAttribute("user", foundUser);
        model.addAttribute("foundUser", foundUser);
        return "searchForm";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteAction(@PathVariable("id") Integer id){
        userService.delete(id);
        return "redirect:/";
    }
}
