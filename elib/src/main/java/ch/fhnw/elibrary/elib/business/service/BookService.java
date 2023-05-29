package ch.fhnw.elibrary.elib.business.service;

import java.util.List;
import org.springframework.stereotype.Service;

import ch.fhnw.elibrary.elib.data.domain.Author;
import ch.fhnw.elibrary.elib.data.domain.Book;
import ch.fhnw.elibrary.elib.data.domain.Genre;
import ch.fhnw.elibrary.elib.data.repository.BookRepository;

// BookService class author @michimel

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();   
        return books;
    }
    
    // checks if the book with the given isbn already exists, if not, the book is saved
    public Book createBook(Book book) throws Exception {
        if (book.getIsbn() != null) {
            if (bookRepository.findByIsbn(book.getIsbn()) == null)
                return bookRepository.save(book);
            else
                throw new Exception("Book with ISBN " + book.getIsbn() + " already exists");
            
        }
        throw new Exception("Invalid ISBN");
    }

    

    public Book updateBook(Long bookID, Book bookDetails) {
        Book book = getBookById(bookID);
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublishYear(bookDetails.getPublishYear());
        book.setDescription(bookDetails.getDescription());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        return bookRepository.save(book);
    }

    /* the following methods are not used in the application, 
    as budibase provides the functionality to search via the filter function,
    but for completeness we provide the methods below */
    
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> getBooksByPublishYear(int publishYear) {
        return bookRepository.findByPublishYear(publishYear);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    private Book getBookById(Long bookID) {
        return bookRepository.findByBookID(bookID);
    }
}
