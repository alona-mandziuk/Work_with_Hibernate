package org.example.hw6.task1;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.hw6.task1.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

class BookHelper {
    private SessionFactory sessionFactory;

    BookHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    List<Book> getBookList() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<Book> criteriaQuery = session.getCriteriaBuilder().createQuery(Book.class);
        criteriaQuery.from(Book.class);
        List<Book> bookList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return bookList;
    }

    Book getBookById(long id) {
        Session session = sessionFactory.openSession();
        Book book = (Book) session.get(Book.class, id);
        return book;
    }

    Book addBook(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        return book;
    }

    void deleteBookById(long id) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        session.beginTransaction();
        session.remove(book);
        session.getTransaction().commit();
        session.close();
    }

    void deleteBookByAuthorName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String delete = "delete from Book  where author in (from Author where name = :name1)";

        Query query = session.createQuery(delete);
        query.setParameter("name1", name);
        int upd = query.executeUpdate();
        transaction.commit();
        session.close();
    }


}
