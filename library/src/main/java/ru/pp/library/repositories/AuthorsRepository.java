package ru.pp.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pp.library.entities.Author;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
}
