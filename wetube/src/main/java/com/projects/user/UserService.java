package com.projects.user;

import com.projects.interfaces.IDTO;
import com.projects.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IService<UserDTO> {

   @Autowired
   private UserRepository repository;

   private static UserDTO cast(Object o) {
      return (UserDTO) o;
   }

   private static Long keyCast(Serializable o) {
      return Long.parseLong(o.toString());
   }

   @Override
   public void save(UserDTO value) {
      repository.saveTransactional(value);
   }

   @Override
   public void save(Collection<UserDTO> values) {
      repository.saveTransactional(values);
   }

   @Override
   public void update(UserDTO value) {
      repository.updateTransactional(value);
   }

   @Override
   public void update(Collection<UserDTO> values) {
      repository.updateTransactional(values);
   }

   @Override
   public void delete(Serializable key) {
      repository.deleteTransactional(keyCast(key.toString()));
   }

   @Override
   public void delete(UserDTO value) {
      repository.deleteTransactional(value);
   }

   @Override
   public void delete(Collection<Serializable> keys) {
      Collection<Long> keysMapped = keys.stream().map(UserService::keyCast).collect(Collectors.toList());
      repository.deleteTransactional(keysMapped);
   }

   @Override
   public Collection<UserDTO> get() {
      Collection<UserDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.find();
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(UserService::cast).collect(Collectors.toList());
      }
      return response;
   }

   @Override
   public UserDTO get(Serializable key) {
      UserDTO response = new UserDTO();
      Optional<IDTO> repositoryResponse = repository.find(keyCast(key.toString()));
      if (repositoryResponse.isPresent()) {
         response = (UserDTO) repositoryResponse.get();
      }
      return response;
   }

   @Override
   public Collection<UserDTO> get(Collection<Serializable> keys) {
      Collection<UserDTO> response = new ArrayList<>();
      Collection<Long> keysMapped = keys.stream().map(UserService::keyCast).collect(Collectors.toList());
      Optional<Collection<IDTO>> repositoryResponse = repository.find(keysMapped);
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(UserService::cast).collect(Collectors.toList());
      }
      return response;
   }

   @Override
   public Collection<UserDTO> get(String filter) {
      Collection<UserDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.find(filter);
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(UserService::cast).collect(Collectors.toList());
      }
      return response;
   }
}
