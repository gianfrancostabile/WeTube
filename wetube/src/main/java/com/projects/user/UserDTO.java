package com.projects.user;

import com.projects.account.AccountDTO;
import com.projects.enums.Genre;
import com.projects.interfaces.IDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserDTO implements IDTO {
   private Long id;
   private String nickname;
   private Genre genre;
   private Date birthday;
   private String image;
   private AccountDTO account;
}
