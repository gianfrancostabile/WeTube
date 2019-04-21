package com.projects.account;

import com.projects.interfaces.IMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AccountMapper implements IMapper<Account, AccountDTO, AccountWrapper> {
   @Override
   public Account fromDTOtoDOMAIN(AccountDTO dto) {
      Account account = new Account();
      account.setEmail(dto.getEmail());
      account.setPassword(dto.getPassword());
      return account;
   }

   @Override
   public AccountDTO fromDOMAINtoDTO(Serializable id, Account domain) {
      AccountDTO accountDTO = new AccountDTO();
      accountDTO.setId(Long.parseLong(id.toString()));
      accountDTO.setEmail(domain.getEmail());
      accountDTO.setPassword(domain.getPassword());
      return accountDTO;
   }

   @Override
   public AccountWrapper fromDOMAINtoWRAPPER(Account domain) {
      return new AccountWrapper();
   }

   @Override
   public AccountWrapper fromDTOtoWRAPPER(AccountDTO dto) {
      return new AccountWrapper();
   }
}
