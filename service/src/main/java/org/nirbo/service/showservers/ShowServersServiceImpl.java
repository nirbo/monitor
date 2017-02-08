package org.nirbo.service.showservers;

import org.nirbo.model.entity.Server;
import org.nirbo.repository.server.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServersServiceImpl implements ShowServersService {

    @Autowired
    private ServerRepository serverRepository;

    public List<Server> getAllServers() {
        return serverRepository.getAllServers();
    }
}
