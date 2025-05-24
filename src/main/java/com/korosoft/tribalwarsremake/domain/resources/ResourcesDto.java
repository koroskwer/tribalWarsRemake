package com.korosoft.tribalwarsremake.domain.resources;

import java.io.Serial;
import java.io.Serializable;


public record ResourcesDto(int wood, int clay, int iron) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
