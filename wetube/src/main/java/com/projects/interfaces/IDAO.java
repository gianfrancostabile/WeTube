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

   Optional<Collection<V>> get();

   Optional<V> get(K key);

   Optional<Collection<V>> get(Collection<K> keys);

   Optional<Collection<V>> get(String filter);
}
