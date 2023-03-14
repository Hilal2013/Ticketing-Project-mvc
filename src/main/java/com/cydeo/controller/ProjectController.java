package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
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

    @GetMapping("/complete/{projectCode}")//which object you completing updating how backend will know
public String completeProject( @PathVariable("projectCode") String projectCode){
//change status for complete ->do you have service?->I dont have //so create
// go to service(you trying do smthing in controller and you want to change sthing in data base//)
projectService.complete(projectService.findById(projectCode));
    return "redirect:/project/create";
}
    @GetMapping("/update/{projectCode}")
    public String editUser( @PathVariable("projectCode") String projectCode,Model model) {
// we need to populate//all object field in the form
        model.addAttribute("project",userService.findById(projectCode));
        model.addAttribute("managers", userService.findMangers());
        model.addAttribute("projects", projectService.findAll());
        return "project/update";
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("projet") ProjectDTO project){
//i need to update that user
        projectService.update(project);
        return "redirect:/project/create";
    }

}
