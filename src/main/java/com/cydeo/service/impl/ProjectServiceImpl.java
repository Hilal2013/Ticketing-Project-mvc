package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO project) {
        if(project.getProjectStatus()==null){
            project.setProjectStatus(Status.OPEN);
        }


        return super.save(project.getProjectCode(),project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void update(ProjectDTO object) {
if(object.getProjectStatus()==null){
    //I have this status in the database. before save go to database get this status and assign to object
object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());

}
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
       //go to database and bring all the projects//separate the projects based on managers
        // i need only projects belongs to this manager//we need to filter//every project has assignedmanager
        //but i dont have two fields in database(completetaskcount..)
        //we can set //and map it
        //after filter i have one project but I dont have two fields//how im gonna set it
        // if i want to implement something on each object what method we use=>map
        //get this project
        List<ProjectDTO> projectList= findAll().stream()
                .filter(project->project.getAssignedManager().equals(manager))
                .map(project->{
                   // int completeTaskCount=5; how im gonna find?
                  //  int unfinishedTaskCount=3;
                    //All the tasks belongs to this project//dont forget TaskService injection
                    //i dont want to see all tasks just i want task belongs to this manger
                    List<TaskDTO> taskList = taskService.findTasksByManager(manager);
                    //now tasks can be open,completed,unfinished. I need to figure out completed and unfinished ones
                //and but this manager has one more than projects//two filtering//count is returning long cast to int
                    int completeTaskCount= (int) taskList.stream()
                            .filter(t->t.getProject().equals(project)&& t.getTaskStatus().equals(Status.COMPLETE)).count();
                    int unfinishedTaskCount=(int) taskList.stream()
                            .filter(t->t.getProject().equals(project)&& !(t.getTaskStatus().equals(Status.COMPLETE))).count();;
                    project.setCompleteTaskCounts(completeTaskCount);
                    project.setUnfinishedTaskCounts(unfinishedTaskCount);
                    return project;
                })
                .collect(Collectors.toList());
        return projectList;
    }
}
