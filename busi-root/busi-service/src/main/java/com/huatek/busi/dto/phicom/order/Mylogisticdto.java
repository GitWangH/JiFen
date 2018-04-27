package com.huatek.busi.dto.phicom.order;


public class Mylogisticdto {
	
	/**
	 * 时间
	 */
	 private String time;
	 /**
      * 快递单当前的状态 ：
      * 0：在途，即货物处于运输过程中；
      * 1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息；
      * 2：疑难，货物寄送过程出了问题；
      * 3：签收，收件人已签收；
      * 4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收；
      * 5：派件，即快递正在进行同城派件；
      * 6：退回，货物正处于退回发件人的途中；
      */
	 private String states;
	 /**
	  * 物流信息
	  */
	 private String context;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	 
	
	
}