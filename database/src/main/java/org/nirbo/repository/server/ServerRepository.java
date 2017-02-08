package org.nirbo.repository.server;

import org.nirbo.model.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    @Query("select s from Server s order by s.serverMgmtIP")
    List<Server> getAllServers();

}
