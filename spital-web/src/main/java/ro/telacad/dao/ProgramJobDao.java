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
import ro.telacad.entity.ProgramJob;
import ro.telacad.entity.ProgramJob_;

/**
 *
 * @author Buzy
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ProgramJobDao extends BaseDao<ProgramJob>{

    @PersistenceContext(unitName = "ro.telacad_spital-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ProgramJobDao() {
        super(ProgramJob.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<ProgramJob> list() {
        return getCriteria().listOrderBy(ProgramJob_.day, true);
    }
}
