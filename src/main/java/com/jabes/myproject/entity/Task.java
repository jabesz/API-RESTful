package com.jabes.myproject.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @com.fasterxml.jackson.annotation.JsonBackReference
  private User user;

  @Column(name = "description", length = 255, nullable = false)
  @NotBlank
  @Size(min = 1, max = 255)
  private String description;

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
    if (!(obj instanceof Task))
      return false;
    Task other = (Task) obj;
    if (this.id == null)
      if (other.id != null)
        return false;
      else if (!this.id.equals(other.id))
        return false;
    return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user)
        && Objects.equals(this.description, other.description);

  }
}