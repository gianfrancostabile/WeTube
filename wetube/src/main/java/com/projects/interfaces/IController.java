package com.projects.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;

public interface IController<K extends Serializable, V> {

   @PostMapping(value = "/", consumes = "application/json")
   ResponseEntity post(@RequestBody Collection<V> values);

   @PutMapping(value = "/", consumes = "application/json")
   ResponseEntity<V> put(@RequestBody V value);

   @DeleteMapping(value = "/")
   ResponseEntity delete(@RequestParam("id") K id);

   @GetMapping(value = "")
   ResponseEntity<Collection<V>> get();

   @GetMapping(value = "/{id}")
   ResponseEntity<V> get(@PathVariable("id") K id);
}
