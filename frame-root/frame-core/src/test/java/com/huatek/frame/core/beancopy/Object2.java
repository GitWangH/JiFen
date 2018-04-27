package com.huatek.frame.core.beancopy;

import java.util.Arrays;
import java.util.Date;

public class Object2 {
	private Date value1;
	private Boolean value2;
	private Double value3;
	private int[] value5;
	private Object4 value6;
	public Date getValue1() {
		return value1;
	}
	public void setValue1(Date value1) {
		this.value1 = value1;
	}
	public Boolean getValue2() {
		return value2;
	}
	public void setValue2(Boolean value2) {
		this.value2 = value2;
	}
	public Double getValue3() {
		return value3;
	}
	public void setValue3(Double value3) {
		this.value3 = value3;
	}
	public int[] getValue5() {
		return value5;
	}
	public void setValue5(int[] value5) {
		this.value5 = value5;
	}
	public Object4 getValue6() {
		return value6;
	}
	public void setValue6(Object4 value6) {
		this.value6 = value6;
	}
	@Override
	public String toString() {
		return "Object2 [value1=" + value1 + ", value2=" + value2 + ", value3="
				+ value3 + ", value5=" + Arrays.toString(value5) + ", value6="
				+ value6 + "]";
	}

	
}
