package com.korosoft.TribalWarsRemake.domain.map.retrieval;

import java.io.IOException;

public interface MapRetrievalService {
    byte[] getMapImage() throws IOException;
}
