package com.example.digitallifesampleandroidapp;

import java.util.ArrayList;

public class DigitalLifeDevice {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

//	public String getImageName() {
//		return imageName;
//	}
//
//	public void setImageName(String imageName) {
//		this.imageName = imageName;
//	} 
	
	public void setResourceID(int rid) {
		resourceID = rid;   
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getResourceID() {
		return resourceID;
	}
	
	public void setDataType(String dataType) {
		String[] dataTypeComponents = dataType.split(":");
		 
		if(dataTypeComponents.length > 1) {
			String atomicTypes = dataTypeComponents[1];
			values =  atomicTypes.split(",");
			type = dataTypeComponents[0];
		}
	}
	
	public String[] getValues() {
		return values; 
	}
	
	@Override
	public String toString() { 
		return "DigitalLifeDevice [name=" + name + ", status=" + status
				+ ", deviceID=" + deviceID + ", deviceType=" + deviceType + ", dataType=" + values +  ", action=" + action + "]";
	}
	
	private int resourceID; 
	private String name, status, deviceID, deviceType, imageNames, action;
	String type; 
	private String[] values;
}
