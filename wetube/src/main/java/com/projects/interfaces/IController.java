package com.projects.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;

public interface IController<T, S> {

   @PostMapping(value = "/", consumes = "application/json")
   ResponseEntity post(@RequestBody Collection<T> values);

   @PutMapping(value = "/", consumes = "application/json")
   ResponseEntity put(@RequestBody T value);

   @DeleteMapping(value = "/{id}")
   ResponseEntity delete(@PathVariable("id") Serializable id);

   @ResponseBody
   @GetMapping(value = "")
   ResponseEntity<Collection<S>> get();

   @ResponseBody
   @GetMapping(value = "/{id}")
   ResponseEntity<S> get(@PathVariable("id") Serializable id);
}
