package ru.pp.library.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.pp.library.entities.Book;
import ru.pp.library.entities.Person;
import ru.pp.library.exceptions.NotFoundException;
import ru.pp.library.repositories.BooksRepository;

@Service
public class BooksService {

    private BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        throwNotFoundIfNotExist(id);
        booksRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Book book) {
        Optional<Book> oldBook = booksRepository.findById(id);
        if(oldBook.isPresent()) {
            book.setId(id);
            book.setCurrentOwner(oldBook.get().getCurrentOwner());
            booksRepository.save(book);
        } else {
            throw new NotFoundException();
        }
    }

    @Transactional
    public void assign(int id, Person person) {
        throwNotFoundIfNotExist(id);

        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setCurrentOwner(person);
                    book.setTakenAt(LocalDate.now());
                });
    }

    @Transactional
    public void release(int id) {
        throwNotFoundIfNotExist(id);

        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setCurrentOwner(null);
                    book.setTakenAt(null);
                });
    }

    private void throwNotFoundIfNotExist(int id) {
        if(!booksRepository.existsById(id)) {
            throw new NotFoundException();
        }
    }
}
