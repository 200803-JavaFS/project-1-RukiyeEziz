package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

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
	public List<ReimbType> findReimbTypeByUserId(int userid) {
		
		return null;
	}

}
