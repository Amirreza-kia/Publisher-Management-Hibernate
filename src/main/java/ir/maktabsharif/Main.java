package ir.maktabsharif;

import ir.maktabsharif.model.Author;
import ir.maktabsharif.repository.AuthorRepository;
import ir.maktabsharif.repository.impl.AuthorRepositoryImpl;
import ir.maktabsharif.utill.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        System.out.println("NIGGER");
        EntityManagerProvider entityManagerProvider = new EntityManagerProvider();
        AuthorRepository authorRepository = new AuthorRepositoryImpl(entityManagerProvider);
        Author author = new Author();
        author.setFirstname("sadegh");
        author.setLastname("hedayat");
        author.setId(3L);
        authorRepository.saveOrUpdate(author);

    }
}
