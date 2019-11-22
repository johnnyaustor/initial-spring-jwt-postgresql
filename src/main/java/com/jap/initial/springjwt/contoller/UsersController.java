package com.jap.initial.springjwt.contoller;

import com.jap.initial.springjwt.model.Users;
import com.jap.initial.springjwt.payload.ApiResponse;
import com.jap.initial.springjwt.payload.MetaInfoResponse;
import com.jap.initial.springjwt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("")
    public ApiResponse getAll(@RequestParam Map<String, String> params) {
        List<Users> users = params.isEmpty() ?  usersService.findAllUsers() : usersService.findByCriteria(params);
        return new ApiResponse(users);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return new ApiResponse(usersService.findById(id));
    }

    @GetMapping("/search")
    public ApiResponse findUsersByCriteria(@RequestParam("q") String q) {
        return new ApiResponse(usersService.findAllByCriteria(q));
    }

    @GetMapping("/page")
    public ApiResponse getAll(Pageable pageable) {
        Page<Users> users = usersService.findAll(pageable);
        return new ApiResponse(users.getContent()).setMetaInfo(new MetaInfoResponse(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody Users newUsers, @PathVariable Long id) {
        newUsers.setId(id);
        Users users = usersService.saveUser(newUsers);
        return new ResponseEntity<>(new ApiResponse(users), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        usersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/annotation")
    public ResponseEntity<?> annotation(@Valid @RequestBody Users users) {
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
