package ru.pp.library_client.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pp.library_client.dto.AuthorDTO;

@Component
public class AuthorsCommunication extends Communication{

    @Autowired
    public AuthorsCommunication(org.springframework.web.client.RestTemplate restTemplate) {
        super(restTemplate);
    }

    public AuthorDTO[] getAuthors() {
        return RestTemplate.getForObject(ServiceUrl + "/authors", AuthorDTO[].class);
    }

    public AuthorDTO getAuthor(int id) {
        return RestTemplate.getForObject(ServiceUrl + "/authors/" + id, AuthorDTO.class);
    }

    public void save(AuthorDTO authorDTO) {
        if(authorDTO.getId() == null) {
            RestTemplate.postForObject(ServiceUrl + "/authors",
                    authorDTO, String.class);
        } else {
            RestTemplate.patchForObject(ServiceUrl + "/authors/" + authorDTO.getId(),
                    authorDTO, String.class);
        }
    }

    public void delete(int id) {
        RestTemplate.delete(ServiceUrl + "/authors/" + id);
    }
}
