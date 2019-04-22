package com.projects.utilities;

import com.projects.interfaces.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class GenericRepository implements IDAO, ITransactionalDAO {
   private static Logger logger = LogManager.getLogger(GenericRepository.class);

   protected Session session;
   protected Transaction transaction;

   private Class<? extends IDTO> valueClass;
   private boolean manually;

   public GenericRepository(@NotNull Class<? extends IDTO> valueClass) {
      this.valueClass = valueClass;
   }

   /**
    * Determinate if the session will be closed at the end of each method
    * @param manually <b>true</b> if the session will be closed at the end of each method. <b>false</b> the otherwise.
    */
   public void closeManually(boolean manually) {
      this.manually = manually;
   }

   /**
    * Begin a new Session, if it already exists, does nothing
    * @return generated session
    */
   public Session begin() {
      if (session == null) {
         session = HibernateUtil.getFactory().openSession();
      }
      return session;
   }

   /**
    * Destroy the created session and if the attribute manually is false
    */
   protected void kill() {
      if (session != null && !manually) {
         session.clear();
         session.close();
         session = null;
      }
   }

   /**
    * Destroy the created session and changes the attribute manually to false
    */
   public void close() {
      manually = false;
      kill();
   }

   @Override
   public void save(IDTO value) {
      logger.debug("GenericRepository::save(IDTO value)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            session.save(value);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void save(Collection values) {
      logger.debug("GenericRepository::save(Collection values)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            for (IDTO value : (Collection<IDTO>) values) {
               session.save(value);
            }
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void saveTransactional(IDTO value) {
      logger.debug("GenericRepository::saveTransactional(IDTO value)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            session.save(value);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void saveTransactional(Collection values) {
      logger.debug("GenericRepository::saveTransactional(Collection values)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            for (IDTO value : (Collection<IDTO>) values) {
               session.save(value);
            }
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void update(IDTO value) {
      logger.debug("GenericRepository::update(IDTO value)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            session.update(value);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void update(Collection values) {
      logger.debug("GenericRepository::update(Collection values)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            for (IDTO value : (Collection<IDTO>) values) {
               session.update(value);
            }
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void updateTransactional(IDTO value) {
      logger.debug("GenericRepository::updateTransactional(IDTO value)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            session.update(value);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void updateTransactional(Collection values) {
      logger.debug("GenericRepository::updateTransactional(Collection values)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            for (IDTO value : (Collection<IDTO>) values) {
               session.update(value);
            }
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void delete(Serializable key) {
      logger.debug("GenericRepository::delete(Serializable key)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            IDTO value = session.load(valueClass, key);
            session.delete(value);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void delete(Collection keys) {
      logger.debug("GenericRepository::delete(Collection keys)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            for (Serializable key : (Collection<Serializable>) keys) {
               IDTO value = session.load(valueClass, key);
               session.delete(value);
            }
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void deleteTransactional(Serializable key) {
      logger.debug("GenericRepository::deleteTransactional(Serializable key)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            IDTO value = session.load(valueClass, key);
            session.delete(value);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void deleteTransactional(Collection keys) {
      logger.debug("GenericRepository::deleteTransactional(Collection keys)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            for (Serializable key : (Collection<Serializable>) keys) {
               IDTO value = session.load(valueClass, key);
               session.delete(value);
            }
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void delete(IDTO value) {
      logger.debug("GenericRepository::delete(IDTO value)");
      execute(new ICallbackDAO() {
         @Override
         public void execute() {
            session.delete(value);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public void deleteTransactional(IDTO value) {
      logger.debug("GenericRepository::deleteTransactional(IDTO value)");
      executeTransactional(new ICallbackDAO() {
         @Override
         public void execute() {
            session.delete(value);
         }

         @Override
         public void onError() { }
      });
   }

   @Override
   public Optional<Collection<IDTO>> get() {
      logger.debug("GenericRepository::get()");
      Optional<Collection<IDTO>> collectionOptional;
      begin();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<? extends IDTO> criteria = builder.createQuery(valueClass);
      criteria.from(valueClass);
      collectionOptional = Optional.of((Collection<IDTO>) session.createQuery(criteria).getResultList());
      kill();
      return collectionOptional;
   }

   @Override
   public Optional<IDTO> get(Serializable key) {
      logger.debug("GenericRepository::get(Serializable key)");
      Optional<IDTO> valueOptional;
      begin();
      valueOptional = Optional.of(session.load(valueClass, key));
      kill();
      return valueOptional;
   }

   @Override
   public Optional<Collection<IDTO>> get(Collection keys) {
      logger.debug("GenericRepository::get(Collection keys)");
      Optional<Collection<IDTO>> collectionOptional;
      begin();

      MultiIdentifierLoadAccess<? extends IDTO> multiLoadAccess = session.byMultipleIds(valueClass);
      List<? extends IDTO> results = multiLoadAccess.multiLoad(new ArrayList<Serializable>(keys));

      collectionOptional = Optional.of(results.stream().map(v -> valueClass.cast(v)).collect(Collectors.toList()));
      kill();
      return collectionOptional;
   }

   @Override
   public Optional<Collection<IDTO>> get(String filter) {
      logger.debug("GenericRepository::get(String filter)");
      Optional<Collection<IDTO>> collectionOptional;
      begin();

      String QUERY = "from :tableName where :filter";
      QUERY = QUERY.replace(":tableName", valueClass.getName());
      QUERY = QUERY.replace(":filter", filter);

      collectionOptional = Optional.of((Collection<IDTO>) session.createQuery(QUERY).getResultList());
      kill();
      return collectionOptional;
   }

   /**
    * Skeleton of a typical non-transactional method
    * @param _callback
    */
   protected final void execute(ICallbackDAO _callback) {
      begin();
      closeManually(true);
      try {
         _callback.execute();
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
         _callback.onError();
      } finally {
         close();
      }
   }

   /**
    * Skeleton of a typical transactional method
    * @param _callback
    */
   protected final void executeTransactional(ICallbackDAO _callback) {
      begin();
      closeManually(true);
      transaction = null;
      try {
         transaction = session.beginTransaction();
         _callback.execute();
         transaction.commit();
      } catch (Exception e) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(e.getMessage(), e);
         _callback.onError();
      } finally {
         close();
      }
   }
}
