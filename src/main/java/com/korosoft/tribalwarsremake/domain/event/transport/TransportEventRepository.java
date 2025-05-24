package com.korosoft.tribalwarsremake.domain.event.transport;

import org.springframework.data.jpa.repository.JpaRepository;

interface TransportEventRepository extends JpaRepository<TransportEvent, Integer> {
}
