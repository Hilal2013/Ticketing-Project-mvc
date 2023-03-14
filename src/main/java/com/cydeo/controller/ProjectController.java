package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createUser(Model model) {
// i need one empty object
model.addAttribute("project",new ProjectDTO());
//I need manager list
        model.addAttribute("managers", userService.findMangers());
                model.addAttribute("projects",projectService.findAll());
        return "/project/create";
    }
   @PostMapping("/create")
    public String insertProject(@ModelAttribute("project") ProjectDTO project){

    //    project.setProjectStatus(Status.OPEN); this is business logic where i can put?to service
        projectService.save(project);


return "redirect:/project/create";
    }


    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String  projectCode){
        projectService.deleteById(projectCode);
return "redirect:/project/create";
    }



}
