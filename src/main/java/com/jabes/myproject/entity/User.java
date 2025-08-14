package com.jabes.myproject.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  public interface CreateUser {
  }

  public interface UpdateUser {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", length = 100, nullable = false, unique = true)
  @NotBlank(groups = CreateUser.class)
  @Size(groups = CreateUser.class, min = 2, max = 100)
  private String username;

  @Column(name = "password", length = 60, nullable = false)
  @NotBlank(groups = { CreateUser.class, UpdateUser.class })
  @Size(groups = { CreateUser.class, UpdateUser.class }, min = 2, max = 100)
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Task> tasks = new ArrayList<Task>();

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof User))
      return false;
    User other = (User) obj;
    if (this.id == null)
      if (other.id != null)
        return false;
      else if (!this.id.equals(other.id))
        return false;
    return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
        && Objects.equals(this.password, other.password);

  }

}