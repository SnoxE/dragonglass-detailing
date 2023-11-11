package com.example.dgbackend.controller;

import com.example.dgbackend.common.dto.ContentDto;
import com.example.dgbackend.database.services.ServiceDto;
import com.example.dgbackend.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    @Autowired
    ServicesService servicesService;

    @GetMapping("/{serviceName}")
    public ContentDto<ServiceDto> getServiceByName(@PathVariable("serviceName") String serviceName) {
        return servicesService.getServiceInfoByName(serviceName);
    }

    @GetMapping("/{serviceName}/{carSize}")
    public Optional<Integer> getServiceId(
            @PathVariable("serviceName")String serviceName,
            @PathVariable("carSize")String carSize) {
        return servicesService.getServiceIdByNameAndCarSize(serviceName, carSize);
    }

}
