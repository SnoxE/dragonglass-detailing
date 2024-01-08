package dgbackend.controller;

import dgbackend.common.dto.ContentDto;
import dgbackend.database.services.ServiceDto;
import dgbackend.database.services.ServiceNamesDto;
import dgbackend.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @PathVariable("serviceName") String serviceName,
            @PathVariable("carSize") String carSize) {
        return servicesService.getServiceByNameAndCarSize(serviceName, carSize);
    }
}
