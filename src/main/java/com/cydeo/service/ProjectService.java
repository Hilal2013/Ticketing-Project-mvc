package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

public interface ProjectService extends  CrudService<ProjectDTO,String>{
//first one which object->ProjectDTO (value);  second one String->projectcode unique item(key))

    void complete(ProjectDTO project);
}
