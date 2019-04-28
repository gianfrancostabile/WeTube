package com.projects.user;

import com.projects.interfaces.IController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
@SuppressWarnings({"unused", "unchecked"})
public class UserController implements IController<UserDTO, UserWrapper> {

   private Logger logger = LogManager.getLogger(UserController.class);

   @Autowired
   private UserService service;

   @Autowired
   private UserMapper mapper;

   @Override
   public ResponseEntity post(Collection<UserDTO> values) {
      service.save(values);
      return new ResponseEntity(HttpStatus.CREATED);
   }

   @Override
   public ResponseEntity<UserDTO> put(UserDTO value) {
      service.update(value);
      return new ResponseEntity(HttpStatus.OK);
   }

   @Override
   public ResponseEntity delete(Serializable id) {
      service.delete(id);
      return new ResponseEntity(HttpStatus.OK);
   }

   @Override
   public ResponseEntity<Collection<UserWrapper>> get() {
      Collection<UserDTO> serviceResponse = service.get();
      List<UserWrapper> mappedValues = serviceResponse.stream().map(v -> mapper.fromDTOtoWRAPPER(v)).collect(Collectors.toList());
      return new ResponseEntity(mappedValues, HttpStatus.OK);
   }

   @Override
   public ResponseEntity<UserWrapper> get(Serializable id) {
      UserDTO serviceResponse = service.get(id);
      UserWrapper mappedValue = mapper.fromDTOtoWRAPPER(serviceResponse);
      return new ResponseEntity(mappedValue, HttpStatus.OK);
   }
}
