package com.projects.interfaces;

import com.projects.abstractions.AbstractDTO;

import java.io.Serializable;

public interface ITransactionalDAO <K extends Serializable, V extends AbstractDTO> {
   void persistTransactional(V value);
   V mergeTransactional(V value);
   void deleteTransactional(K key);
   void deleteTransactional(V value);
}