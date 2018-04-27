package com.huatek.frame.core.util;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.asm.Type;

public class EntityGenerator extends ClassLoader implements Opcodes{
    public Class<?>  createEntityClass(String className,String tableName, List<Map<String,Object>> fields)  {  
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);  
        cw.visit(V1_8, ACC_PUBLIC, className, null, Type.getInternalName(Object.class), null);  
        AnnotationVisitor av=cw.visitAnnotation(Type.getDescriptor(Entity.class), true);
        av.visitEnd();
        av=cw.visitAnnotation(Type.getDescriptor(Table.class), true);
        av.visit("name", tableName);
        av.visitEnd();
        // creates a MethodWriter for the (implicit) constructor  
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);  
        mv.visitVarInsn(ALOAD, 0);  
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");  
        mv.visitInsn(RETURN);  
        mv.visitMaxs(1, 1);  
  
        // create set&get methods  
        for (Map f : fields) {  
            addMethod(cw, mv, className, f);  
        }  
  
        byte[] b= cw.toByteArray();
//        try{
//        FileOutputStream fos=new FileOutputStream("e:/new/clazz.class");
//        fos.write(b);;
//        fos.flush();
//        fos.close();
//        }catch(Exception e){}
		return this.defineClass(className, b, 0, b.length,this.getClass().getProtectionDomain());
    } 
   
    private static void addMethod(ClassWriter cw, MethodVisitor mv, String className,  
                                  Map fieldInfo) {  
        String fieldName = fieldInfo.get("name").toString();  
        String setMethodName = "set" + StringUtils.capitalize(fieldName);  
        String getMethodName = "get" + StringUtils.capitalize(fieldName);  
  
        String typeof = Type.getType(Type.getDescriptor((Class<?>)fieldInfo.get("type"))).getDescriptor();  
        String getof = getof(typeof);  
        String setof = setof(typeof);  
        int[] loadAndReturnOf = loadAndReturnOf(typeof);  
          
        //add field  
        FieldVisitor fv=cw.visitField(ACC_PRIVATE, fieldName, typeof, null, null); 
        AnnotationVisitor av=null;
        if("id".equals(fieldName)){
        	av=fv.visitAnnotation(Type.getDescriptor(Id.class), true);
        	av.visitEnd();
        	av=fv.visitAnnotation(Type.getDescriptor(GeneratedValue.class), true);
        	av.visitEnum("strategy", Type.getDescriptor(GenerationType.class), "IDENTITY");
        	av.visitEnd();
        	av=fv.visitAnnotation(Type.getDescriptor(GenericGenerator.class), true);
        	av.visit("name", "persistenceGenerator");
        	av.visit("strategy", "increment");
        	av.visitEnd();
        	
        }
        av=fv.visitAnnotation(Type.getDescriptor(Column.class), true);
    	av.visit("name", fieldInfo.get("columnName"));
    	av.visitEnd();
        fv.visitEnd();
  
        // getMethod  
        mv = cw.visitMethod(ACC_PUBLIC, getMethodName, getof, null, null);  
        mv.visitCode();  
        mv.visitVarInsn(ALOAD, 0);  
        mv.visitFieldInsn(GETFIELD, className, fieldName, typeof);  
        mv.visitInsn(loadAndReturnOf[1]);  
        mv.visitMaxs(2, 1);  
        mv.visitEnd();  
          
        // setMethod  
        mv = cw.visitMethod(ACC_PUBLIC, setMethodName, setof, null, null);  
        mv.visitCode();  
        mv.visitVarInsn(ALOAD, 0);  
        mv.visitVarInsn(loadAndReturnOf[0], 1);  
        mv.visitFieldInsn(PUTFIELD, className, fieldName, typeof);  
        mv.visitInsn(RETURN);  
        mv.visitMaxs(3, 3);  
        mv.visitEnd();  
    }  
  
    private static String setof(String typeof) {  
        return "(" + typeof + ")V";  
    }  
  
    private static String getof(String typeof) {  
        return "()" + typeof;  
    }  
      
    private static int[] loadAndReturnOf(String typeof) {  
        if (typeof.equals("I") || typeof.equals("Z")) {  
            return new int[]{ILOAD,IRETURN};  
        } else if (typeof.equals("J")) {  
            return new int[]{LLOAD,LRETURN};  
        } else if (typeof.equals("D")) {  
            return new int[]{DLOAD,DRETURN};  
        } else if (typeof.equals("F")) {  
            return new int[]{FLOAD,FRETURN};  
        } else {  
            return new int[]{ALOAD,ARETURN};  
        }  
    } 
    public static String getEntityFieldNameByColumnName(String data) {
		StringBuffer buffer = new StringBuffer();;
        if (data.contains("_")) {
            String[] split = data.split("_");
            buffer.append(split[0].toLowerCase());
            for(int i=1;i<split.length;i++){
            	buffer.append(StringUtils.capitalize(split[i].toLowerCase()));
            }
            
        } else{
        	buffer.append(data.toLowerCase());
        }
        return buffer.toString();
	}
}
