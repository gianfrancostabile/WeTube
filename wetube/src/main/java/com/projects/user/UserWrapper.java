package com.projects.user;

import com.projects.enums.Genre;
import lombok.Data;

import java.util.Date;

@Data
public class UserWrapper {
   private String nickname;
   private Genre genre;
   private Date birthday;
   private String image;
}
