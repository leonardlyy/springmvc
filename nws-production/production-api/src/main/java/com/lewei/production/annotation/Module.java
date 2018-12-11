package com.lewei.production.annotation;

public enum Module {

	PUBLISH("公示管理"), PATROL("巡查管理"), REPORT("投诉管理"), GENERAL("普通管理"), 
	STATS("统计管理"), PRE_WARNING("预警管理"), EVIDENCE("取证宝"), GIS("GIS地图");

	private String value;

	Module(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
