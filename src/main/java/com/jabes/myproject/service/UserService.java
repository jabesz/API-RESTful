package com.jabes.myproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jabes.myproject.entity.User;
import com.jabes.myproject.entity.enums.ProfileEnum;
import com.jabes.myproject.repository.UserRepository;
import com.jabes.myproject.service.exceptions.ObjectNotFoundException;

@Service
public class UserService {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  
  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return this.userRepository.findAll();
  }

  public User findById(Long id){
    Optional<User> user = this.userRepository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException(
      "Usuario não encontrado! id: " + id + ", Tipo: " + User.class.getName()
    ));
  }

  @Transactional
  public User create(User obj) {
    obj.setId(null);
    obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
    obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
    obj = this.userRepository.save(obj);
    return obj;
  }

  @Transactional
  public User update(User obj) {
    User newObj = findById(obj.getId());
    newObj.setPassword(obj.getPassword());
    newObj.setPassword(this.bCryptPasswordEncoder.encode(newObj.getPassword()));
    return this.userRepository.save(newObj);
  }

  public void delete(Long id) {
    findById(id);
    try {
      this.userRepository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Não é possível excluir, pois há entidades relacionadas!");
    }
  }

}