package br.unisul.aula.Banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.unisul.aula.modelo.Endereco;

public class EnderecoImpl {

    public void insert(Endereco endereco) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(endereco);
        entityManager.getTransaction().commit();
    }

    public void remove(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Endereco endereco = findById(id);
        entityManager.remove(endereco);
        entityManager.getTransaction().commit();
    }

    public void update(Endereco endereco) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(endereco);
        entityManager.getTransaction().commit();
    }

    public List<Endereco> findAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        return entityManager
                .createQuery("SELECT e FROM Endereco e", Endereco.class)
                .getResultList();
    }

    public Endereco findById(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Endereco> query = entityManager
                .createQuery("SELECT s FROM Endereco s where s.id =:id", Endereco.class);
        return query.setParameter("id", id).getSingleResult();
    }
}