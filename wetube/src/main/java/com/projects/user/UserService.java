package com.projects.user;

import com.projects.interfaces.IDTO;
import com.projects.interfaces.IService;
import com.projects.utilities.GenericRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IService<Long, UserDTO> {

   private GenericRepository repository = new GenericRepository("USERS", Long.class, UserDTO.class);

   @Override
   public void persist(UserDTO value) {
      repository.persistTransactional(value);
   }

   @Override
   public void persist(Collection<UserDTO> values) {
      repository.persistTransactional(values);
   }

   @Override
   public UserDTO merge(UserDTO value) {
      return (UserDTO) repository.mergeTransactional(value);
   }

   @Override
   public Collection<UserDTO> merge(Collection<UserDTO> values) {
      Collection<IDTO> repositoryResponse = repository.mergeTransactional(values);
      Collection<UserDTO> response = repositoryResponse.stream().map(v -> (UserDTO) v).collect(Collectors.toList());
      return response;
   }

   @Override
   public void delete(Long key) {
      repository.deleteTransactional(key);
   }

   @Override
   public void delete(UserDTO value) {
      repository.deleteTransactional(value);
   }

   @Override
   public void delete(Collection<Long> keys) {
      repository.deleteTransactional(keys);
   }

   @Override
   public Collection<UserDTO> get() {
      Collection<UserDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.get();
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(v -> (UserDTO) v).collect(Collectors.toList());
      }
      return response;
   }

   @Override
   public UserDTO get(Long key) {
      UserDTO response = null;
      Optional<IDTO> repositoryResponse = repository.get(key);
      if (repositoryResponse.isPresent()) {
         response = (UserDTO) repositoryResponse.get();
      }
      return response;
   }

   @Override
   public Collection<UserDTO> get(Collection<Long> keys) {
      Collection<UserDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.get(keys);
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(v -> (UserDTO) v).collect(Collectors.toList());
      }
      return response;
   }
}
