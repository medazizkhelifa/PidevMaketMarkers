package tn.esprit.spring.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tn.esprit.spring.serviceInterface.IHttpHelper;

@Service
public class HttpHelper implements IHttpHelper {

    @Override
    public <T> T get(String url, Class<T> responseType, Object... uriVariables) {//il peut ajouter une infiniter de parametre
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> result = restTemplate.getForEntity(url, responseType, uriVariables);
        return result.getBody();
    }

    @Override
    public <T> T post(String url, Object request, Class<T> responseType, Object... uriVariables) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> result = restTemplate.postForEntity(url, request, responseType, uriVariables);
        return result.getBody();
    }

}