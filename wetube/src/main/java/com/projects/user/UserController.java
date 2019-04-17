package com.projects.user;

import com.projects.interfaces.IController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController implements IController<Long, UserDTO> {

   private Logger logger = LogManager.getLogger(UserController.class);
   @Autowired private UserService service;

   @Override
   public ResponseEntity post(Collection<UserDTO> values) {
      service.persist(values);
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
      Collection<UserDTO> serviceResponse = service.get();
      return new ResponseEntity(serviceResponse, HttpStatus.OK);
   }

   @Override
   @SuppressWarnings("unchecked")
   public ResponseEntity<UserDTO> get(Long id) {
      UserDTO serviceResponse = service.get(id);
      return new ResponseEntity(serviceResponse, HttpStatus.OK);
   }
}
