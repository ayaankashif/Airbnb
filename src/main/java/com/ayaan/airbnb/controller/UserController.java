package com.ayaan.airbnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.ayaan.airbnb.exception.UserNotFoundException;
import com.ayaan.airbnb.model.User;
import com.ayaan.airbnb.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    
    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ModelAndView getUserProfile(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }   

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/login"; 
    }

    @GetMapping("/login")
    public String showLoginForm() { 
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("name") String name, 
                            @RequestParam("password") String password, Model model) {
        try{
            User existingUser = userService.getUserByUsername(name);
            if (existingUser != null && existingUser.getPassword().equals(password)) {
                // session.setAttribute("loggedInUser", existingUser); 
                if (existingUser.getRole().equals("admin")) {
                    return "redirect:/hotel/register"; 
                } else if (existingUser.getRole().equals("user")) {
                    return "redirect:/reserve?userId=" + existingUser.getUserId(); 
                }
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "login"; 
            }    
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage()); // "User not found"
            return "login";
        }  
        return "redirect:/home";
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView("update"); // You should have a separate form view
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Integer id, @ModelAttribute("user") User user) {
        User existing = userService.getUserById(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setContact(user.getContact());

        userService.updateUser(existing);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
        public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/home";   
    }
}
