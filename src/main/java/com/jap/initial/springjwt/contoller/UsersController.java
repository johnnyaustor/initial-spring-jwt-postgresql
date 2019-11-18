package com.jap.initial.springjwt.contoller;

import com.jap.initial.springjwt.model.Users;
import com.jap.initial.springjwt.payload.ApiResponse;
import com.jap.initial.springjwt.payload.MetaInfoResponse;
import com.jap.initial.springjwt.services.MapValidationErrorService;
import com.jap.initial.springjwt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("")
    public ApiResponse getAll(@RequestParam Map<String, String> params) {
        return new ApiResponse(usersService.findByCriteria(params));
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
    public ResponseEntity<?> put(@Valid @RequestBody Users newUsers, BindingResult result, @PathVariable Long id) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if (errorMap != null) return errorMap;

        newUsers.setId(id);
        Users users = usersService.saveUser(newUsers);
        return new ResponseEntity<>(new ApiResponse(users), HttpStatus.OK);
    }
}
