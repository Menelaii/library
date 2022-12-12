package ru.pp.library_client.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.pp.library_client.dto.GenreDTO;
import ru.pp.library_client.models.Genre;

@Component
public class GenresCommunication extends Communication {

    @Autowired
    public GenresCommunication(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public GenreDTO[] getGenres() {
        return RestTemplate.getForObject(ServiceUrl + "/genres", GenreDTO[].class);
    }

    public GenreDTO getGenre(int id) {
        return RestTemplate.getForObject(ServiceUrl + "/genres/" + id, GenreDTO.class);
    }

    public void save(GenreDTO genreDTO) {
        if(genreDTO.getId() == null){
            RestTemplate.postForObject(ServiceUrl + "/genres", genreDTO, String.class);
        } else {
            RestTemplate.patchForObject(ServiceUrl + "/genres/" + genreDTO.getId(), genreDTO, String.class);
        }
    }

    public void delete(int id) {
        RestTemplate.delete(ServiceUrl + "/genres/" + id);
    }
}


