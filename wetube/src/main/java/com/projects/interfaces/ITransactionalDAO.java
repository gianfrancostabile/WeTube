package com.projects.interfaces;

import java.io.Serializable;
import java.util.Collection;

public interface ITransactionalDAO <K extends Serializable, V extends IDTO> {
   void persistTransactional(V value);
   void persistTransactional(Collection<V> values);
   V mergeTransactional(V value);
   Collection<V> mergeTransactional(Collection<V> values);
   void deleteTransactional(V value);
   void deleteTransactional(K key);
   void deleteTransactional(Collection<K> keys);
}