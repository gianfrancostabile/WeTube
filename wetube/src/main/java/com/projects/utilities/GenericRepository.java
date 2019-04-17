package com.projects.utilities;

import com.projects.interfaces.IDTO;
import com.projects.interfaces.IDAO;
import com.projects.interfaces.ITransactionalDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
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

   private String _tableName;

   private Class<? extends Serializable> keyClass;
   private Class<? extends IDTO> valueClass;

   public GenericRepository(@NotNull String tableName,
                            @NotNull Class<? extends Serializable> keyClass,
                            @NotNull Class<? extends IDTO> valueClass) {
      this._tableName = tableName;
      this.keyClass = keyClass;
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
      logger.info("GenericRepository.persist(IDTO value)");
      beginSession();
      session.persist(value);
      closeSession();
   }

   @Override
   public void persist(Collection values) {
      logger.info("GenericRepository.persist(Collection<IDTO> values)");
      beginSession();
      for (IDTO value : (Collection<IDTO>) values) {
         session.persist(value);
      }
      closeSession();
   }

   @Override
   public void persistTransactional(IDTO value) {
      logger.info("GenericRepository.persistTransactional(IDTO value)");
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
      logger.info("GenericRepository.persistTransactional(Collection<IDTO> values)");
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
   public IDTO merge(IDTO value) {
      logger.info("GenericRepository.merge(IDTO value)");
      IDTO copy;
      beginSession();
      copy = (IDTO) session.merge(value);
      closeSession();
      return copy;
   }

   @Override
   public Collection<IDTO> merge(Collection values) {
      logger.info("GenericRepository.merge(Collection<IDTO> values)");
      Collection<IDTO> copies = new ArrayList<>();
      beginSession();
      for (IDTO value : (Collection<IDTO>) values) {
         copies.add((IDTO) session.merge(value));
      }
      closeSession();
      return copies;
   }

   @Override
   public IDTO mergeTransactional(IDTO value) {
      logger.info("GenericRepository.mergeTransactional(IDTO value)");
      IDTO copy = null;
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         copy = (IDTO) session.merge(value);
         transaction.commit();
      } catch (HibernateException he) {
         if (transaction != null) {
            transaction.rollback();
         }
         logger.error(he.getMessage(), he);
      } finally {
         closeSession();
      }
      return copy;
   }

   @Override
   public Collection<IDTO> mergeTransactional(Collection values) {
      logger.info("GenericRepository.mergeTransactional(Collection<IDTO> values)");
      Collection<IDTO> copies = new ArrayList<>();
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         for (IDTO value : (Collection<IDTO>) values) {
            copies.add((IDTO) session.merge(value));
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
      return copies;
   }

   @Override
   public void delete(Serializable key) {
      logger.info("GenericRepository.delete(Serializable key)");
      beginSession();
      IDTO value = session.load(valueClass, key);
      session.delete(value);
      closeSession();
   }

   @Override
   public void delete(Collection keys) {
      logger.info("GenericRepository.delete(Collection<Serializable> keys)");
      beginSession();
      for (Serializable key : (Collection<Serializable>) keys) {
         IDTO value = session.load(valueClass, key);
         session.delete(value);
      }
      closeSession();
   }

   @Override
   public void deleteTransactional(Serializable key) {
      logger.info("GenericRepository.deleteTransactional(Serializable key)");
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
      logger.info("GenericRepository.deleteTransactional(Collection<Serializable> keys)");
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
      logger.info("GenericRepository.delete(IDTO value)");
      beginSession();
      session.delete(value);
      closeSession();
   }

   @Override
   public void deleteTransactional(IDTO value) {
      logger.info("GenericRepository.deleteTransactional(IDTO value)");
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
      Optional<IDTO> valueOptional;
      beginSession();
      valueOptional = Optional.of(session.load(valueClass, key));
      closeSession();
      return valueOptional;
   }

   @Override
   public Optional<Collection<IDTO>> get(Collection keys) {
      Optional<Collection<IDTO>> collectionOptional;
      beginSession();
      String in = ((Collection<Serializable>) keys).stream().map(v -> v.toString()).collect(Collectors.joining(","));
      String QUERY = new StringBuffer().append("from ").append(_tableName)
                                       .append(" where ID in (").append(in).append(")")
                                       .toString();
      List<IDTO> results = session.createQuery(QUERY).getResultList();
      collectionOptional = Optional.of(results.stream().map(v -> valueClass.cast(v)).collect(Collectors.toList()));
      closeSession();
      return collectionOptional;
   }
}
