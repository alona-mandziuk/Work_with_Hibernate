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

    public void changeName() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String update = "update Author  a set a.name = '1' where length(a.lastName)  >= 7";
        int updateEntities = session.createQuery(update).executeUpdate();
        transaction.commit();
        session.close();
    }

    /*
    З пакету ex_002_select_where написати окремий метод для виборки по пошуку виразу
    */

    public void getAuthorByLastName(String lastName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        criteriaQuery.select(authorRoot).where(criteriaBuilder.like(authorRoot.get("lastName"),
                lastName));

        Query query = session.createQuery(criteriaQuery);
        List<Author> authorList = query.getResultList();
        System.out.println(" ");
        System.out.println("--------------------------------------");
        System.out.println(" ");
        for (Author a : authorList) {
            System.out.println("Author ID: " + a.getId() + ": " + a.getName() + " "
                    + a.getLastName() + ".");
            System.out.println("Books: ");
            for (int i = 0; i < a.getBooks().size(); i++) {
                System.out.println(a.getBooks().get(i).getName());
            }
        }
        System.out.println(" ");
        System.out.println("--------------------------------------");
        System.out.println(" ");
        transaction.commit();
        session.close();
    }

    /*
    D пакеті ex_003_delete методи createCriteria і createCriteriaLogic переписати правильно.
    */

    public void deleteAuthorByLastName (String lastName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<Author> criteriaDelete = criteriaBuilder.createCriteriaDelete(Author.class);
        Root<Author> authorRoot = criteriaDelete.from(Author.class);

        criteriaDelete.where(criteriaBuilder.like(authorRoot.get("lastName"), lastName));
        Query query = session.createQuery(criteriaDelete);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

}
