package ru.pp.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pp.library.entities.Record;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    Optional<Record> findByBook_IdAndReturnedAtIsNull(Integer id);
}
