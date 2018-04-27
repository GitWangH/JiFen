package com.huatek.file.excel.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ImportQueue {
	private static final int queueLength=3;
	public static ExecutorService excutor=Executors.newFixedThreadPool(queueLength);
	public static Map<String,String> submit(Callable<String> task){
		 Future<String> future=excutor.submit(task);
		 
		 Map<String,String> result=new HashMap<String,String>();
		 String message=null;
		 try {
			 message=future.get(3, TimeUnit.SECONDS);
		} catch (Exception e) {
//			result.put("error", "入库任务排队异常");
//			result.put("message", e.getMessage());
		} 
		 if(future.isDone()){
			 if(message!=null){
				 result.put("error", "入库异常");
				result.put("message", message);
				 return result;
			 }else{
				 result.put("message", "入库成功");
				 return result;
			 }
		 }else{
			 result.put("message", "系统繁忙，已排队等待，请稍后查看结果！");
			 return result;
		 }	
	}
	
}
