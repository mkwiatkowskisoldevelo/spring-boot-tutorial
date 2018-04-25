package com.sda.springbootdemo.exercises.repository.custom.impl;

import com.sda.springbootdemo.exercises.model.Product;
import com.sda.springbootdemo.exercises.model.Receipt;
import com.sda.springbootdemo.exercises.repository.custom.ProductRepositoryCustom;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Product> search(String name, Receipt receipt, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> productQuery = builder.createQuery(Product.class);
        productQuery = prepareQuery(productQuery, name, receipt, false, builder);

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery = prepareQuery(countQuery, name, receipt, true, builder);

        Long count = entityManager.createQuery(countQuery).getSingleResult();

        int pageSize = null != pageable ? pageable.getPageSize() : 0;
        int firstResult = null != pageable ? pageable.getPageNumber() * pageSize : 0;

        List<Product> processingPeriods = entityManager.createQuery(productQuery)
            .setMaxResults(pageSize)
            .setFirstResult(firstResult)
            .getResultList();
        return new PageImpl<>(processingPeriods, pageable, count);
    }


    private <T> CriteriaQuery<T> prepareQuery(CriteriaQuery<T> query, String name,
        Receipt receipt, boolean count, CriteriaBuilder builder) {
        Root<Product> root = query.from(Product.class);

        if (count) {
            CriteriaQuery<Long> countQuery = (CriteriaQuery<Long>) query;
            query = (CriteriaQuery<T>) countQuery.select(builder.count(root));
        }

        Predicate predicate = builder.conjunction();

        if (name != null) {
            predicate = builder.and(predicate,
                builder.like(builder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
        }

        if (null != receipt) {
            predicate = builder.and(predicate, builder.equal(root.get("receipt"), receipt));
        }

        return query.where(predicate);
    }
}
