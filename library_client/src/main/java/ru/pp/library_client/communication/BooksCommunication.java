package ru.pp.library_client.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.pp.library_client.dto.BookDTO;
import ru.pp.library_client.dto.PersonDTO;
import ru.pp.library_client.dto.RichBookDTO;

@Component
public class BooksCommunication extends Communication {
    @Autowired
    public BooksCommunication(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public BookDTO[] getBooks() {
        return RestTemplate.getForObject(ServiceUrl + "/books", BookDTO[].class);
    }

    public RichBookDTO getBook(int id) {
        return RestTemplate.getForObject(ServiceUrl + "/books/" + id, RichBookDTO.class);
    }

    public void save(RichBookDTO richBookDTO) {
        if(richBookDTO.getId() == null) {
            RestTemplate.postForObject(ServiceUrl + "/books", richBookDTO, String.class);
        } else {
            RestTemplate.patchForObject(ServiceUrl + "/books/" + richBookDTO.getId(),
                    richBookDTO, String.class);
        }
    }

    public void delete(int id) {
        RestTemplate.delete(ServiceUrl + "/books/" + id);
    }

    public void assign(int id, PersonDTO personDTO) {
        RestTemplate.patchForObject(ServiceUrl + "/books/" + id + "/assign",
                personDTO, String.class);
    }

    public void release(int id) {
        RestTemplate.patchForObject(ServiceUrl + "/books/" + id + "/release",
                null, String.class);
    }
}
