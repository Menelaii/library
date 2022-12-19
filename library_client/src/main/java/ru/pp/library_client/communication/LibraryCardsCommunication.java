package ru.pp.library_client.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.pp.library_client.dto.LibraryCardDTO;
import ru.pp.library_client.dto.RichLibraryCardDTO;

@Component
public class LibraryCardsCommunication extends Communication {

    @Autowired
    public LibraryCardsCommunication(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public LibraryCardDTO[] getLibraryCards() {
        return RestTemplate.getForObject(ServiceUrl + "/people", LibraryCardDTO[].class);
    }

    public RichLibraryCardDTO getPerson(int id) {
        return RestTemplate.getForObject(ServiceUrl + "/people/" + id, RichLibraryCardDTO.class);
    }

    public void save(RichLibraryCardDTO richPersonDTO) {
        if(richPersonDTO.getId() == null){
            RestTemplate.postForObject(ServiceUrl + "/people", richPersonDTO, String.class);
        } else {
            RestTemplate.patchForObject(ServiceUrl + "/people/" + richPersonDTO.getId(), richPersonDTO, String.class);
        }
    }

    public void delete(int id) {
        RestTemplate.delete(ServiceUrl + "/people/" + id);
    }
}
