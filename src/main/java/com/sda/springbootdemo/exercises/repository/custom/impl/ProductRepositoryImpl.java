package com.sda.springbootdemo.exercises.repository.custom.impl;

import com.sda.springbootdemo.exercises.model.Product;
import com.sda.springbootdemo.exercises.model.Receipt;
import com.sda.springbootdemo.exercises.model.Receipt2;
import com.sda.springbootdemo.exercises.repository.custom.ProductRepositoryCustom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> search(String name, Receipt receipt) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        Predicate predicate = builder.conjunction();

        if (name != null) {
            predicate = builder.and(predicate,
                builder.like(builder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
        }

        if (null != receipt) {
            predicate = builder.and(predicate, builder.equal(root.get("receipt"), receipt));
        }

        /*if (null != date) {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }*/

        return entityManager.createQuery(query.where(predicate)).getResultList();
    }

    public List<Receipt2> search(String productName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Receipt2> query = builder.createQuery(Receipt2.class);
        Root<Receipt2> root = query.from(Receipt2.class);
        Predicate predicate = builder.conjunction();

        if (productName != null) {
            Join<Receipt, Product> receiptProducts = root.join("products", JoinType.LEFT);

            predicate = builder.and(
                predicate,
                builder.like(builder.upper(receiptProducts.get("name")), productName.toUpperCase()));
        }
        return entityManager.createQuery(query.where(predicate)).getResultList();
    }

    public Page<Product> search(String name, Receipt receipt, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> productQuery = builder.createQuery(Product.class);
        productQuery = prepareQuery(productQuery, name, receipt, false, builder, pageable);

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery = prepareQuery(countQuery, name, receipt, true, builder, pageable);

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
        Receipt receipt, boolean count, CriteriaBuilder builder, Pageable pageable) {
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

        if (!count && pageable != null && pageable.getSort() != null) {
            query = addSortProperties(query, root, builder, pageable);
        }

        return query.where(predicate);
    }

    private <T> CriteriaQuery<T> addSortProperties(CriteriaQuery<T> query,
        Root<Product> root, CriteriaBuilder builder, Pageable pageable) {
        List<Order> orders = new ArrayList<>();
        Iterator<Sort.Order> iterator = pageable.getSort().iterator();
        Sort.Order order;

        while (iterator.hasNext()) {
            order = iterator.next();
            String property = order.getProperty();
            Path path = root.get(property);

            if (order.isAscending()) {
                orders.add(builder.asc(path));
            } else {
                orders.add(builder.desc(path));
            }
        }

        return query.orderBy(orders);
    }
}
