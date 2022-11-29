package ru.pp.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pp.library.entities.Genre;

@Repository
public interface GenresRepository extends JpaRepository<Genre, Integer> {
}
