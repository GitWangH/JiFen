#项目名称
数据中心web项目（可部署项目）

#项目开发目录
无（此项目仅需配置）

#公共配置文件
com.huatek.mdm.web.config目录下，
AppConfig.java文件中basePackages属性用来配置service和dao的扫描路径；
HibernateConfiguration.java文件中sessionFactory.setPackagesToScan用来配置model的扫描路径

resources.config目录下config.propertie文件：
registry.address配置ZooKeeper服务器地址和端口
server.address配置RPC本地端口（开发是需要修改成本地ip）

#依赖项目
cnex4-mdm-api