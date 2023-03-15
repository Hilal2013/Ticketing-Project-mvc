package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    //if you are using any method belongs to other class you need to injected
    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());

        return "user/create";
    }

    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult,Model model) {
//if there is error
if(bindingResult.hasErrors()){
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("users", userService.findAll());

    return "user/create";//file//dont redirect
}

        userService.save(user);
        return "redirect:/user/create";
    }
@GetMapping("/update/{username}")
    public String editUser( @PathVariable("username") String username,Model model) {
//what i need to do//user//roles//users
    //user object    ${user}
   //     model.addAttribute("user", new UserDTO());   im gonna see empty//there needs to be object but what it is?
        model.addAttribute("user",userService.findById(username));
//username comes outside//Pathvariable//when update just retrieving just posting //GetMapping

        //roles ${roles}
    model.addAttribute("roles", roleService.findAll());
    //users  ${users}
    model.addAttribute("users", userService.findAll());
        return "user/update";
    }
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user){
//i need to update that user
userService.update(user);
return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser( @PathVariable("username") String username ){

        userService.deleteById(username);
        return "redirect:/user/create";
    }



}
