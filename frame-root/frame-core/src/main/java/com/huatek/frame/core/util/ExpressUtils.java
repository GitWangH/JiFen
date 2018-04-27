package com.huatek.frame.core.util;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

/**
 * 
* @ClassName: ExpressUtils 
* @Description: 快递详情查询
* @author eden_sun
* @date Jan 20, 2018 3:02:17 PM 
*
 */
public class ExpressUtils {
	private final static String CUSTOMER = CommonConstants.KUAIDI100CUSTOMER;
	  private final static String KEY = CommonConstants.KUAIDI100KEY;
	  /**
	   * 查询快递
	   *
	   * @param com 公司代码
	   * @param num 快递单号
	   * @return
	   */
	  public ExpressVO query(String com, String num) {
	      String param = "{\"com\":\"" + com + "\",\"num\":\"" + num + "\"}";
	      String sign = MD5Util.encodeMD5(param + KEY + CUSTOMER);
	      HashMap<String, String> params = new HashMap<String, String>();
	      params.put("param", param);
	      params.put("sign", sign);
	      params.put("customer", CUSTOMER);
	      String resp = null;
	      try {
	          resp = HttpClientUtil.requestFormPost(CommonConstants.KUAIDI100URL, params).getResponseContent();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return (ExpressVO)   new Gson().fromJson(resp, ExpressVO.class);
	  }
	  
	  
	  /**
	     * 快递查询返回结果
	     */
	   public static class ExpressVO {
	        private String message;//无意义，请忽略
	  
	        private String nu;//物流单号
	  
	        private String ischeck;//无意义，请忽略
	 
	        private String condition;//无意义，请忽略
	 
	        private String com;//物流公司编号
	 
	        /**
	         * 查询结果状态：
	         * 0：物流单暂无结果，
	         * 1：查询成功，
	         * 2：接口出现异常，
	         */
	        private String status;
	  
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
	        private String state;
	        private List<ExpressItemVO> data;//单条信息
	        public void setMessage(String message) {
	            this.message = message;
	        }
	        public String getMessage() {
	            return this.message;
	        }
	        public void setNu(String nu) {
	            this.nu = nu;
	        }
	        public String getNu() {
	            return this.nu;
	        }
	        public void setIscheck(String ischeck) {
	            this.ischeck = ischeck;
	        }
	        public String getIscheck() {
	            return this.ischeck;
	        }
	        public void setCondition(String condition) {
	            this.condition = condition;
	        }
	        public String getCondition() {
	            return this.condition;
	        }
	        public void setCom(String com) {
	            this.com = com;
	        }
	        public String getCom() {
	            return this.com;
	        }
	        public void setStatus(String status) {
	            this.status = status;
	        }
	        public String getStatus() {
	            return this.status;
	        }
	        public void setState(String state) {
	            this.state = state;
	        }
	        public String getState() {
	            return this.state;
	        }
	        public void setData(List<ExpressItemVO> data) {
	            this.data = data;
	        }
	        public List<ExpressItemVO> getData() {
	            return this.data;
	        }
	    }
	    
	    
	    /**
	      * 快递单条结果
	      */
	   public  static class ExpressItemVO {
	         private String time;//每条跟踪信息的时间
	         private String ftime;
	         private String context;//每条跟综信息的描述
	         public void setTime(String time) {
	             this.time = time;
	         }
	         public String getTime() {
	             return this.time;
	         }
	         public void setFtime(String ftime) {
	             this.ftime = ftime;
	         }
	         public String getFtime() {
	             return this.ftime;
	         }
	         public void setContext(String context) {
	             this.context = context;
	         }
	         public String getContext() {
	             return this.context;
	         }
	     }
}
