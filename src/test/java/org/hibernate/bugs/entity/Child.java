package org.hibernate.bugs.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Child {

   @Id
   @GeneratedValue
   private Long id;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   private Set<ChildToy> toys;

   public Set<ChildToy> getToys() {
      return toys;
   }

   public void setToys(Set<ChildToy> toys) {
      this.toys = toys;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Override
   public String toString() {
      return "Child{" + "id=" + id + ", toys=" + toys + '}';
   }
}
