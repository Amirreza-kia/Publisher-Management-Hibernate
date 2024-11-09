package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.NewsPaper;
import ir.maktabsharif.repository.NewsPaperRepository;
import ir.maktabsharif.utill.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class NewsPaperRepositoryImpl implements NewsPaperRepository {
    private EntityManagerProvider entityManagerProvider;
    public void saveOrUpdate(NewsPaper object) {
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
            NewsPaper author = entityManager.find(NewsPaper.class, id);
            entityManager.remove(author);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<NewsPaper> findById(Long id) {
        return Optional.ofNullable(entityManagerProvider.getEntityManager().find(NewsPaper.class, id));
    }

    @Override
    public List<NewsPaper> getAll() {
        return entityManagerProvider.getEntityManager().createQuery("select a from NewsPaper a").getResultList();
    }
    public void persist(NewsPaper author) {
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
    public void update(NewsPaper author) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Optional<NewsPaper> author1 = findById(author.getId());
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
