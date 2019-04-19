package com.projects.utilities;

import com.projects.interfaces.IDAO;
import com.projects.interfaces.IDTO;
import com.projects.interfaces.ITransactionalDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
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

public class GenericRepository implements IDAO, ITransactionalDAO {
   private static Logger logger = LogManager.getLogger(GenericRepository.class);

   protected Session session;
   protected Transaction transaction;

   private Class<? extends IDTO> valueClass;

   public GenericRepository(@NotNull Class<? extends IDTO> valueClass) {
      this.valueClass = valueClass;
   }

   public void beginSession() {
      session = HibernateUtil.getFactory().openSession();
   }

   public void closeSession() {
      if (session != null) {
         session.close();
         session = null;
      }
   }

   @Override
   public void persist(IDTO value) {
      logger.debug("GenericRepository::persist(IDTO value)");
      beginSession();
      session.persist(value);
      closeSession();
   }

   @Override
   public void persist(Collection values) {
      logger.debug("GenericRepository::persist(Collection values)");
      beginSession();
      for (IDTO value : (Collection<IDTO>) values) {
         session.persist(value);
      }
      closeSession();
   }

   @Override
   public void persistTransactional(IDTO value) {
      logger.debug("GenericRepository::persistTransactional(IDTO value)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         session.persist(value);
         transaction.commit();
      } catch (HibernateException he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         closeSession();
      }
   }

   @Override
   public void persistTransactional(Collection values) {
      logger.debug("GenericRepository::persistTransactional(Collection values)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         for (IDTO value : (Collection<IDTO>) values) {
            session.persist(value);
         }
         transaction.commit();
      } catch (HibernateException he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         closeSession();
      }
   }

   @Override
   public void update(IDTO value) {
      logger.debug("GenericRepository::update(IDTO value)");
      beginSession();
      session.update(value);
      closeSession();
   }

   @Override
   public void update(Collection values) {
      logger.debug("GenericRepository::update(Collection values)");
      beginSession();
      for (IDTO value : (Collection<IDTO>) values) {
         session.update(value);
      }
      closeSession();
   }

   @Override
   public void updateTransactional(IDTO value) {
      logger.debug("GenericRepository::updateTransactional(IDTO value)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         session.update(value);
         transaction.commit();
      } catch (HibernateException he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         closeSession();
      }
   }

   @Override
   public void updateTransactional(Collection values) {
      logger.debug("GenericRepository::updateTransactional(Collection values)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         for (IDTO value : (Collection<IDTO>) values) {
            session.update(value);
         }
         transaction.commit();
      } catch (HibernateException he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         closeSession();
      }
   }

   @Override
   public void delete(Serializable key) {
      logger.debug("GenericRepository::delete(Serializable key)");
      beginSession();
      IDTO value = session.load(valueClass, key);
      session.delete(value);
      closeSession();
   }

   @Override
   public void delete(Collection keys) {
      logger.debug("GenericRepository::delete(Collection keys)");
      beginSession();
      for (Serializable key : (Collection<Serializable>) keys) {
         IDTO value = session.load(valueClass, key);
         session.delete(value);
      }
      closeSession();
   }

   @Override
   public void deleteTransactional(Serializable key) {
      logger.debug("GenericRepository::deleteTransactional(Serializable key)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         IDTO value = session.load(valueClass, key);
         session.delete(value);
         transaction.commit();
      } catch (HibernateException he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         closeSession();
      }
   }

   @Override
   public void deleteTransactional(Collection keys) {
      logger.debug("GenericRepository::deleteTransactional(Collection keys)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         for (Serializable key : (Collection<Serializable>) keys) {
            IDTO value = session.load(valueClass, key);
            session.delete(value);
         }
         transaction.commit();
      } catch (HibernateException he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         closeSession();
      }
   }

   @Override
   public void delete(IDTO value) {
      logger.debug("GenericRepository::delete(IDTO value)");
      beginSession();
      session.delete(value);
      closeSession();
   }

   @Override
   public void deleteTransactional(IDTO value) {
      logger.debug("GenericRepository::deleteTransactional(IDTO value)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         session.delete(value);
         transaction.commit();
      } catch (HibernateException he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         closeSession();
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public Optional<Collection<IDTO>> get() {
      logger.debug("GenericRepository::get()");
      Optional<Collection<IDTO>> collectionOptional;
      beginSession();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<? extends IDTO> criteria = builder.createQuery(valueClass);
      criteria.from(valueClass);
      collectionOptional = Optional.of((Collection<IDTO>) session.createQuery(criteria).getResultList());
      closeSession();
      return collectionOptional;
   }

   @Override
   public Optional<IDTO> get(Serializable key) {
      logger.debug("GenericRepository::get(Serializable key)");
      Optional<IDTO> valueOptional;
      beginSession();
      valueOptional = Optional.of(session.load(valueClass, key));
      closeSession();
      return valueOptional;
   }

   @Override
   public Optional<Collection<IDTO>> get(Collection keys) {
      logger.debug("GenericRepository::get(Collection keys)");
      Optional<Collection<IDTO>> collectionOptional;
      beginSession();

      MultiIdentifierLoadAccess<? extends IDTO> multiLoadAccess = session.byMultipleIds(valueClass);
      List<? extends IDTO> results = multiLoadAccess.multiLoad(new ArrayList<Serializable>(keys));

      collectionOptional = Optional.of(results.stream().map(v -> valueClass.cast(v)).collect(Collectors.toList()));
      closeSession();
      return collectionOptional;
   }
}
