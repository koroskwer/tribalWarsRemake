package com.korosoft.tribalwarsremake.domain.root;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public class AbstractEntityRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
}
