package com.korosoft.TribalWarsRemake.domain.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransportEventDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int clay;
}
