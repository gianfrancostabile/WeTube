package com.projects.interfaces;

import java.io.Serializable;
import java.util.Collection;

public interface ITransactionalDAO<K extends Serializable, V extends IDTO> {
   void saveTransactional(V value);

   void saveTransactional(Collection<V> values);

   void updateTransactional(V value);

   void updateTransactional(Collection<V> values);

   void deleteTransactional(V value);

   void deleteTransactional(K key);

   void deleteTransactional(Collection<K> keys);
}