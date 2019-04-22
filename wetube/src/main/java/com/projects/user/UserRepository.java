package com.projects.user;

import com.projects.account.AccountDTO;
import com.projects.interfaces.ICallbackDAO;
import com.projects.interfaces.IDTO;
import com.projects.utilities.GenericRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            UserDTO userDTO = (UserDTO) value;
            AccountDTO accountDTO = userDTO.getAccount();

            Serializable accountId = session.save(accountDTO);
            userDTO.getAccount().setId(Long.parseLong(accountId.toString()));
            session.save(userDTO);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void save(Collection values) {
      logger.debug("UserRepository::save(Collection values)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            for (IDTO value : (Collection<IDTO>) values) {
               UserDTO userDTO = (UserDTO) value;
               AccountDTO accountDTO = userDTO.getAccount();

               Serializable accountId = session.save(accountDTO);
               userDTO.getAccount().setId(Long.parseLong(accountId.toString()));
               session.save(userDTO);
            }
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void saveTransactional(IDTO value) {
      logger.debug("UserRepository::saveTransactional(IDTO value)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            UserDTO userDTO = (UserDTO) value;
            AccountDTO accountDTO = userDTO.getAccount();

            Serializable accountId = session.save(accountDTO);
            userDTO.getAccount().setId(Long.parseLong(accountId.toString()));
            session.save(userDTO);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void saveTransactional(Collection values) {
      logger.debug("UserRepository::saveTransactional(Collection values)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            for (IDTO value : (Collection<IDTO>) values) {
               UserDTO userDTO = (UserDTO) value;
               AccountDTO accountDTO = userDTO.getAccount();

               Serializable accountId = session.save(accountDTO);
               userDTO.getAccount().setId(Long.parseLong(accountId.toString()));
               session.save(userDTO);
            }
         }

         @Override
         public void onError() { }
      });
   }
}
