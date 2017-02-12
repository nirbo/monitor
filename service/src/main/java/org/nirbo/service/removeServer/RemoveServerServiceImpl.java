package org.nirbo.service.removeServer;

import org.nirbo.model.entity.Server;
import org.nirbo.repository.server.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveServerServiceImpl implements RemoveServerService {

    @Autowired
    private ServerRepository serverRepository;

    public void removeServer(Server server) {
        serverRepository.delete(server);
    }
}
