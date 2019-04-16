package com.projects.abstractions;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractDTO<K extends Serializable> implements Serializable {
   private K id;

   protected AbstractDTO() {
      this(null);
   }

   protected AbstractDTO(K id) {
      setId(id);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof AbstractDTO)) return false;
      AbstractDTO<?> that = (AbstractDTO<?>) o;
      return Objects.equals(getId(), that.getId());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId());
   }

   @Override
   public String toString() {
      return new StringBuffer().append("id:").append(getId().toString()).toString();
   }

   @Override
   protected Object clone() {
      return this;
   }

   public K getId() {
      return this.id;
   }

   public AbstractDTO setId(K id) {
      this.id = id;
      return this;
   }
}
