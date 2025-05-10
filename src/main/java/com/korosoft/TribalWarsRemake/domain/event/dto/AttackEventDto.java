package com.korosoft.TribalWarsRemake.domain.event.dto;

import java.io.Serializable;

public record AttackEventDto(int pikemen, int sourceVillageId, int targetVillageId) implements Serializable {
}
