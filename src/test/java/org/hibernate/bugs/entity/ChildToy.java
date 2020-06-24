package org.hibernate.bugs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ChildToy {

   @Id
   @GeneratedValue
   private Long id;

   private String name = "choochootrain";

   @ManyToOne
   private Child child;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Child getChild() {
      return child;
   }

   public void setChild(Child child) {
      this.child = child;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
