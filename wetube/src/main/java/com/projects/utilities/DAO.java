package com.projects.utilities;

import com.projects.abstractions.AbstractDTO;
import com.projects.interfaces.IDAO;
import com.projects.interfaces.ITransactionalDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public class DAO implements IDAO, ITransactionalDAO {
   private static Logger logger = LogManager.getLogger(DAO.class);

   protected Session session = null;
   protected Transaction transaction = null;

   private String _tableName = "";

   public DAO(String tableName) {
      this._tableName = tableName;
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
   public void persist(AbstractDTO value) {
      logger.info("DAO.persist(AbstractDTO value)");
      beginSession();
      session.save(value);
      closeSession();
   }

   @Override
   public void persistTransactional(AbstractDTO value) {
      logger.info("DAO.persistTransactional(AbstractDTO value)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         session.save(value);
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
   public AbstractDTO merge(AbstractDTO value) {
      AbstractDTO copy;
      logger.info("DAO.merge(AbstractDTO value)");
      beginSession();
      copy = (AbstractDTO) session.merge(value);
      closeSession();
      return copy;
   }

   @Override
   public AbstractDTO mergeTransactional(AbstractDTO value) {
      AbstractDTO copy = null;
      logger.info("DAO.mergeTransactional(AbstractDTO value)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         copy = (AbstractDTO) session.merge(value);
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
   public void delete(Serializable key) {
      logger.info("DAO.delete(Serializable key)");
      beginSession();
      AbstractDTO value = session.load(AbstractDTO.class, key);
      session.delete(value);
      closeSession();
   }

   @Override
   public void deleteTransactional(Serializable key) {
      logger.info("DAO.deleteTransactional(Serializable key)");
      beginSession();
      transaction = null;
      try {
         transaction = session.beginTransaction();
         AbstractDTO value = session.load(AbstractDTO.class, key);
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
   public void delete(AbstractDTO value) {
      logger.info("DAO.delete(AbstractDTO value)");
      beginSession();
      session.delete(value);
      closeSession();
   }

   @Override
   public void deleteTransactional(AbstractDTO value) {
      logger.info("DAO.deleteTransactional(AbstractDTO value)");
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
   public Optional<Collection<AbstractDTO>> get() {
      Optional<Collection<AbstractDTO>> collectionOptional;
      beginSession();
      collectionOptional = Optional.of(session.createQuery("from ".concat(_tableName)).getResultList());
      closeSession();
      return collectionOptional;
   }

   @Override
   public Optional<AbstractDTO> get(Serializable key) {
      Optional<AbstractDTO> valueOptional;
      beginSession();
      valueOptional = Optional.of(session.load(AbstractDTO.class, key));
      closeSession();
      return valueOptional;
   }
}
