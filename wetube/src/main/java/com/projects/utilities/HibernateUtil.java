package com.projects.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
   private static SessionFactory factory;

   static {
      factory = new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml")).buildSessionFactory();
   }

   public static SessionFactory getFactory() {
      return factory;
   }
}
