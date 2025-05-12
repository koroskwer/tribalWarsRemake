package com.korosoft.TribalWarsRemake.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;

interface TransportEventRepository extends JpaRepository<TransportEvent, Integer> {
}
