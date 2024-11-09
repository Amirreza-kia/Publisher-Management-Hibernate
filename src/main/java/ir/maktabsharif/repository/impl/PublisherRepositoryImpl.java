package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Publisher;
import ir.maktabsharif.repository.PublisherRepository;
import ir.maktabsharif.utill.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class PublisherRepositoryImpl implements PublisherRepository {
    private EntityManagerProvider entityManagerProvider;
    public void saveOrUpdate(Publisher object) {
        if (object.getId() == null) {
            persist(object);
        }else {
            update(object);
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Publisher author = entityManager.find(Publisher.class, id);
            entityManager.remove(author);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        return Optional.ofNullable(entityManagerProvider.getEntityManager().find(Publisher.class, id));
    }

    @Override
    public List<Publisher> getAll() {
        return entityManagerProvider.getEntityManager().createQuery("select a from Publisher a").getResultList();
    }
    public void persist(Publisher author) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(author);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
    }
    public void update(Publisher author) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Optional<Publisher> author1 = findById(author.getId());
        try{
            transaction.begin();
            entityManager.merge(author1);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
    }
}
