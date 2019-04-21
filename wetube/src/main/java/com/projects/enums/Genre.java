package com.projects.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {
   MAN("Man"),
   WOMAN("Woman"),
   NONE("I prefer not to say it"),
   CUSTOM("Customized");

   private String value;

   @JsonCreator
   Genre(String value) {
      this.value = value;
   }

   @JsonValue
   public String get() {
      return value;
   }
}
