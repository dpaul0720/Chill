package com.example.digitallifesampleandroidapp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import letschill.netflixchill.R;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class DeviceListActivity extends Activity {

	private List<DigitalLifeDevice> devices; 
	private DigitalLifeController dlc; 
	private SimpleAdapter deviceAdapter; 
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_device_list);

        dlc = DigitalLifeController.getInstance(); 
        dlc.init("WE_DDD2A3FFB232DB1E_001", "https://systest.digitallife.att.com");
        try {
            dlc.login( "553045826", "NO-PASSWD");
        } catch (Exception e) {
        	System.out.println("Logout Failed");
            e.printStackTrace();
            return;
        }

        devices = dlc.fetchDevices();
        populateListView( devices);
        
        new Thread(new eServiceClient( this)).start();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    public void populateListView( List<DigitalLifeDevice> deviceList) {
        String[] keys = new String[] { "icon", "secondRow", "firstRow"};
        int[] uiComponents = { R.id.icon, R.id.firstLine, R.id.secondLine };
    	
        List<HashMap<String, Object>> deviceData = new ArrayList<HashMap<String, Object>>(deviceList.size());

        // build the data for the table view. 
        for (DigitalLifeDevice device : deviceList) {
            HashMap<String, Object> d =  new HashMap<String, Object>();
            d.put("icon", device.getResourceID());
            d.put("firstRow", device.getName());
            d.put("secondRow", device.getStatus());
            deviceData.add(d);
		}
        
        deviceAdapter = new SimpleAdapter(this, deviceData, R.layout.row_layout, keys, uiComponents);
        final ListView deviceView = (ListView)findViewById(R.id.device_list);
        deviceView.setAdapter(deviceAdapter);
        
        deviceView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(devices!=null) {
					DigitalLifeDevice device = devices.get(position);
					
					ArrayList<String> editableDeviceList = DigitalLifeViewConfiguration.editableDeviceList;

					if(!editableDeviceList.contains(device.getDeviceType())) {
						// this is not something that can be controlled.
						return; 
					}

					// retrieve the potential values this device can be set to.  ON/OFF, LOCKED/UNLOCKED, etc...
					String[] potentialValues = device.getValues();
					String pendingValue = null; 
					if(potentialValues.length>1) {
						if(potentialValues[0].equalsIgnoreCase(device.getStatus())) {
							pendingValue = potentialValues[1];
						} else {
							pendingValue = potentialValues[0];
						}
					}
					
					Toast.makeText(getApplicationContext(), device.getName() + " getting set to:  " + pendingValue, Toast.LENGTH_SHORT).show();
		            try {
						dlc.updateDevice(device.getDeviceID(), device.getAction(), pendingValue);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

    }
    
    public void updateDeviceWithEvent(final JSONObject event) {
    	String devID = (String) event.get("dev");
    	String attributeId = (String) event.get("label");
		String value = (String) event.get("value");

    	if(attributeId!=null) {
	    	for (DigitalLifeDevice device : devices) {
				if(device.getDeviceID().equalsIgnoreCase(devID)  && (value != null)) { 
					if(value == null || value.equals("")){
						System.out.println("empty value for device:  " + event);
						//this is only here to
					}
					device.setStatus( value);
					
					DigitalLifeController.decorateDevice( device);
					
			    	updateDisplay();
			    	return; 
				}
			}
    	}
    }

	private void updateDisplay() {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
//				deviceAdapter.notifyDataSetChanged();		
				populateListView(devices);
			}
		};
    	runOnUiThread(r);
	}
}