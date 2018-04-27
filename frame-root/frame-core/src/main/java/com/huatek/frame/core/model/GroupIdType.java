package com.huatek.frame.core.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;
/***
 * 自定义的组织ID编码类型.
 * @author winner pan.
 *
 */
public class GroupIdType implements UserType {
	private static final int[] TYPES = new int[] {Types.VARCHAR}; 
	@Override
	public int[] sqlTypes() {
		return TYPES;
	}
	
	

	@Override
	public Class<GroupId> returnedClass() {
		return GroupId.class;
	}


	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		return new GroupId((String)StringType.INSTANCE.get(rs, names[0], session));  
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
		StringType.INSTANCE.nullSafeSet(st, value, index, session);
	}

	@Override
	public boolean isMutable() {
	    return false;
	}
	 
	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
	    return x.equals(y);
	}
	 
	@Override
	public int hashCode(Object x) throws HibernateException {
	    assert (x != null);
	    return x.hashCode();
	}
	 
	@Override
	public Object deepCopy(Object value) throws HibernateException {
	    return value;
	}
	 
	@Override
	public Object replace(Object original, Object target, Object owner)
	        throws HibernateException {
	    return original;
	}
	 
	@Override
	public Serializable disassemble(Object value) throws HibernateException {
	    return (Serializable) value;
	}
	 
	@Override
	public Object assemble(Serializable cached, Object owner)
	        throws HibernateException {
	    return cached;
	}

}
