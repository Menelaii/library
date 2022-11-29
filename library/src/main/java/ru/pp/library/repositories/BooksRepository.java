package ru.pp.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.pp.library.entities.Book;
import ru.pp.library.entities.Person;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
