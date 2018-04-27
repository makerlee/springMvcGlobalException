package com.example.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(serviceId = "vehicle-control", path = "/vehicle-control/report")
public interface VehicleControlReportClient {

}
