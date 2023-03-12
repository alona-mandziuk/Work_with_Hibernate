package org.example.hw6.task1;


import org.example.hw6.task1.entity.Book;
import org.example.hw6.task1.entity.Author;
import org.jboss.logging.Logger;

class Main {
    private static final Logger LOG1 = Logger.getLogger(AuthorHelper.class.getName());
    private static final Logger LOG2 = Logger.getLogger(BookHelper.class.getName());

    public static void main(String[] args) {
        AuthorHelper authorHelper = new AuthorHelper();
        Author author1 = new Author();
        author1.setName("Den");
        author1.setLastName("Brown");
        Author author2 = new Author();
        author2.setName("Agatha");
        author2.setLastName("Christi");
        authorHelper.addAuthor(author1);
        authorHelper.addAuthor(author2);

        BookHelper bookHelper = new BookHelper();
        Book book1 = new Book();
        book1.setName("Da Vinci Code");
        book1.setAuthor(author1);
        Book book2 = new Book();
        book2.setName("The source");
        book2.setAuthor(author1);
        Book book3 = new Book();
        book3.setName("The Murder of Roger Ackroyd");
        book3.setAuthor(author2);
        Book book4 = new Book();
        book4.setName("The Big Four");
        book4.setAuthor(author2);
        Book book5 = new Book();
        book5.setName("Murder on the Orient Express");
        book5.setAuthor(author2);

        bookHelper.addBook(book1);
        bookHelper.addBook(book2);
        bookHelper.addBook(book3);
        bookHelper.addBook(book4);

      //  authorHelper.changeName();

      //  bookHelper.deleteBookById(4);
       bookHelper.deleteBookByAuthorName("Agatha");


    }
}
