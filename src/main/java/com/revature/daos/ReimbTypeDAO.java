package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.utils.HibernateUtil;

public class ReimbTypeDAO implements IReimbTypeDAO {

	@Override
	public ReimbType findReimbType(int typeid) {
		Session session = HibernateUtil.getSession();
		ReimbType type = session.get(ReimbType.class, typeid);
		
		return type;
	}
	
	@Override
	public ReimbType findReimbTypeByType(String type) {
		Session ses = HibernateUtil.getSession();
		List<ReimbType> list = (List<ReimbType>) ses.createQuery("FROM ReimbType WHERE reimbType='"+type+"'", ReimbType.class).list();
		return list.get(0);
	}
	
	@Override
	public List<ReimbType> findReimbTypeByUserId(int userid) {
		
		return null;
	}


	

}
