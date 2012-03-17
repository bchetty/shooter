package com.bchetty.client;

import com.bchetty.annotations.Shoot;
import com.bchetty.service.NewService;
import com.bchetty.service.Service;

public class Client {
    private Service service;
    private NewService newService;

    @Shoot
    public Client(Service service, NewService newService) {
        this.service = service;
        this.newService = newService;
    }

    public void doSomething() {
        service.doSomething();
        newService.doSomethingNew();
    }
}