package com.livenow.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/return-value")
public class ReturnValueController {

//    public void string() {
//        return "message";
//    }
//
//    public void responseBodyForUser() {
//        return new User("name", "email");
//    }
//
//    public void responseEntity(@PathVariable Long id) {
//        return ResponseEntity.ok(new User("name", "email"));
//    }
//
//    public void responseEntityFor400() {
//        return ResponseEntity.badRequest().build();
//    }
}