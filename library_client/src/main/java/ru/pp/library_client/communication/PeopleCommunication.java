package ru.pp.library_client.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.pp.library_client.dto.PersonDTO;
import ru.pp.library_client.dto.RichPersonDTO;

@Component
public class PeopleCommunication extends Communication {

    @Autowired
    public PeopleCommunication(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public PersonDTO[] getPeople() {
        return RestTemplate.getForObject(ServiceUrl + "/people", PersonDTO[].class);
    }

    public RichPersonDTO getPerson(int id) {
        return RestTemplate.getForObject(ServiceUrl + "/people/" + id, RichPersonDTO.class);
    }

    public void save(RichPersonDTO richPersonDTO) {
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
