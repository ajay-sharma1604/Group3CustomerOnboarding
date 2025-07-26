package com.oracle.kyc.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.oracle.kyc.dto.CustomerResponseDTO;

@FeignClient(name = "customer-service", path = "/customerservice/api")
public interface CustomerServiceProxy {

    @GetMapping("/customers/{id}")
    CustomerResponseDTO getCustomerById(@PathVariable("id") String id);
}
