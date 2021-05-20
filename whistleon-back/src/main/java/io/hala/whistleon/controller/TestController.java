package io.hala.whistleon.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*")
@Controller
public class TestController {

    @ApiOperation(value = "테스트")
    @GetMapping("/test/{testId}")
    public ResponseEntity<String> test(@PathVariable String testId, HttpServletResponse response) {
        return null;
    }
}
