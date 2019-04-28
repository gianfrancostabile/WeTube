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

   private GenericRepository<Long> repository = new GenericRepository<>(AccountDTO.class);

   private static AccountDTO cast(Object o) {
      return (AccountDTO) o;
   }

   private static Long keyCast(Serializable o) {
      return Long.parseLong(o.toString());
   }

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
      repository.delete(keyCast(key.toString()));
   }

   @Override
   public void delete(AccountDTO value) {
      repository.delete(value);
   }

   @Override
   public void delete(Collection<Serializable> keys) {
      Collection<Long> keysMapped = keys.stream().map(AccountService::keyCast).collect(Collectors.toList());
      repository.delete(keysMapped);
   }

   @Override
   public Collection<AccountDTO> get() {
      Collection<AccountDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.find();
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(AccountService::cast).collect(Collectors.toList());
      }
      return response;
   }

   @Override
   public AccountDTO get(Serializable key) {
      AccountDTO response = new AccountDTO();
      Optional<IDTO> repositoryResponse = repository.find(keyCast(key.toString()));
      if (repositoryResponse.isPresent()) {
         response = (AccountDTO) repositoryResponse.get();
      }
      return response;
   }

   @Override
   public Collection<AccountDTO> get(Collection<Serializable> keys) {
      Collection<AccountDTO> response = new ArrayList<>();
      Collection<Long> keysMapped = keys.stream().map(AccountService::keyCast).collect(Collectors.toList());
      Optional<Collection<IDTO>> repositoryResponse = repository.find(keysMapped);
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(AccountService::cast).collect(Collectors.toList());
      }
      return response;
   }

   @Override
   public Collection<AccountDTO> get(String filter) {
      Collection<AccountDTO> response = new ArrayList<>();
      Optional<Collection<IDTO>> repositoryResponse = repository.find(filter);
      if (repositoryResponse.isPresent()) {
         response = repositoryResponse.get().stream().map(AccountService::cast).collect(Collectors.toList());
      }
      return response;
   }
}
