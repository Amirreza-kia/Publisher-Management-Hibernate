package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.BookRepository;
import ir.maktabsharif.utill.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {
    private EntityManagerProvider entityManagerProvider;
    @Override
    public void saveOrUpdate(Book object) {
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
            Optional<Book> book = findById(id);
            entityManager.remove(book);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManagerProvider.getEntityManager().find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        return entityManagerProvider.getEntityManager().createQuery("select a from Book a").getResultList();
    }
    public void persist(Book author) {
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
    public void update(Book author) {
        EntityManager entityManager = entityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Optional<Book> author1 = findById(author.getId());
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
