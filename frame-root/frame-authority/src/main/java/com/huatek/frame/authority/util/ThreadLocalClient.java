package com.huatek.frame.authority.util;
/***
 * 用于保存当前线程关联的环境上下文信息.
 * 包括用户，客户端访问对象等.
 * @author 潘仁胜
 *
 */

public class ThreadLocalClient {
	private static final ThreadLocal<ClientInfoBean> container = new ThreadLocal<ClientInfoBean>(){
		protected synchronized ClientInfoBean initialValue() {
			      return new ClientInfoBean();
		}
	};
	 /**
     * Put the form bean to ThreadLocal.
     */
    public static void put(ClientInfoBean object) {
        container.set(object);
    }

    /**
     * Get the current thread's form bean.
     */
    public static ClientInfoBean get() {
        return container.get();
    }

    public static void remove() {
    	container.remove();
    }
}
