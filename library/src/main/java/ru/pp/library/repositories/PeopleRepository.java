package ru.pp.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.pp.library.entities.LibraryCard;

@Repository
public interface PeopleRepository extends JpaRepository<LibraryCard, Integer>{
}
