package com.korosoft.tribalwarsremake.domain.map.retrieval;

import java.io.IOException;

public interface MapRetrievalService {
    byte[] getMapImage() throws IOException;
}
