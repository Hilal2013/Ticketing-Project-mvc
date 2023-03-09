package com.cydeo.converter;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class RoleDTOConverter implements Converter<String,RoleDTO> {

    public RoleDTOConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    RoleService roleService;

    @Override
    public RoleDTO convert(String source) {//source is String
        //go to database find by id
        return roleService.findById(Long.parseLong(source));//convert to long
    }
}
