package ru.pp.library.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.pp.library.entities.Book;
import ru.pp.library.entities.LibraryCard;
import ru.pp.library.entities.Record;
import ru.pp.library.exceptions.NotFoundException;
import ru.pp.library.repositories.BooksRepository;
import ru.pp.library.repositories.RecordRepository;

@Service
public class BooksService {

    private BooksRepository booksRepository;
    private RecordRepository recordRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, RecordRepository recordRepository) {
        this.booksRepository = booksRepository;
        this.recordRepository = recordRepository;
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
    public void assign(int id, LibraryCard libraryCard) {
        throwNotFoundIfNotExist(id);
        Book book = booksRepository.findById(id).get();
        book.setCurrentOwner(libraryCard);

        Record record = new Record();
        record.setBook(book);
        record.setLibraryCard(libraryCard);
        record.setTakenAt(LocalDate.now());

        recordRepository.save(record);
    }

    @Transactional
    public void release(int id) {
        throwNotFoundIfNotExist(id);
        Book book = booksRepository.findById(id).get();
        book.setCurrentOwner(null);

        recordRepository
                .findByBook_IdAndReturnedAtIsNull(book.getId())
                .get()
                .setReturnedAt(LocalDate.now());
    }

    private void throwNotFoundIfNotExist(int id) {
        if(!booksRepository.existsById(id)) {
            throw new NotFoundException();
        }
    }
}
