package com.projects.user;

import com.projects.account.AccountDTO;
import com.projects.account.AccountMapper;
import com.projects.account.AccountService;
import com.projects.interfaces.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserMapper implements IMapper<User, UserDTO, UserWrapper> {
   @Autowired private AccountService accountService;
   @Autowired private AccountMapper accountMapper;

   @Override
   public User fromDTOtoDOMAIN(UserDTO dto) {
      User user = new User();
      user.setNickname(dto.getNickname());
      user.setGenre(dto.getGenre());
      user.setBirthday(dto.getBirthday());
      user.setImage(dto.getImage());
      user.setAccount(accountMapper.fromDTOtoDOMAIN(dto.getAccount()));
      return user;
   }

   @Override
   public UserDTO fromDOMAINtoDTO(Serializable id, User domain) {
      UserDTO userDTO = new UserDTO();
      userDTO.setId(Long.parseLong(id.toString()));
      userDTO.setNickname(domain.getNickname());
      userDTO.setGenre(domain.getGenre());
      userDTO.setBirthday(domain.getBirthday());
      userDTO.setImage(domain.getImage());
      userDTO.setAccount(accountMapper.fromDOMAINtoDTO(0L, domain.getAccount()));
      return userDTO;
   }

   @Override
   public UserWrapper fromDOMAINtoWRAPPER(User domain) {
      UserWrapper userWrapper = new UserWrapper();
      userWrapper.setNickname(domain.getNickname());
      userWrapper.setGenre(domain.getGenre());
      userWrapper.setBirthday(domain.getBirthday());
      userWrapper.setImage(domain.getImage());
      return userWrapper;
   }

   @Override
   public UserWrapper fromDTOtoWRAPPER(UserDTO dto) {
      UserWrapper userWrapper = new UserWrapper();
      userWrapper.setNickname(dto.getNickname());
      userWrapper.setGenre(dto.getGenre());
      userWrapper.setBirthday(dto.getBirthday());
      userWrapper.setImage(dto.getImage());
      return userWrapper;
   }
}
