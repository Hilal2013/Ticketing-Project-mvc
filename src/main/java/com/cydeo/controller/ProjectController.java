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

import java.util.List;

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
        model.addAttribute("managers", userService.findManagers());
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
    public String editProject(@PathVariable("projectCode") String projectCode, Model model){

        model.addAttribute("project",projectService.findById(projectCode));
        model.addAttribute("managers",userService.findManagers());
        model.addAttribute("projects",projectService.findAll());

        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project") ProjectDTO project){

        projectService.update(project);

        return "redirect:/project/create";
    }
@GetMapping("/manager/project-status")
    public String getProjectByManager(Model model){
UserDTO manager=userService.findById("john@cydeo.com");//this is hard code //but now we dont have security.
    //we need to see projects belongs to this manger//do i have any service?
    List<ProjectDTO> projects=projectService.getCountedListOfProjectDTO(manager);
//go to user ser service find specific manager by id(username/email) and then got the object.and pass this object to my service

model.addAttribute("projects",projects);
        return "manager/project-status";
}



}
