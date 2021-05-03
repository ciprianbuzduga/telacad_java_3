/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ro.telacad.entity.TypeConsultation;
import ro.telacad.entity.TypeConsultation_;

/**
 *
 * @author Buzy
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class TypeConsultationDao extends BaseDao<TypeConsultation> {
    @PersistenceContext(unitName = "ro.telacad_spital-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeConsultationDao() {
        super(TypeConsultation.class);
    }
    
    public List<TypeConsultation> listTypeConsultations() {
        return getCriteria().listOrderBy(TypeConsultation_.name, true);
    }
    
    public TypeConsultation getById(Integer id) {
        return getCriteria().getByProp(TypeConsultation_.id, id);
    }
}
