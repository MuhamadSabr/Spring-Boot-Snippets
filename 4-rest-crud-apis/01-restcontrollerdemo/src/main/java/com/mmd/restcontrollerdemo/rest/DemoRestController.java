package com.mmd.restcontrollerdemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Indicates that this class defines a RESTful web service endpoint.
//test is the endpoint, and the class can handle RequestMethods of GET, POST, and PUT.
@RestController
@RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class DemoRestController {

    //Add the code for the hello end-point
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello, World!";
    }
}
