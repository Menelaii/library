package ru.pp.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pp.library.entities.Author;
import ru.pp.library.exceptions.NotFoundException;
import ru.pp.library.repositories.AuthorsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthorsService {

    private AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public List<Author> findAll() {
        return authorsRepository.findAll();
    }

    public Author findOne(int id) {
        Optional<Author> author = authorsRepository.findById(id);
        return author.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void save(Author author) {
        authorsRepository.save(author);
    }

    @Transactional
    public void delete(int id) {
        throwNotFoundIfNotExists(id);
        authorsRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Author author) {
        throwNotFoundIfNotExists(id);
        author.setId(id);
        authorsRepository.save(author);
    }

    private void throwNotFoundIfNotExists(int id) {
        if (!authorsRepository.existsById(id)) {
            throw new NotFoundException();
        }
    }
}
