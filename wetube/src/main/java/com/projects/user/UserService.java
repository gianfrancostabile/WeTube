package com.projects.user;

import com.projects.interfaces.IDTO;
import com.projects.interfaces.IService;
import com.projects.utilities.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IService<UserDTO> {

   @Autowired private UserRepository repository;

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
      repository.deleteTransactional(key);
   }

   @Override
   public void delete(UserDTO value) {
      repository.deleteTransactional(value);
   }

   @Override
   public void delete(Collection<Serializable> keys) {
      repository.deleteTransactional(keys);
   }

   @Override
   public Collection<UserDTO> get() {
      Collection<UserDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.get();
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(UserService::cast).collect(Collectors.toList());
      }
      return response;
   }

   @Override
   public UserDTO get(Serializable key) {
      UserDTO response = new UserDTO();
      Optional<IDTO> repositoryResponse = repository.get(key);
      if (repositoryResponse.isPresent()) {
         response = (UserDTO) repositoryResponse.get();
      }
      return response;
   }

   @Override
   public Collection<UserDTO> get(Collection<Serializable> keys) {
      Collection<UserDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.get(keys);
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(UserService::cast).collect(Collectors.toList());
      }
      return response;
   }

   @Override
   public Collection<UserDTO> get(String filter) {
      Collection<UserDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.get(filter);
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(UserService::cast).collect(Collectors.toList());
      }
      return response;
   }

   private static UserDTO cast(Object o) {
      return (UserDTO) o;
   }
}
