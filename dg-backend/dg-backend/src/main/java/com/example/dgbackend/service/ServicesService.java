package com.example.dgbackend.service;

import com.example.dgbackend.common.dto.ContentDto;
import com.example.dgbackend.database.services.ServiceDto;
import com.example.dgbackend.database.services.ServiceNamesDto;
import com.example.dgbackend.database.services.sql.ServiceSqlRow;
import static com.example.dgbackend.database.services.sql.ServiceSqlRow.ServiceNamesSqlRow;
import com.example.dgbackend.database.services.sql.ServicesSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    @Autowired
    ServicesSqlService servicesSqlService;

    public ServicesService(ServicesSqlService servicesSqlService) {
        this.servicesSqlService = servicesSqlService;
    }

    public ContentDto<ServiceDto> getServiceInfoByName(String serviceName) {
        List<ServiceDto> serviceDtoList = servicesSqlService.getServiceByName(serviceName)
                .stream().map(ServicesService::serviceDtoMapper).toList();

        return new ContentDto<>(serviceDtoList);
    }

    public ContentDto<ServiceNamesDto> getServiceNames() {
        List<ServiceNamesDto> serviceNamesDtoList = servicesSqlService.getServiceNames()
                .stream().map(ServicesService::serviceNamesDtoMapper).toList();

        return new ContentDto<>(serviceNamesDtoList);
    }

    public List<ServiceDto> getServiceByNameAndCarSize(String serviceName, String carSize) {
        return servicesSqlService.getServiceByNameAndCarSize(serviceName, carSize).stream()
                .map(ServicesService::serviceDtoMapper).toList();
    }

    private static ServiceDto serviceDtoMapper(ServiceSqlRow serviceSqlRow) {
        return new ServiceDto(
                serviceSqlRow.id(),
                serviceSqlRow.name(),
                serviceSqlRow.price(),
                serviceSqlRow.length(),
                serviceSqlRow.car_size());
    }

    private static ServiceNamesDto serviceNamesDtoMapper(ServiceNamesSqlRow servicesSqlRow) {
        return new ServiceNamesDto(
                servicesSqlRow.name());
    }
}
