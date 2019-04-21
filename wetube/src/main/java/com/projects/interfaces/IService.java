package com.projects.interfaces;

import java.io.Serializable;
import java.util.Collection;

public interface IService<T extends IDTO> {
   void save(T value);

   void save(Collection<T> values);

   void update(T value);

   void update(Collection<T> values);

   void delete(Serializable key);

   void delete(T value);

   void delete(Collection<Serializable> keys);

   Collection<T> get();

   T get(Serializable key);

   Collection<T> get(Collection<Serializable> keys);

   Collection<T> get(String filter);
}
