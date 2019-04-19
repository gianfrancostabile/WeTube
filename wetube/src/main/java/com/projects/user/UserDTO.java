package com.projects.user;

import com.projects.interfaces.IDTO;

import java.util.Objects;

public class UserDTO implements IDTO<Long> {
   private Long ID;
   private String name;
   private int age;

   public UserDTO() {
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof UserDTO)) return false;
      UserDTO userDTO = (UserDTO) o;
      return getAge() == userDTO.getAge() &&
         Objects.equals(getID(), userDTO.getID()) &&
         Objects.equals(getName(), userDTO.getName());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getID(), getName(), getAge());
   }

   @Override
   public String toString() {
      return new StringBuffer("UserDTO {")
         .append("id=").append(getID())
         .append(", name='").append(getName()).append("'")
         .append(", age=").append(getAge())
         .append("}").toString();
   }

   @Override
   protected Object clone() {
      return this;
   }

   @Override
   public Long getID() {
      return this.ID;
   }

   @Override
   public UserDTO setID(Long ID) {
      this.ID = ID;
      return this;
   }

   public String getName() {
      return name;
   }

   public UserDTO setName(String name) {
      this.name = name;
      return this;
   }

   public int getAge() {
      return age;
   }

   public UserDTO setAge(int age) {
      this.age = age;
      return this;
   }
}
