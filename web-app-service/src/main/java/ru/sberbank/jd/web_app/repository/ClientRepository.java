package ru.sberbank.jd.web_app.repository;

import org.springframework.stereotype.Repository;
import ru.sberbank.jd.web_app.entity.Client;
import ru.sberbank.jd.web_app.entity.Performer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientRepository {

    public Map<String, Client> clientStorage = new HashMap<>();

    public List<Client> findAll() {
        return new ArrayList<>(clientStorage.values());
    }

    /*public void save(Client client) {
        clientStorage.put(client.getId(), client);
    }

    public Client findById(String id) {
        return clientStorage.get(id);
    }

    public void delete(String id) {
        clientStorage.remove(id);
    }*/
}
