package phieulong.api.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import phieulong.api.resources.Resource;


@RestController
public class OrderController {

    @GetMapping("/v1/orders")
    public Resource<String> listOrders() {
        return new Resource<>("Ok");
    }
}
