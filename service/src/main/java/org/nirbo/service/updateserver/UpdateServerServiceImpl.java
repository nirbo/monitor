package org.nirbo.service.updateserver;

import org.nirbo.model.entity.Server;
import org.nirbo.repository.server.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateServerServiceImpl implements UpdateServerService {

    @Autowired
    private ServerRepository serverRepository;

    public void updateServer(Server serverToUpdate) {
        serverRepository.saveAndFlush(serverToUpdate);
    }
}
