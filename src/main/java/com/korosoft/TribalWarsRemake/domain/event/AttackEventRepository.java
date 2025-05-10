package com.korosoft.TribalWarsRemake.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;

interface AttackEventRepository extends JpaRepository<AttackEvent, Integer> {
}
