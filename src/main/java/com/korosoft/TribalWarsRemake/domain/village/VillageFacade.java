package com.korosoft.TribalWarsRemake.domain.village;

import com.korosoft.TribalWarsRemake.domain.village.repository.VillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class VillageFacade {

    private final VillageRepository villageRepository;

    public Village getVillage(int id) {
        Optional<Village> village = villageRepository.findById((long) id);
        if(village.isPresent()) {
            return village.get();
        }
        //TODO log this instead
        System.out.printf("Village with id: %d not found%n", id);
        return null;
    }
}
