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
@SuppressWarnings("unchecked")
public class UserRepository extends GenericRepository<Long> {
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
         public void onError() {
         }
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
         public void onError() {
         }
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
         public void onError() {
         }
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
         public void onError() {
         }
      });
   }

   @Override
   public void update(IDTO value) {
      logger.debug("UserRepository::update(IDTO value)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            UserDTO userDTO = (UserDTO) value;
            AccountDTO accountDTO = userDTO.getAccount();

            session.update(accountDTO);
            session.update(userDTO);
         }

         @Override
         public void onError() {
         }
      });
   }

   @Override
   public void update(Collection values) {
      logger.debug("UserRepository::update(Collection values)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            for (IDTO value : (Collection<IDTO>) values) {
               UserDTO userDTO = (UserDTO) value;
               AccountDTO accountDTO = userDTO.getAccount();

               session.update(accountDTO);
               session.update(userDTO);
            }
         }

         @Override
         public void onError() {
         }
      });
   }

   @Override
   public void updateTransactional(IDTO value) {
      logger.debug("UserRepository::updateTransactional(IDTO value)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            UserDTO userDTO = (UserDTO) value;
            AccountDTO accountDTO = userDTO.getAccount();

            session.update(accountDTO);
            session.update(userDTO);
         }

         @Override
         public void onError() {
         }
      });
   }

   @Override
   public void updateTransactional(Collection values) {
      logger.debug("UserRepository::updateTransactional(Collection values)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            for (IDTO value : (Collection<IDTO>) values) {
               UserDTO userDTO = (UserDTO) value;
               AccountDTO accountDTO = userDTO.getAccount();

               session.update(accountDTO);
               session.update(userDTO);
            }
         }

         @Override
         public void onError() {
         }
      });
   }

   @Override
   public void delete(Long key) {
      logger.debug("UserRepository::delete(Long key)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            UserDTO userDTO = (UserDTO) session.find(valueClass, key.toString());
            AccountDTO accountDTO = userDTO.getAccount();
            session.delete(userDTO);
            session.delete(accountDTO);
         }

         @Override
         public void onError() {
         }
      });
   }

   @Override
   public void delete(Collection keys) {
      logger.debug("UserRepository::delete(Collection keys)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            for (Long key : (Collection<Long>) keys) {
               UserDTO userDTO = (UserDTO) session.find(valueClass, key);
               AccountDTO accountDTO = userDTO.getAccount();
               session.delete(userDTO);
               session.delete(accountDTO);
            }
         }

         @Override
         public void onError() {
         }
      });
   }

   @Override
   public void deleteTransactional(Long key) {
      logger.debug("UserRepository::deleteTransactional(Long key)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            UserDTO userDTO = (UserDTO) session.find(valueClass, key);
            AccountDTO accountDTO = userDTO.getAccount();
            session.delete(userDTO);
            session.delete(accountDTO);
         }

         @Override
         public void onError() {
         }
      });
   }

   @Override
   public void deleteTransactional(Collection keys) {
      logger.debug("UserRepository::deleteTransactional(Collection keys)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            for (Long key : (Collection<Long>) keys) {
               UserDTO userDTO = (UserDTO) session.find(valueClass, key);
               AccountDTO accountDTO = userDTO.getAccount();
               session.delete(userDTO);
               session.delete(accountDTO);
            }
         }

         @Override
         public void onError() {
         }
      });
   }

   @Override
   public void delete(IDTO value) {
      logger.debug("UserRepository::delete(IDTO value)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            UserDTO userDTO = (UserDTO) value;
            AccountDTO accountDTO = userDTO.getAccount();
            session.delete(userDTO);
            session.delete(accountDTO);
         }

         @Override
         public void onError() {
         }
      });
   }

   @Override
   public void deleteTransactional(IDTO value) {
      logger.debug("UserRepository::deleteTransactional(IDTO value)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            UserDTO userDTO = (UserDTO) value;
            AccountDTO accountDTO = userDTO.getAccount();
            session.delete(userDTO);
            session.delete(accountDTO);
         }

         @Override
         public void onError() {
         }
      });
   }
}
