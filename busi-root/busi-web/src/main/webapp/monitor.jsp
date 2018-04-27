<%@page import="com.huatek.rpc.server.core.SystemType"%>
<%@page import="com.huatek.esb.service.RpcTestService"%>
<%@page import="com.huatek.frame.core.util.MonitorUtil"%>
<%@page import="com.huatek.frame.core.util.MonitorInfoBean"%>
<%@page import="com.huatek.esb.msg.rpc.RpcProxy"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.beans.factory.config.AutowireCapableBeanFactory"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<style>
	table  th {
background-color: rgb(128, 102, 160);
color: #fff;
border-bottom-width: 0;
}

/* Column Style */
table td {
color: #000;
}
/* Heading and Column Style */
table tr, table th {
border-width: 1px;
border-style: solid;
border-color: rgb(128, 102, 160);
}

/* Padding and font style */
table td, table th {
padding: 5px 10px;
font-size: 12px;
font-family: Verdana;
font-weight: bold;
}
</style>
<body>
<% 
AutowireCapableBeanFactory factory=WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getAutowireCapableBeanFactory();
RpcProxy rpcProxy=factory.getBean(RpcProxy.class);
MonitorInfoBean info=MonitorUtil.getMonitorInfoBean();
%>

<table>
  <caption>核心业务服务</caption>
  <tr>
  	<th>监测项目</th>
  	<th>监测值</th>
  </tr>
  <tr><td>cpu占有率</td><td><%=info.getCpuRatio()%>%</td></tr>
  <tr><td>可使用内存</td><td><%=info.getTotalMemory()%>M</td></tr>
  <tr><td>剩余内存</td><td><%=info.getFreeMemory()%>M</td></tr>
  <tr><td>最大可使用内存</td><td><%=info.getMaxMemory()%>M</td></tr>
  <tr><td>操作系统</td><td><%=info.getOsName()%></td></tr>
  <tr><td>总的物理内存</td><td><%=info.getTotalMemorySize()%>M</td></tr>
  <tr><td>剩余的物理内存</td><td><%=info.getFreeMemory()%>M</td></tr>
  <tr><td>已使用的物理内存</td><td><%=info.getUsedMemory()%>M</td></tr>
  <tr><td>线程总数</td><td><%=info.getTotalThread()%>个</td></tr>
  <tr><td>数据库状态</td><td><%=info.isDbStatus()%></td></tr>
  <tr><td>数据库时间</td><td><%=info.getDbTime()%></td></tr>
</table>

<table>
  <caption>基础数据服务</caption>
  <tr>
  	<th>监测项目</th>
  	<th>监测值</th>
  </tr>
  <% 
  try{
		MonitorInfoBean subInfo=rpcProxy.create(RpcTestService.class,SystemType.cmd).getMonitorInfo();
  %>
  <tr><td>RPC通讯</td><td>正常</td></tr>
 <tr><td>cpu占有率</td><td><%=info.getCpuRatio()%>%</td></tr>
  <tr><td>可使用内存</td><td><%=info.getTotalMemory()%>M</td></tr>
  <tr><td>剩余内存</td><td><%=info.getFreeMemory()%>M</td></tr>
  <tr><td>最大可使用内存</td><td><%=info.getMaxMemory()%>M</td></tr>
  <tr><td>操作系统</td><td><%=info.getOsName()%></td></tr>
  <tr><td>总的物理内存</td><td><%=info.getTotalMemorySize()%>M</td></tr>
  <tr><td>剩余的物理内存</td><td><%=info.getFreeMemory()%>M</td></tr>
  <tr><td>已使用的物理内存</td><td><%=info.getUsedMemory()%>M</td></tr>
  <tr><td>线程总数</td><td><%=info.getTotalThread()%>个</td></tr>
  <tr><td>数据库状态</td><td><%=info.isDbStatus()%></td></tr>
  <tr><td>数据库时间</td><td><%=info.getDbTime()%></td></tr>
<% 
  }catch(Exception e){
	  %><tr><td>RPC通讯</td><td>异常(<%=e.getMessage() %>)</td></tr><%
  }
%>
</table>
<table>
  <caption>OA服务</caption>
  <tr>
  	<th>监测项目</th>
  	<th>监测值</th>
  </tr>
  <% 
  try{
		MonitorInfoBean subInfo=rpcProxy.create(RpcTestService.class,SystemType.oa).getMonitorInfo();
  %>
  <tr><td>RPC通讯</td><td>正常</td></tr>
  <tr><td>cpu占有率</td><td><%=info.getCpuRatio()%>%</td></tr>
  <tr><td>可使用内存</td><td><%=info.getTotalMemory()%>M</td></tr>
  <tr><td>剩余内存</td><td><%=info.getFreeMemory()%>M</td></tr>
  <tr><td>最大可使用内存</td><td><%=info.getMaxMemory()%>M</td></tr>
  <tr><td>操作系统</td><td><%=info.getOsName()%></td></tr>
  <tr><td>总的物理内存</td><td><%=info.getTotalMemorySize()%>M</td></tr>
  <tr><td>剩余的物理内存</td><td><%=info.getFreeMemory()%>M</td></tr>
  <tr><td>已使用的物理内存</td><td><%=info.getUsedMemory()%>M</td></tr>
  <tr><td>线程总数</td><td><%=info.getTotalThread()%>个</td></tr>
  <tr><td>数据库状态</td><td><%=info.isDbStatus()%></td></tr>
  <tr><td>数据库时间</td><td><%=info.getDbTime()%></td></tr>
<% 
  }catch(Exception e){
	   %><tr><td>RPC通讯</td><td>异常(<%=e.getMessage() %>)</td></tr><%
  }
%>
</table>
<table>
  <caption>工作流服务</caption>
  <tr>
  	<th>监测项目</th>
  	<th>监测值</th>
  </tr>
  <% 
  try{
		MonitorInfoBean subInfo=rpcProxy.create(RpcTestService.class,SystemType.workflow).getMonitorInfo();
  %>
  <tr><td>RPC通讯</td><td>正常</td></tr>
  <tr><td>cpu占有率</td><td><%=info.getCpuRatio()%>%</td></tr>
  <tr><td>可使用内存</td><td><%=info.getTotalMemory()%>M</td></tr>
  <tr><td>剩余内存</td><td><%=info.getFreeMemory()%>M</td></tr>
  <tr><td>最大可使用内存</td><td><%=info.getMaxMemory()%>M</td></tr>
  <tr><td>操作系统</td><td><%=info.getOsName()%></td></tr>
  <tr><td>总的物理内存</td><td><%=info.getTotalMemorySize()%>M</td></tr>
  <tr><td>剩余的物理内存</td><td><%=info.getFreeMemory()%>M</td></tr>
  <tr><td>已使用的物理内存</td><td><%=info.getUsedMemory()%>M</td></tr>
  <tr><td>线程总数</td><td><%=info.getTotalThread()%>个</td></tr>
  <tr><td>数据库状态</td><td><%=info.isDbStatus()%></td></tr>
  <tr><td>数据库时间</td><td><%=info.getDbTime()%></td></tr>
<% 
  }catch(Exception e){
	   %><tr><td>RPC通讯</td><td>异常(<%=e.getMessage() %>)</td></tr><%
  }
%>
</table>
</body>
</html>
