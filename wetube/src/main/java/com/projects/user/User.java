package com.projects.user;

import com.projects.account.Account;
import com.projects.enums.Genre;
import lombok.Data;

import java.util.Date;

@Data
public class User {
   private String nickname;
   private Genre genre;
   private Date birthday;
   private String image;
   private Account account;
}
