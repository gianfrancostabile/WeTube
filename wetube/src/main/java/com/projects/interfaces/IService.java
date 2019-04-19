package com.projects.interfaces;

import java.io.Serializable;
import java.util.Collection;

public interface IService<K extends Serializable, V extends IDTO> {
   void persist(V value);

   void persist(Collection<V> values);

   V update(V value);

   Collection<V> update(Collection<V> values);

   void delete(K key);

   void delete(V value);

   void delete(Collection<K> keys);

   Collection<V> get();

   V get(K key);

   Collection<V> get(Collection<K> keys);
}
