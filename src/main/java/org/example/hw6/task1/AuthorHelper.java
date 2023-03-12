package org.example.hw6.task1;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.example.hw6.task1.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

class AuthorHelper {
    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = criteriaQuery.from(Author.class);
        Selection[] selections = {root.get("id"), root.get("name")};
        criteriaQuery.select(criteriaBuilder.construct(Author.class, selections));
        Query query = session.createQuery(criteriaQuery);
        List<Author> authorList = query.getResultList();
        session.close();
        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }



    public Author addAuthor(Author author) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
       // session.close();
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

    public void changeName (){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String update = "update Author  a set a.name = '1' where length(a.lastName)  >= 7";
        int updateEntities = session.createQuery(update).executeUpdate();
        transaction.commit();
        session.close();
    }

}
