package com.korosoft.tribalwarsremake.domain.village.repository;


import com.korosoft.tribalwarsremake.domain.village.Village;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillageRepository extends JpaRepository<Village, Long> {
    Village findVillageById(int id);
}
