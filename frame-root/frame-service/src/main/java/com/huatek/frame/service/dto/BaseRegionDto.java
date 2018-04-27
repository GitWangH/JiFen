package com.huatek.frame.service.dto;

public class BaseRegionDto implements java.io.Serializable{
	
	  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Long id;
	    private String provinceCode;
	    private String provinceName;
	    private String cityCode;
	    private String cityName;
	    private String districtCode;
	    private String districtName;
	    private String status;
	    private String sapCode;
	    private String sapName;
	    private String townCode;
	    private String townName;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getProvinceCode() {
			return provinceCode;
		}
		public void setProvinceCode(String provinceCode) {
			this.provinceCode = provinceCode;
		}
		public String getProvinceName() {
			return provinceName;
		}
		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getDistrictCode() {
			return districtCode;
		}
		public void setDistrictCode(String districtCode) {
			this.districtCode = districtCode;
		}
		public String getDistrictName() {
			return districtName;
		}
		public void setDistrictName(String districtName) {
			this.districtName = districtName;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getSapCode() {
			return sapCode;
		}
		public void setSapCode(String sapCode) {
			this.sapCode = sapCode;
		}
		public String getSapName() {
			return sapName;
		}
		public void setSapName(String sapName) {
			this.sapName = sapName;
		}
		public String getTownCode() {
			return townCode;
		}
		public void setTownCode(String townCode) {
			this.townCode = townCode;
		}
		public String getTownName() {
			return townName;
		}
		public void setTownName(String townName) {
			this.townName = townName;
		}
}
