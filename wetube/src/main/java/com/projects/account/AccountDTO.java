package com.projects.account;

import com.projects.interfaces.IDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AccountDTO implements IDTO {
   private Long id;
   private String email;
   private String password;
}
