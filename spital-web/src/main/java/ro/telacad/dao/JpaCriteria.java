/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author Buzy
 */
public class JpaCriteria<T> {
    
    private final Class<T> entityClass;
    private final Class<?> queryResultClass;
    private final EntityManager em;
    private CriteriaBuilder cb;
    private CriteriaQuery<?> cq;
    private Root<T> root;
    private List<Predicate> predicates;
    private List<Order> orders;
    
    JpaCriteria(Class<T> entityClass, Class<?> queryResultClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.queryResultClass = queryResultClass;
        this.em = entityManager;
        init();
    }
    
    private void init() {
        cb = em.getCriteriaBuilder();
        cq = cb.createQuery(queryResultClass);
        root = cq.from(entityClass);
        predicates = new ArrayList<>();
        orders = new ArrayList<>();
    }
    
    public <X> Path<X> getPath(SingularAttribute<T, X> prop) {
        return root.get(prop);
    }
    
    public <X, Y> Path<Y> getPath(SingularAttribute<T, X> joinProp, SingularAttribute<X, Y> prop) {
        return root.get(joinProp).get(prop);
    }
    
    public <X> void addEqual(SingularAttribute<T, X> prop, X obj) {
        Predicate peq = cb.equal(getPath(prop), obj);
        predicates.add(peq);
    }
    
    public <X, Y> void addEqual(SingularAttribute<T, X> joinProp, SingularAttribute<X, Y> prop, Y obj) {
        Predicate peq = cb.equal(getPath(joinProp).get(prop), obj);
        predicates.add(peq);
    }
    
    public <X> void addNotEqual(SingularAttribute<T, X> prop, X obj) {
        Predicate peq = cb.notEqual(getPath(prop), obj);
        predicates.add(peq);
    }
    
    public <X, Y> void addNotEqual(SingularAttribute<T, X> joinProp, SingularAttribute<X, Y> prop, Y obj) {
        Predicate peq = cb.notEqual(getPath(joinProp).get(prop), obj);
        predicates.add(peq);
    }

    public T get() {
        List<T> rl = (List<T>) addClauseFinalAndGetResultList(false);
        if(rl != null && rl.size() > 0) {
            if(rl.size() > 1) {
                System.out.println("Multiple results");
            }
            return rl.get(0);
        }
        return null;
    }
    
    private List<?> addClauseFinalAndGetResultList(boolean addOrders) {
        if(!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        
        if(addOrders && !orders.isEmpty()) {
            cq.orderBy(orders);
        }
        
        TypedQuery<?> q = em.createQuery(cq);
        return q.getResultList();
    }
    
    public <X> void addOrder(SingularAttribute<T, X> prop, boolean asc) {
        if(asc) {
            orders.add(cb.asc(getPath(prop)));
        } else {
            orders.add(cb.desc(getPath(prop)));
        }
    }
    
    public <X, Y> void addOrder(SingularAttribute<T, X> joinProp, SingularAttribute<X, Y> prop, boolean asc) {
        if(asc) {
            orders.add(cb.asc(getPath(joinProp).get(prop)));
        } else {
            orders.add(cb.desc(getPath(joinProp).get(prop)));
        }
    }
    
    public <X> List<T> listOrderBy(SingularAttribute<T, X> prop, boolean asc) {
        addOrder(prop, asc);
        return (List<T>) addClauseFinalAndGetResultList(true);
    }
    
    public <X, Y> List<T> listOrderBy(SingularAttribute<T, X> joinProp, SingularAttribute<X, Y> prop, boolean asc) {
        addOrder(joinProp, prop, asc);
        return (List<T>) addClauseFinalAndGetResultList(true);
    }
    
    public <X> T getByProp(SingularAttribute<T, X> prop, X obj) {
        if(obj == null) return null;
        addEqual(prop, obj);
        return get();
    }
    
    public <X, Y> T getByProp(SingularAttribute<T, X> joinProp, SingularAttribute<X, Y> prop, Y obj) {
        addEqual(joinProp, prop, obj);
        return get();
    }
    
    public <X> void addLike(SingularAttribute<T, X> prop, X obj) {
        Expression<String> fieldLike = root.get(prop).as(String.class);
        Predicate plike =  cb.like(fieldLike, obj + "%");
        predicates.add(plike);
    }
    
    public <X, Y> void addLike(SingularAttribute<T, X> joinProp, SingularAttribute<X, Y> prop, Y obj) {
        Expression<String> fieldLike = getPath(joinProp, prop).as(String.class);
        Predicate plike =  cb.like(fieldLike, obj + "%");
        predicates.add(plike);
    }
    
    public <X> List<X> selectDisctinct(SingularAttribute<T, X> prop) {
        ((CriteriaQuery<X>)cq).select(root.get(prop));
        cq.distinct(true);
        return (List<X>) addClauseFinalAndGetResultList(true);
    }
    
    public <X, Y> List<Y> selectDisctinct(SingularAttribute<T, X> joinProp, SingularAttribute<X, Y> prop) {
        ((CriteriaQuery<Y>)cq).select(getPath(joinProp, prop));
        cq.distinct(true);
        return (List<Y>) addClauseFinalAndGetResultList(true);
    }
}
