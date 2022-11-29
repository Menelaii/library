package ru.pp.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pp.library.entities.Genre;
import ru.pp.library.exceptions.NotFoundException;
import ru.pp.library.repositories.GenresRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GenresService {

    private GenresRepository genresRepository;

    @Autowired
    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public List<Genre> findAll() {
        return genresRepository.findAll();
    }

    public Genre findOne(int id) {
        Optional<Genre> genre = genresRepository.findById(id);
        return genre.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void save(Genre genre) {
        genresRepository.save(genre);
    }

    @Transactional
    public void delete(int id) {
        throwNotFoundIfNotExists(id);
        genresRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Genre genre) {
        throwNotFoundIfNotExists(id);
        genre.setId(id);
        genresRepository.save(genre);
    }

    private void throwNotFoundIfNotExists(int id) {
        if (!genresRepository.existsById(id)) {
            throw new NotFoundException();
        }
    }
}
