package com.projects.interfaces;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface IDAO <K extends Serializable, V extends IDTO> {
   void persist(V value);
   void persist(Collection<V> values);
   V merge(V value);
   Collection<V> merge(Collection<V> values);
   void delete(V value);
   void delete(K key);
   void delete(Collection<K> keys);
   Optional<Collection<V>> get();
   Optional<V> get(K key);
   Optional<Collection<V>> get(Collection<K> keys);
}