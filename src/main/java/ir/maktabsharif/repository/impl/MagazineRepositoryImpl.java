package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Magazine;
import ir.maktabsharif.repository.MagazineRepository;
import ir.maktabsharif.utill.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class MagazineRepositoryImpl implements MagazineRepository {
    private EntityManagerProvider entityManagerProvider;
    public void saveOrUpdate(Magazine object) {
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
            Magazine author = entityManager.find(Magazine.class, id);
            entityManager.remove(author);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Magazine> findById(Long id) {
        return Optional.ofNullable(entityManagerProvider.getEntityManager().find(Magazine.class, id));
    }

    @Override
    public List<Magazine> getAll() {
        return entityManagerProvider.getEntityManager().createQuery("select a from Magazine a").getResultList();
    }
    public void persist(Magazine author) {
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
    public void update(Magazine author) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Optional<Magazine> author1 = findById(author.getId());
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
