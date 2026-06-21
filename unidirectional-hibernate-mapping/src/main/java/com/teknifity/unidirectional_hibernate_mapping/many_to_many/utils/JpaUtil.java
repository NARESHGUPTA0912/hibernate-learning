package com.teknifity.unidirectional_hibernate_mapping.many_to_many.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("hibernate");
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void closeFactory() {
        if (entityManagerFactory != null
                && entityManagerFactory.isOpen()) {

            entityManagerFactory.close();
        }
    }
}