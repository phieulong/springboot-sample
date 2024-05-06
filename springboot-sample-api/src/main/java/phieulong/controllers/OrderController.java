package phieulong.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import phieulong.resources.Resource;


@RestController
public class OrderController {

    @GetMapping("/v1/orders")
    public Resource<String> listOrders() {
        return new Resource<>("Ok");
    }
}
