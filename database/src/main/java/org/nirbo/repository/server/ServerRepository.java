package org.nirbo.repository.server;

import org.nirbo.model.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    @Query("SELECT s FROM Server s ORDER BY s.serverMgmtIP")
    List<Server> getAllServers();

//    @Modifying
//    @Query("UPDATE Server s SET s.serverName = :serverName, s.serverMgmtIP = :serverMgmtIP, s.serverDataNet1 = :serverDataNet1," +
//                                "s.serverDataNet2 = :serverDataNet2, s.serverLocation = :serverLocation, s.serverOwner = :serverOwner," +
//                                "WHERE s.id = :serverId")
//    void updateServerById(@Param("serverId") Long serverId,
//                          @Param("serverName") String serverName,
//                          @Param("serverMgmtIP") String serverMgmtIP,
//                          @Param("serverDataNet1") String serverDataNet1,
//                          @Param("serverDataNet2") String serverDataNet2,
//                          @Param("serverLocation") String serverLocation,
//                          @Param("serverOwner") String serverOwner);
}
