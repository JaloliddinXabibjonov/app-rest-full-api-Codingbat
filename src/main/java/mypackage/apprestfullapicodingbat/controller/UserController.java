package mypackage.apprestfullapicodingbat.controller;

import mypackage.apprestfullapicodingbat.entity.User;
import mypackage.apprestfullapicodingbat.payload.UserDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * ADD USER
     * @param userDto
     * @return ResponseEntity<Result>
     */
    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody UserDto userDto){
        Result result = userService.add(userDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }

    /**
     * GET ALL USERS
     * @return ResponseEntity<List<User>>
     */
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> userList = userService.getAll();
        return ResponseEntity.ok(userList);
    }

    /**
     * GET USER BY ID
     * @param id
     * @return ResponseEntity<User>
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id){
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * EDIT USER BY ID
     * @param id
     * @param userDto
     * @return ResponseEntity<Result>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody UserDto userDto){
        Result result = userService.edit(id, userDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

    /**
     * DELETE USER BY ID
     * @param id
     * @return ResponseEntity<Result>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = userService.delete(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }
}
