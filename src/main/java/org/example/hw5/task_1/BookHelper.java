package org.example.hw5.task_1;

import jakarta.persistence.criteria.CriteriaQuery;
import org.example.hw5.task_1.entity.Author;
import org.example.hw5.task_1.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

class BookHelper {
    private SessionFactory sessionFactory;
    public BookHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public List<Book> getBookList() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<Book> criteriaQuery = session.getCriteriaBuilder().createQuery(Book.class);
        criteriaQuery.from(Book.class);

        List<Book> bookList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return bookList;
    }

    public Book getBookById(long id) {
        Session session = sessionFactory.openSession();
        Book book = (Book) session.get(Book.class, id);
        return book;
    }

    public Book addBook(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        return book;
    }

    public Book addBookById(long id, Book book){

        Session session = sessionFactory.openSession();
        Book book1 = session.get(Book.class, id);
        String name = book.getName();
        book1.setName(name);
        session.beginTransaction();
        session.save(book1);
        session.getTransaction().commit();
        session.close();
        return book;

    }
}
