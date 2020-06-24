package org.hibernate.bugs.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedEntityGraph(
      name = "parentWithToysAndChild",
      attributeNodes = {
            @NamedAttributeNode("toys"),
            @NamedAttributeNode("child")
      }
)
public class Parent {

   public static final String PARENT_WITH_TOYS_AND_CHILD_GRAPH = "parentWithToysAndChild";

   @Id
   @GeneratedValue
   private Long id;

   @ManyToOne(cascade = CascadeType.PERSIST)
   private Child child;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   private Set<ParentToy> toys;

   public Child getChild() {
      return child;
   }

   public void setChild(Child child) {
      this.child = child;
   }

   public Set<ParentToy> getToys() {
      return toys;
   }

   public void setToys(Set<ParentToy> toys) {
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
      return "Parent{" + "id=" + id + '}';
   }
}
