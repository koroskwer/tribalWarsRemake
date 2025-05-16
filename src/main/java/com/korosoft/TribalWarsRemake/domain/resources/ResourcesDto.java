package com.korosoft.TribalWarsRemake.domain.resources;

import java.io.Serializable;


public record ResourcesDto(int wood, int clay, int iron) implements Serializable {
    private static final long serialVersionUID = 1L;
}
