package com.projects.user;

import com.projects.interfaces.IController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/user")
public class UserController implements IController<Long, UserDTO> {
   private Logger logger = LogManager.getLogger(UserController.class);

   @Override
   public ResponseEntity post(Collection<UserDTO> values) {
      return new ResponseEntity(HttpStatus.CREATED);
   }

   @Override
   public ResponseEntity put(UserDTO value) {
      return new ResponseEntity(HttpStatus.OK);
   }

   @Override
   public ResponseEntity put(UserDTO value, Long id) {
      return new ResponseEntity(HttpStatus.OK);
   }

   @Override
   public ResponseEntity delete(Long id) {
      return new ResponseEntity(HttpStatus.OK);
   }

   @Override
   @SuppressWarnings("unchecked")
   public ResponseEntity<Collection<UserDTO>> get() {
      return new ResponseEntity(HttpStatus.OK);
   }

   @Override
   @SuppressWarnings("unchecked")
   public ResponseEntity<UserDTO> get(Long id) {
      return new ResponseEntity(id, HttpStatus.OK);
   }
}
