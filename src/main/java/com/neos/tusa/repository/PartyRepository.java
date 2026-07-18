package com.neos.tusa.repository;

import com.neos.tusa.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
           "FROM Party p JOIN p.members m " +
           "WHERE p.id = :partyId AND m.id = :userId")
    boolean existsUserInParty(@Param("userId") Long userId,
                              @Param("partyId") Long partyId);
}
