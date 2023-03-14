/* Використовуючи MySQL Workbench переписати БД так, щоб одну книгу могли написати кілька авторів,
також один автор міг написати кілька книг, - реалізувати зв'язок many-to-many. */

use library;
create table many_to_many (
serialId bigint not null primary key,
authorId bigint not null,
bookId bigint not null,
book_year year,
foreign key (authorId) references author(id) on delete cascade,
foreign key (bookId) references book(id) on delete cascade
);