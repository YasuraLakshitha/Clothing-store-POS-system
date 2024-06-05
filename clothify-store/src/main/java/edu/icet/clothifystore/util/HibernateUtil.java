package edu.icet.clothifystore.util;

import edu.icet.clothifystore.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory createSessionFactory(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        MetadataSources metadataSources = new MetadataSources(registry)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(OrderEntity.class)
                .addAnnotatedClass(PaymentEntity.class)
                .addAnnotatedClass(PaymentTypeEntity.class)
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(AdminEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .addAnnotatedClass(CategoryEntity.class)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(SupplierProductEntity.class)
                .addAnnotatedClass(OrderDetailsEntity.class);
        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
        Metadata metadata = metadataBuilder.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static Session getSession(){
        return createSessionFactory().openSession();
    }
}
