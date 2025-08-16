package com.jabes.myproject.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jabes.myproject.entity.User;
import com.jabes.myproject.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User obj = this.userService.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  @Validated
  public ResponseEntity<Void> create(@Valid @RequestBody User obj) {
    this.userService.create(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping
  @Validated
  public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id) {
    obj.setId(id);
    this.userService.update(obj);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.userService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
