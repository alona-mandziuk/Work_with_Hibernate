package org.example.hw5.task_1;


import jakarta.persistence.criteria.CriteriaQuery;
import org.example.hw5.task_1.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AuthorHelper {
    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<Author> criteriaQuery = session.getCriteriaBuilder().createQuery(Author.class);
        criteriaQuery.from(Author.class);
        List<Author> authorList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id); // получение объекта по id
        session.close();
        return author;
    }

    public Author addAuthorCicle(Author author) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (int i = 0; i < 200; i++) {
            session.save(new Author("name" + i));
            if (i % 10 == 0) {
                session.flush();
            }
        }

        session.getTransaction().commit();
        session.close();
        return author;
    }

    public Author addAuthor(Author author) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
        return author;
    }

    public Author addAuthorById(long id, Author author) {

        Session session = sessionFactory.openSession();
        Author author1 = session.get(Author.class, id);
        String name = author.getName();
        String surname = author.getLastName();
        author1.setName(name);
        author1.setLastName(surname);
        session.beginTransaction();
        session.save(author1);
        session.getTransaction().commit();
        session.close();
        return author;
    }
}
