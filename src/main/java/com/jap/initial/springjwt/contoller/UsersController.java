package com.jap.initial.springjwt.contoller;

import com.jap.initial.springjwt.payload.ApiResponse;
import com.jap.initial.springjwt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("")
    public ApiResponse getAll(@RequestParam Map<String, String> params) {
        return new ApiResponse(usersService.findByCriteria(params));
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return new ApiResponse(usersService.findById(id));
    }
}
