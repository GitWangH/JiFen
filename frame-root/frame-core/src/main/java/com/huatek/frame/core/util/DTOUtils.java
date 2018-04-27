package com.huatek.frame.core.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;

public class DTOUtils {
	private static final ModelMapper INSTANCE = new ModelMapper();
	static {
		INSTANCE.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
    public static <S, T> T map(S source, Class<T> targetClass) {
        return INSTANCE.map(source, targetClass);
    }
    
    @SuppressWarnings("unchecked")
	public static <S, T> T map(S source, Class<T> targetClass, String mapMethod, Class<S> sourceClass) {
    	T target = null;
        if(source!=null){
     	   try{
 	    	    Method method = targetClass.getMethod(mapMethod, sourceClass);
 		        target = (T) method.invoke(null, source);
 	        }catch(Exception e){
 	        	e.printStackTrace();
 	        	throw new BusinessRuntimeException(e.getMessage(),"-1");
 	        }
        }
        return target;
    }

    public static <S, T> void mapTo(S source, T dist) {
        INSTANCE.map(source, dist);
    }
    
    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            T target = INSTANCE.map(source.get(i), targetClass);
            list.add(target);
        }

        return list;
    }
    
    public static <S, T> T mapObject(S s, Class<T> targetClass) {
        if(s != null){
    		T target = INSTANCE.map(s, targetClass);
    		return target;
    	}
        return null;
    }
    
   public static <S, T> DataPage<T> mapPage(DataPage<S> source, Class<T> targetClass) {
        List<S> sourceList = source.getContent();

        List<T> list = new ArrayList<>();
        if(sourceList!=null){
	        for (S s : sourceList) {
	            T target = INSTANCE.map(s, targetClass);
	            list.add(target);
	        }
        }
        DataPage<T> page = new DataPage<T>();
        page.setContent(list);
        page.setPage(source.getPage());
        page.setPageSize(source.getPageSize());
        page.setTotalPage(source.getTotalPage());
        page.setTotalRows(source.getTotalRows());
        return page;
    }
   
   public static <S, T> DataPage<T> mapPage(DataPage<S> source, Class<T> targetClass, String mapMethod, Class<S> sourceClass) {
       List<S> sourceList = source.getContent();

       List<T> list = new ArrayList<>();
       if(sourceList!=null){
    	   try{
	    	    Method method = targetClass.getMethod(mapMethod, sourceClass);
		        for (S s : sourceList) {
		            @SuppressWarnings("unchecked")
					T target = (T) method.invoke(null, s);
		            list.add(target);
		        }
	        }catch(Exception e){
	        	e.printStackTrace();
	        	throw new BusinessRuntimeException(e.getMessage(),"-100");
	        }
       }
       DataPage<T> page = new DataPage<T>();
       page.setContent(list);
       page.setPage(source.getPage());
       page.setPageSize(source.getPageSize());
       page.setTotalPage(source.getTotalPage());
       page.setTotalRows(source.getTotalRows());
       return page;
   }
   
}
