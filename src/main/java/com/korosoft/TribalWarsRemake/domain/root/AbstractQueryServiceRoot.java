package com.korosoft.TribalWarsRemake.domain.root;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;

public abstract class AbstractQueryServiceRoot {

    @PersistenceContext
    protected EntityManager entityManager;

    public JPAQueryFactory getQueryFactory() {
        return new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }

    public JPAQueryFactory getQueryFactoryWithPartitionKey(String partitionKey) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter(WorldGenerationDirectionPartitionAware.PARTITION_KEY)
                .setParameter(
                        WorldGenerationDirectionPartitionAware.PARTITION_KEY,
                        partitionKey
                );
        return new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }
}
