package tn.esprit.spring.serviceInterface;

public interface IHttpHelper {
    <T> T get(String url, Class<T> responseType, Object... uriVariables);
    <T> T post(String url, Object request, Class<T> responseType, Object... uriVariables);
}