package com.projects.interfaces;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface IDAO<K extends Serializable, V extends IDTO> {
   void save(V value);

   void save(Collection<V> values);

   void update(V value);

   void update(Collection<V> values);

   void delete(V value);

   void delete(K key);

   void delete(Collection<K> keys);

   Optional<Collection<V>> find();

   Optional<V> find(K key);

   Optional<Collection<V>> find(Collection<K> keys);

   Optional<Collection<V>> find(String filter);
}
