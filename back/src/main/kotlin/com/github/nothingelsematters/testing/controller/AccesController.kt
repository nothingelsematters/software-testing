package com.github.nothingelsematters.testing.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class AccessController {
    
    @RequestMapping(method = [RequestMethod.OPTIONS, RequestMethod.POST], value = ["/**"]) fun `FOR FUCKS SAKE`() = ""

    @GetMapping("/health") fun healthCheck() = "OK"
}
