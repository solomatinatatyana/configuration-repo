package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookComments;
import ru.otus.homework.service.books.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookApplicationCommands {
    private final BookService bookService;

    private long id;
    private String bookTitle;
    private String genreName;
    private long author;
    private long genre;


    @ShellMethod(value = "getting book by id", key = {"getBookById", "gbId"})
    public Book getBookById(@ShellOption long id){
        this.id = id;
        return bookService.getBookById(id);
    }

    @ShellMethod(value = "getting book by title", key = {"getBookByTitle", "gbt"})
    public Book getBookByTitle(@ShellOption String bookTitle){
        this.bookTitle = bookTitle;
        return bookService.getBookByTitle(bookTitle);
    }

    @ShellMethod(value = "get all books", key = {"getAllBooks", "gab"})
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "insert new book", key = {"insertBook", "ib"})
    public void insertBook(@ShellOption long id,
                           @ShellOption String title,
                           @ShellOption long authorId,
                           @ShellOption long genreId){
        this.id = id;
        this.author = authorId;
        this.genre = genreId;
        this.bookTitle = title;
        bookService.insertBook(id,title,author,genre);
    }

    @ShellMethod(value = "delete book by id", key = {"deleteBookById", "dbId"})
    public void deleteBookById(@ShellOption long id){
        bookService.deleteBookById(id);
    }

    @ShellMethod(value = "delete book by title", key = {"deleteBookByTitle", "dbt"})
    public void deleteBookById(@ShellOption String title){
        bookService.deleteBookByTitle(title);
    }

    @ShellMethod(value = "get all bookCommentsCount", key = {"getAllBooksCommentsCount", "gabcc"})
    public List<BookComments> getAllBooksCommentsCount(){
        return bookService.getBookCommentsCount();
    }

    @ShellMethod(value = "get all books with genres", key = {"getAllBooksWithGenres", "gabwg"})
    public List<Book> getAllBooksWithGenres(@ShellOption String genre) {
        this.genreName = genre;
        return bookService.getAllBooksWithGivenGenre(genre);
    }
}
