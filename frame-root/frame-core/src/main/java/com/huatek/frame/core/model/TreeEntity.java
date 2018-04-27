package com.huatek.frame.core.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TreeEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * 级别.
	 */
	@Column(name = "group_level", nullable = false)
	private Integer orgLevel;
	
	public Integer getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}
	
	/**
	 * 第一个序号数据.
	 */
	@Column(name = "level_1", nullable = false)
	long level1;
	/***
	 * 第二个序号数据
	 */
	@Column(name = "level_2", nullable = false)
	long level2;
	/**
	 * 第三个序号数据.
	 */
	@Column(name = "level_3", nullable = false)
	long level3;
	/***
	 * 第四个序号数据.
	 */
	@Column(name = "level_4", nullable = false)
	long level4;
	/***
	 * 第五个序号数据.
	 */
	@Column(name = "level_5", nullable = false)
	long level5;
	/***
	 * 第六个序号.
	 */
	@Column(name = "level_6", nullable = false)
	long level6;
	
	/***
	 * 第七个序号.
	 */
	@Column(name = "level_7", nullable = false)
	long level7;
	
	/***
	 * 第八个序号.
	 */
	@Column(name = "level_8", nullable = false)
	long level8;
	
	/***
	 * 第九个序号.
	 */
	@Column(name = "level_9", nullable = false)
	long level9;
	
	/***
	 * 第十个序号.
	 */
	@Column(name = "level_10", nullable = false)
	long level10;



	public long getLevel1() {
		return level1;
	}
	public void setLevel1(long level1) {
		this.level1 = level1;
	}
	public long getLevel2() {
		return level2;
	}
	public void setLevel2(long level2) {
		this.level2 = level2;
	}
	public long getLevel3() {
		return level3;
	}
	public void setLevel3(long level3) {
		this.level3 = level3;
	}
	public long getLevel4() {
		return level4;
	}
	public void setLevel4(long level4) {
		this.level4 = level4;
	}
	public long getLevel5() {
		return level5;
	}
	public void setLevel5(long level5) {
		this.level5 = level5;
	}
	public long getLevel6() {
		return level6;
	}
	public void setLevel6(long level6) {
		this.level6 = level6;
	}
	public long getLevel7() {
		return level7;
	}
	public void setLevel7(long level7) {
		this.level7 = level7;
	}
	public long getLevel8() {
		return level8;
	}
	public void setLevel8(long level8) {
		this.level8 = level8;
	}
	public long getLevel9() {
		return level9;
	}
	public void setLevel9(long level9) {
		this.level9 = level9;
	}
	public long getLevel10() {
		return level10;
	}
	public void setLevel10(long level10) {
		this.level10 = level10;
	}
	
	/**
	 * 
	 * @param s
	 * @param t
	 */
	public static void copyLevels(TreeEntity s, TreeEntity t) {
		t.setLevel1(s.getLevel1());
		t.setLevel2(s.getLevel2());
		t.setLevel3(s.getLevel3());
		t.setLevel4(s.getLevel4());
		t.setLevel5(s.getLevel5());
		t.setLevel6(s.getLevel6());
		t.setLevel7(s.getLevel7());
		t.setLevel8(s.getLevel8());
		t.setLevel9(s.getLevel9());
		t.setLevel10(s.getLevel10());

		if (t.getOrgLevel() == 1)
			t.setLevel1(s.getId());
		if (t.getOrgLevel() == 2)
			t.setLevel2(s.getId());
		if (t.getOrgLevel() == 3)
			t.setLevel3(s.getId());
		if (t.getOrgLevel() == 4)
			t.setLevel4(s.getId());
		if (t.getOrgLevel() == 5)
			t.setLevel5(s.getId());
		if (t.getOrgLevel() == 6)
			t.setLevel6(s.getId());
		if (t.getOrgLevel() == 7)
			t.setLevel7(s.getId());
		if (t.getOrgLevel() == 8)
			t.setLevel8(s.getId());
		if (t.getOrgLevel() == 9)
			t.setLevel9(s.getId());
		if (t.getOrgLevel() == 10)
			t.setLevel10(s.getId());
	}
	

}
