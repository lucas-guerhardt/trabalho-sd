package com.lp.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.users.models.dto.UserCreate;
import com.lp.users.models.dto.UserGet;
import com.lp.users.models.dto.UserGetAll;
import com.lp.users.models.dto.UserUpdate;
import com.lp.users.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "User Controller", description = "Controller responsável pelos usuários")
@RestController
@RequestMapping("/u")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserGet>> get(){
        try{
            return ResponseEntity.ok(userService.get());
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGet> get(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.get(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserGetAll> get(@PathVariable String email){
        try{
            return ResponseEntity.ok(userService.get(email));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody UserCreate user){
        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        try{
            return ResponseEntity.created(null).body(userService.create(user).toString());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UserUpdate user){
        try{
            return ResponseEntity.ok(userService.update(id, user).toString());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    
}
