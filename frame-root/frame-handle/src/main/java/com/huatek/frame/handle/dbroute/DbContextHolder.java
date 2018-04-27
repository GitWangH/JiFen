package com.huatek.frame.handle.dbroute;

public class DbContextHolder {

   private static final ThreadLocal<DbType> contextHolder =
            new ThreadLocal<DbType>();

   public static void setDbType(DbType dbType) {
      contextHolder.set(dbType);
   }

   public static DbType getDbType() {
      return (DbType) contextHolder.get();
   }

   public static void clearDbType() {
      contextHolder.remove();
   }
}