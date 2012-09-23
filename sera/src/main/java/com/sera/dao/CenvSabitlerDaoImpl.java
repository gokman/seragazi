package com.sera.dao;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvSabitler;
import com.sera.util.SeraCenvSabitlerRep;
import com.sera.util.object.DegerGenel;

@Repository("cenvsabitlerdao")
public class CenvSabitlerDaoImpl implements CenvSabitlerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<SeraCenvSabitler> listSabitler() {
		List<SeraCenvSabitler> list=(List<SeraCenvSabitler>)sessionFactory.getCurrentSession().
		createQuery("from SeraCenvSabitler").list();
		
		return list;
	}

	@Override
	public SeraCenvSabitler getSabit(Long id) {
		
		return (SeraCenvSabitler)sessionFactory.getCurrentSession().
		createCriteria(SeraCenvSabitler.class).
		add(Restrictions.eq("hasId", id)).list().get(0);
	}
	
	@Override
	public JRDataSource getCenvSabitlerReport() {
		List<SeraCenvSabitler> listSabitler=sessionFactory.getCurrentSession().createCriteria(SeraCenvSabitler.class).list();
			
		return new JRBeanCollectionDataSource(listSabitler);
		
	}
	
	
	@Override
	public JRDataSource getSabitlerRep() {
		List<SeraCenvSabitlerRep> seraSabitObjList;
		
		Query query=sessionFactory.getCurrentSession().createQuery(
				"SELECT d.baslik as baslik,sab.sabit as sabit,sab.createDate as createDate " +
				"FROM SeraCenvSabitler sab,SeraCenvDegerListe d " +
				"WHERE d.id =sab.hasId");
		
	
		seraSabitObjList=(List<SeraCenvSabitlerRep>)query.setResultTransformer( Transformers.aliasToBean(SeraCenvSabitlerRep.class)).list();

		return new JRBeanCollectionDataSource(seraSabitObjList);
	}

}
