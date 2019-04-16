package com.projects.interfaces;

import com.projects.abstractions.AbstractDTO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface IDAO <K extends Serializable, V extends AbstractDTO> {
   void persist(V value);
   V merge(V value);
   void delete(K key);
   void delete(V value);
   Optional<Collection<V>> get();
   Optional<V> get(K key);
}
