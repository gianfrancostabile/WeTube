package com.projects.user;

import com.projects.account.AccountDTO;
import com.projects.interfaces.IDTO;
import com.projects.utilities.GenericRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;

@Repository
public class UserRepository extends GenericRepository {
   private static Logger logger = LogManager.getLogger(GenericRepository.class);

   public UserRepository() {
      super(UserDTO.class);
   }

   @Override
   public void save(IDTO value) {
      logger.debug("UserRepository::save(IDTO value)");
      begin();
      UserDTO userDTO = (UserDTO) value;
      AccountDTO accountDTO = userDTO.getAccount();

      Serializable accountId = session.save(accountDTO);
      userDTO.getAccount().setId(Long.parseLong(accountId.toString()));
      session.save(userDTO);
      kill();
   }

   @Override
   public void save(Collection values) {
      logger.debug("UserRepository::save(Collection values)");
      begin();
      for (IDTO value : (Collection<IDTO>) values) {
         UserDTO userDTO = (UserDTO) value;
         AccountDTO accountDTO = userDTO.getAccount();

         Serializable accountId = session.save(accountDTO);
         userDTO.getAccount().setId(Long.parseLong(accountId.toString()));
         session.save(userDTO);
      }
      kill();
   }

   @Override
   public void saveTransactional(IDTO value) {
      logger.debug("UserRepository::saveTransactional(IDTO value)");
      begin();
      closeManually(true);
      transaction = null;
      try {
         transaction = session.beginTransaction();
         save(value);
         transaction.commit();
      } catch (Exception he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         close();
      }
   }

   @Override
   public void saveTransactional(Collection values) {
      logger.debug("UserRepository::saveTransactional(Collection values)");
      begin();
      closeManually(true);
      transaction = null;
      try {
         transaction = session.beginTransaction();
         save(values);
         transaction.commit();
      } catch (Exception he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         close();
      }
   }
}
