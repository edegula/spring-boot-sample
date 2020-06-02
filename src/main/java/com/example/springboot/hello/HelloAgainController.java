package com.example.springboot.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloAgainController {

    @RequestMapping("v2/hello")
    public String sayHi() {
        return "<html><body><h1>Hi</h1></body></html>";
    }

}
