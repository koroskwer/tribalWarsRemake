package com.korosoft.TribalWarsRemake.domain.event.attack;

import org.springframework.data.jpa.repository.JpaRepository;

interface AttackEventRepository extends JpaRepository<AttackEvent, Integer> {
}
