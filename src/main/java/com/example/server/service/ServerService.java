package com.example.server.service;

import com.example.server.model.Server;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAdress) throws IOException;
    Collection<Server> List(int limit);
    Server get(Long id);
    Server update(Server server);
    boolean delete(Long id);
}
