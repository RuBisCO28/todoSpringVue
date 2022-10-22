package com.spa.todoSpringVue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/todo")
public class TodoController {
    @GetMapping
    public String home() {
        return "hello";
    }
}
