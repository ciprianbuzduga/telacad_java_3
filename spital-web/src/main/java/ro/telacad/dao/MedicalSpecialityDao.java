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
import ro.telacad.entity.MedicalSpeciality;
import ro.telacad.entity.MedicalSpeciality_;

/**
 *
 * @author Buzy
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class MedicalSpecialityDao extends BaseDao<MedicalSpeciality> {
    @PersistenceContext(unitName = "ro.telacad_spital-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicalSpecialityDao() {
        super(MedicalSpeciality.class);
    }
    
    public List<MedicalSpeciality> listSpeciality() {
        return getCriteria().listOrderBy(MedicalSpeciality_.name, true);
    }
    
    public MedicalSpeciality getById(Integer id) {
        if(id == null) return null;
        return getCriteria().getByProp(MedicalSpeciality_.id, id);
    }
}
