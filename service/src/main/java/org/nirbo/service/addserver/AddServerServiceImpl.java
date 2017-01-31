package org.nirbo.service.addserver;

import org.nirbo.model.entity.Server;
import org.nirbo.repository.server.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddServerServiceImpl implements AddServerService {

    @Autowired
    private ServerRepository serverRepository;

    public void saveServer(Server serverDAO) {

        Server server = new Server();
        server.setServerName(serverDAO.getServerName());
        server.setServerMgmtIP(serverDAO.getServerMgmtIP());
        server.setServerDataNet1(serverDAO.getServerDataNet1());
        server.setServerDataNet2(serverDAO.getServerDataNet2());
        server.setServerLocation(serverDAO.getServerLocation());
        server.setServerOwner(serverDAO.getServerOwner());

        serverRepository.save(server);
    }
}
