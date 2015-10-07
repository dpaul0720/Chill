package com.example.digitallifesampleandroidapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class eServiceClient implements Runnable {
	private DeviceListActivity mainActivity; 

	public eServiceClient( DeviceListActivity mainActivity) {
		this.mainActivity = mainActivity; 
	}
	
	public static final String ESERVICE_URL = "https://systest.digitallife.att.com/messageRelay/pConnection?app2=\"\"\"&uuid=7&key=6FCE715ECD934D06A5C678DE271B242A";
	public void run() { 
		DigitalLifeController dlc = DigitalLifeController.getInstance(); 

		try {
			InputStream is = dlc.getInputStreamForResource(ESERVICE_URL);
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedReader br = new BufferedReader(new InputStreamReader(bis));
			String line = "";
			while( (line = br.readLine()) != null) {
				line = line.replaceAll("\"\"\"", "");

				if(!line.startsWith("*")) {
					System.out.println("Event Recieved:  "+line);
					JSONObject event = (JSONObject) new JSONParser().parse(line);
					mainActivity.updateDeviceWithEvent(event);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}