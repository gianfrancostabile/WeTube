package com.projects.interfaces;

import java.io.Serializable;

public interface IDTO<K> extends Serializable {
   K getID();

   IDTO setID(K ID);
}
