package com.korosoft.tribalwarsremake.domain.army.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UnitFacade {

    private final Map<Class<? extends UnitStatistics>, UnitStatistics> unitStatistics;

    @Autowired
    UnitFacade(List<UnitStatistics> unitStatistics) {
        this.unitStatistics = unitStatistics.stream().collect(Collectors.toMap(UnitStatistics::getClass, Function.identity()));
    }

    public UnitStatistics getPikemenStatistics(){
        return this.unitStatistics.get(PikemenStatistics.class);
    }

}
