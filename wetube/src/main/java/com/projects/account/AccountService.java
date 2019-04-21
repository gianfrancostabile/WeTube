package com.projects.account;

import com.projects.interfaces.IDTO;
import com.projects.interfaces.IService;
import com.projects.utilities.GenericRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements IService<AccountDTO> {

   private GenericRepository repository = new GenericRepository(AccountDTO.class);

   @Override
   public void save(AccountDTO value) {
      repository.save(value);
   }

   @Override
   public void save(Collection<AccountDTO> values) {
      repository.save(values);
   }

   @Override
   public void update(AccountDTO value) {
      repository.update(value);
   }

   @Override
   public void update(Collection<AccountDTO> values) {
      repository.save(values);
   }

   @Override
   public void delete(Serializable key) {
      repository.delete(key);
   }

   @Override
   public void delete(AccountDTO value) {
      repository.delete(value);
   }

   @Override
   public void delete(Collection<Serializable> keys) {
      repository.delete(keys);
   }

   @Override
   public Collection<AccountDTO> get() {
      Collection<AccountDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.get();
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(AccountService::cast).collect(Collectors.toList());
      }
      return response;
   }

   @Override
   public AccountDTO get(Serializable key) {
      AccountDTO response = new AccountDTO();
      Optional<IDTO> repositoryResponse = repository.get(key);
      if (repositoryResponse.isPresent()) {
         response = (AccountDTO) repositoryResponse.get();
      }
      return response;
   }

   @Override
   public Collection<AccountDTO> get(Collection<Serializable> keys) {
      Collection<AccountDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.get(keys);
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(AccountService::cast).collect(Collectors.toList());
      }
      return response;
   }

   @Override
   public Collection<AccountDTO> get(String filter) {
      Collection<AccountDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.get(filter);
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(AccountService::cast).collect(Collectors.toList());
      }
      return response;
   }

   private static AccountDTO cast(Object o) {
      return (AccountDTO) o;
   }
}
