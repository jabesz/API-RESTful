package com.jabes.myproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jabes.myproject.entity.User;
import com.jabes.myproject.repository.UserRepository;
import com.jabes.myproject.service.exceptions.ObjectNotFoundException;

@Service
public class UserService {
  
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
    obj = this.userRepository.save(obj);
    return obj;
  }

  @Transactional
  public User update(User obj) {
    User newObj = findById(obj.getId());
    newObj.setPassword(obj.getPassword());
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