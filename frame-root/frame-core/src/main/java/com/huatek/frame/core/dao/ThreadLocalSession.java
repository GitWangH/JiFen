package com.huatek.frame.core.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;


public class ThreadLocalSession {
	private static final ThreadLocal<Set<Session>> container = new ThreadLocal<Set<Session>>(){
		
			protected synchronized Set<Session> initialValue() {
				      return new HashSet<Session>();
			}
		
	};
	 /**
     * Put the form bean to ThreadLocal.
     */
    public static void put(Session session) {
        container.get().add(session);
    }

    /**
     * Get the current thread's form bean.
     */
    public static Set<Session> get() {
        return container.get();
    }

    public static int remove() {
    	int count = 0;
    	for(Session session : container.get()){
    		if(session.isOpen()){
    			session.clear();
    			session.close();
    			count ++;
    		}
    		session = null;
    	}
    	container.remove();
    	return count;
    }
}
