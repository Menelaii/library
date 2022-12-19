package ru.pp.library.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.pp.library.entities.LibraryCard;
import ru.pp.library.entities.Record;
import ru.pp.library.exceptions.NotFoundException;
import ru.pp.library.repositories.PeopleRepository;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    @Value("${books.expiredAfter}")
    private int expiredAfterInDays;
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<LibraryCard> findAll() {
        return peopleRepository.findAll();
    }

    public LibraryCard findOne(int id) {
        Optional<LibraryCard> libraryCard = peopleRepository.findById(id);

        if (libraryCard.isPresent()) {
            List<Record> records = libraryCard.get().getRecords();
            for (var record : records) {
                if(record.getReturnedAt() != null)
                    continue;

                if(DAYS.between(record.getTakenAt(), LocalDate.now()) > expiredAfterInDays) {
                    record.getBook().setExpired(true);
                }
            }
        }

        return libraryCard.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void save(LibraryCard libraryCard) {
        peopleRepository.save(libraryCard);
    }

    @Transactional
    public void delete(int id) {
        throwNotFoundIfNotExist(id);
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, LibraryCard libraryCard) {
        throwNotFoundIfNotExist(id);
        libraryCard.setId(id);
        peopleRepository.save(libraryCard);
    }

    private void throwNotFoundIfNotExist(int id) {
        if(!peopleRepository.existsById(id)) {
            throw new NotFoundException();
        }
    }
}
