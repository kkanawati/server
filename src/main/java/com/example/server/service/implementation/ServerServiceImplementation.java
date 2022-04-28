package com.example.server.service.implementation;

import com.example.server.enumeration.Status;
import com.example.server.model.Server;
import com.example.server.repo.ServerRepo;
import com.example.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.example.server.enumeration.Status.SERVER_DOWN;
import static com.example.server.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class ServerServiceImplementation implements ServerService {

    private final ServerRepo serverRepo;
    @Override
    public Server create(Server server) {
       log.info("Saving new server : {}",server.getName());
       server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAdress) throws IOException {
        log.info("pinging server IP : {}",ipAdress );
        Server server = serverRepo.findByIpAdress(ipAdress);
        InetAddress address = InetAddress.getByName(ipAdress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> List(int limit) {
        log.info("Fetchin all servers");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetchin server by id: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server : {}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public boolean delete(Long id) {
        log.info("Deleting server by ID: {}",id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl() {
        String[] imagename = {"server1.png","server2.png","server3.png","server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("C:\\Users\\khale\\Pictures\\server" + imagename[new Random().nextInt(4)]).toUriString();
    }

}
