package com.huatek.cache.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.huatek.cache.model.CacheDataWrap;

/**
 * 实现 java对象的序列化，然后保存到memcache服务器上.
 * @author winner pan.
 *
 */
public class KryoUtil {
	/*private static final String memcacheServre = PropertyUtil
			.getConfigValue("memcache.address");*/
	private static final int maxBufferSize = 2048000;
	private static final int bufferSize = 1024;
	public static final Kryo kryo = new Kryo();
	static{
		kryo.register(CacheDataWrap.class);
	}
	public static byte[] serializer(CacheDataWrap cacheDataWrap){
		Output output = new Output(bufferSize, maxBufferSize);
		kryo.writeClassAndObject(output, cacheDataWrap);
		return output.toBytes();
	}
	
	public static CacheDataWrap deserializer(byte[] object){
		if(object==null){
			return null;
		}
		Input input = new Input(object);
		return kryo.readObject(input, CacheDataWrap.class);
	}
	
	/*public static void main(String args[]) throws IOException{
		TestBean testBean = new TestBean();
		SampleBean sample =  new SampleBean();
		sample.setId("ok");
		testBean.setSample(sample);
		testBean.setId("winner");
		testBean.setName("潘仁胜");
		testBean.setComments("测试");
		byte[] object  = serializer(testBean, TestBean.class);
		TestBean bean2 = deserializer(object,TestBean.class);
		///System.out.println(bean2.getComments());
		//System.out.println(bean2.getSample().getId());
		MemcachedClient c = new MemcachedClient(  
                AddrUtil.getAddresses(memcacheServre)); 
		c.add("1.2.3/(123)1_id", 8400, object);
		byte[] newObject = (byte[])c.get("1.2.3/(123)1_id");
		TestBean bean3 = deserializer(newObject,TestBean.class);
		System.out.println(bean3.getComments());
		System.out.println(bean3.getSample().getId());
		c.shutdown();
	}*/

}
