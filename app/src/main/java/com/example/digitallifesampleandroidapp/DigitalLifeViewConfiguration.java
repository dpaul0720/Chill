package com.example.digitallifesampleandroidapp;

import java.util.ArrayList;

public class DigitalLifeViewConfiguration {

	public static ArrayList<String> viewableDeviceList = new ArrayList<String>();
	
	public static ArrayList<String> editableDeviceList = new ArrayList<String>();
	
	static {
		viewableDeviceList.add("contact-sensor");
		viewableDeviceList.add("water-sensor");
		viewableDeviceList.add("smart-plug");
		viewableDeviceList.add("door-lock");

		editableDeviceList.add("smart-plug");
		editableDeviceList.add("door-lock");	
	}
	
}
