package org.nirbo.service.showservers;

import org.nirbo.model.entity.Server;

import java.util.List;

public interface ShowServersService {
    List<Server> getAllServers();

    Server getServerById(Long id);
}
