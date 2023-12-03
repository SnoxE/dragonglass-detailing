package com.example.dgbackend.controller;

import com.example.dgbackend.common.dto.ContentDto;
import com.example.dgbackend.database.services.ServiceDto;
import com.example.dgbackend.database.services.ServiceNamesDto;
import com.example.dgbackend.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    @Autowired
    ServicesService servicesService;

    @GetMapping()
    public ContentDto<ServiceNamesDto> getServiceNames() {
        return servicesService.getServiceNames();
    }

    @GetMapping("/{serviceName}")
    public ContentDto<ServiceDto> getServiceByName(@PathVariable("serviceName") String serviceName) {
        return servicesService.getServiceInfoByName(serviceName);
    }

    @GetMapping("/{serviceName}/{carSize}")
    public List<ServiceDto> getServiceId(
            @PathVariable("serviceName")String serviceName,
            @PathVariable("carSize")String carSize) {
        return servicesService.getServiceByNameAndCarSize(serviceName, carSize);
    }
}
