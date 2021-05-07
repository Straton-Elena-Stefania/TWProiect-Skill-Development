package com.timetraveling.models.users;

import com.timetraveling.models.management.EntityManagerFactoryHandle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserOpenJPADAO implements UserDAO {
    @Override
    public User findByID(int id) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryHandle.getInstance()
                .getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getMetamodel().entity(User.class);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        AbstractQuery<User> abstractQuery = criteriaBuilder.createQuery(User.class);

        Root<User> user = abstractQuery.from(User.class);

        abstractQuery.where(criteriaBuilder.equal(user.get("id"), id));

        CriteriaQuery<User> select = ((CriteriaQuery<User>) abstractQuery).select(user);
        TypedQuery<User> typedQuery = entityManager.createQuery(select);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        return new User("A", "B", "C");
    }

    @Override
    public User findByEmail(String email) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryHandle.getInstance()
                .getEntityManagerFactory();
        System.out.println("entitymanagerfactory " + entityManagerFactory);
        System.out.println(entityManagerFactory.isOpen());
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("entity manager " + entityManager);
        entityManager.getMetamodel().entity(User.class);

        /*User user = entityManager.createQuery(
                "SELECT g FROM User g WHERE User.email = :email", User.class).setParameter("email", email).getSingleResult();

         */

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        AbstractQuery<User> abstractQuery = criteriaBuilder.createQuery(User.class);

        Root<User> user = abstractQuery.from(User.class);

        abstractQuery.where(criteriaBuilder.equal(user.get("email"), email));

        CriteriaQuery<User> select = ((CriteriaQuery<User>) abstractQuery).select(user);
        TypedQuery<User> typedQuery = entityManager.createQuery(select);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException noResultException) {
            System.err.println("Nu am gasit emailul in baza de date");
            return null;
        }
    }

    @Override
    public void save(User user) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryHandle.getInstance()
                                                                              .getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();

        System.out.println("#########################entity" + entityManagerFactory);
        System.out.println("em - " + em);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(User user) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryHandle.getInstance()
                .getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }
}
