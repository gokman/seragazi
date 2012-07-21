package com.sera.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvSabitler;

@Repository("cenvdegerlistedao")
public class CenvDegerListeDaoImpl implements CenvDegerListeDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveKokCenvDeger(SeraCenvDegerListe cenvDegerListe) {
        sessionFactory.getCurrentSession().save(cenvDegerListe);

	}
	
	@Override
	public List<SeraCenvDegerListe> listDalKokCenv() {
		List<SeraCenvDegerListe> list=(List<SeraCenvDegerListe>)sessionFactory.getCurrentSession().createQuery("from SeraCenvDegerListe where tip1!='Yaprak'").list();
	
		return list;
	}

	@Override
	public List<Long> getSeviye(long id) {
		List<Long> list=(List<Long>)sessionFactory.getCurrentSession().createQuery("select seviye from SeraCenvDegerListe where id="+id).list();
		return list;
	}
	
	@Override
	public List<SeraCenvDegerListe> searchCenvDeger(String baslik){
   
         List<SeraCenvDegerListe> lookList=(List<SeraCenvDegerListe>) sessionFactory.getCurrentSession().createCriteria(SeraCenvDegerListe.class).
         add(Restrictions.like("baslik", "%"+baslik+"%")).list();
         
         return lookList;

	}
	
	@Override
	public SeraCenvDegerListe getCenvDeger(long id){
     
		SeraCenvDegerListe seracenv=(SeraCenvDegerListe)sessionFactory.getCurrentSession().
		createCriteria(SeraCenvDegerListe.class).add(Restrictions.eq("id", id)).list().get(0);
         
         return seracenv;

	}
	
	@Override
	public List<SeraCenvDegerListe> getParent(long id) {
		List<Long> parent_id=sessionFactory.getCurrentSession().createSQLQuery("select parent_id from sera.sera_cenv_deger_liste where id="+id).list();
		List<SeraCenvDegerListe> list=sessionFactory.getCurrentSession().createQuery("from SeraCenvDegerListe where id="+parent_id.get(0)).list();
		return list;
	}
	
	

	@Override
	public SeraCenvDegerListe getKok() {
		SeraCenvDegerListe kok=(SeraCenvDegerListe)sessionFactory.getCurrentSession().createQuery("from SeraCenvDegerListe where tip1='Kok'").list().get(0);
		return kok;
	}

	@Override
	public List<SeraCenvDegerListe> listChildren(long id) {
		List<SeraCenvDegerListe> list=sessionFactory.getCurrentSession().createQuery("from SeraCenvDegerListe where parentId="+id).list();
		return list;
	}

	@Override
	public void saveKokCenvSabit(SeraCenvSabitler cenvDegerSabit) {
		sessionFactory.getCurrentSession().save(cenvDegerSabit);
		
	}

	@Override
	public void updateCenvSabit(SeraCenvSabitler cenvsabit) {
		sessionFactory.getCurrentSession().update(cenvsabit);
		
	}
	
	@Override
	public void updateCenvDeger(SeraCenvDegerListe cenvdeger) {
		
		sessionFactory.getCurrentSession().update(cenvdeger);
		
	}

	@Override
	public SeraCenvSabitler getCenvSabit(long id) {
		
		return (SeraCenvSabitler)sessionFactory.getCurrentSession().
		createCriteria(SeraCenvSabitler.class).add(Restrictions.eq("hasId", id)).list().get(0);
	}

	@Override
	public List<SeraCenvDegerListe> getAncestors(Long id) {
		List<SeraCenvDegerListe> ancestors=new ArrayList<SeraCenvDegerListe>();
		SeraCenvDegerListe parent=new SeraCenvDegerListe();
		Long tempId=id;
		while(getParent(tempId).get(0)!=null){
			parent=getParent(tempId).get(0);
			ancestors.add(parent);
			tempId=parent.getId();
		}
		
		return ancestors;
	}

	@Override
	public int getYaprakQuantity() {
		
		return (Integer)sessionFactory.getCurrentSession().createCriteria(SeraCenvDegerListe.class).
		add(Restrictions.eq("tip1", "Yaprak")).
		setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public int getYaprakQuantity(String tip) {
		// TODO Auto-generated method stub
		return (Integer)sessionFactory.getCurrentSession().createCriteria(SeraCenvDegerListe.class).
		add(Restrictions.eq("tip1", "Yaprak")).add(Restrictions.eq("tip2", tip)).
		setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
	public List<SeraCenvDegerListe> listyaprak() {
		List<SeraCenvDegerListe> yaprakliste;
		yaprakliste=sessionFactory.getCurrentSession().createCriteria(SeraCenvDegerListe.class).
		add(Restrictions.eq("tip1", "Yaprak")).list();
		return yaprakliste;
	}
	
	@Override
	public List<SeraCenvDegerListe> listyaprak(String tip) {
		List<SeraCenvDegerListe> yaprakliste;
		yaprakliste=sessionFactory.getCurrentSession().createCriteria(SeraCenvDegerListe.class).
		add(Restrictions.eq("tip1", "Yaprak")).add(Restrictions.eq("tip2", tip)).list();
		return yaprakliste;
	}
	

}