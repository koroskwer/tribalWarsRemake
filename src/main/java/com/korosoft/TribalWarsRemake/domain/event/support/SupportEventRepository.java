package com.korosoft.TribalWarsRemake.domain.event.support;

import org.springframework.data.jpa.repository.JpaRepository;

interface SupportEventRepository extends JpaRepository<SupportEvent, Integer> {
}
