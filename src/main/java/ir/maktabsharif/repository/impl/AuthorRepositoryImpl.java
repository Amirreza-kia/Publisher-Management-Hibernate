package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Author;
import ir.maktabsharif.repository.AuthorRepository;
import ir.maktabsharif.utill.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class AuthorRepositoryImpl implements AuthorRepository {
    private EntityManagerProvider entityManagerProvider;
    public AuthorRepositoryImpl(EntityManagerProvider entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    @Override
    public void saveOrUpdate(Author object) {
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
            Optional<Author> author = findById(id);
            entityManager.remove(author);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(entityManagerProvider.getEntityManager().find(Author.class, id));
    }

    @Override
    public List<Author> getAll() {
        return entityManagerProvider.getEntityManager().createQuery("select a from Author a").getResultList();
    }

    public void persist(Author author) {
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
    public void update(Author author) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Optional<Author> author1 = findById(author.getId());
        if (author1.isPresent()) {
            try{
                transaction.begin();
                entityManager.merge(author);
                transaction.commit();
            }catch(Exception e){
                transaction.rollback();
            }finally {
                entityManager.close();
            }
        }else System.out.println("No author found");

    }
}
