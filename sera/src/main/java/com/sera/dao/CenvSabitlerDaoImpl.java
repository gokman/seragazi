package com.sera.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvSabitler;

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

}
