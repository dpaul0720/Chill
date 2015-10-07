package letschill.netflixchill;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;
import android.media.AudioManager;


import com.example.digitallifesampleandroidapp.DigitalLifeController;


public class Customize extends ActionBarActivity {


    private AudioManager myAudioManager;

    public void reset(View view){

        // This part will log in

        DigitalLifeController dlc = DigitalLifeController.getInstance();
        dlc.init("BE_9B3CF8E2249F53DB_1", "https://systest.digitallife.att.com");
        try {
            dlc.login("553474453", "NO-PASSWD");
        } catch (Exception e) {
            System.out.println("Logout Failed");
            e.printStackTrace();
            return;
        }

        dlc.updateDevice("PE00000002", "switch", "off");
        try {Thread.sleep(1000);} catch (Exception e) {}
        dlc.updateDevice("DE00000005","multilevel", "50");
        try {Thread.sleep(1000);} catch (Exception e) {}
        dlc.updateDevice("DT0000000A", "thermostat-mode", "heat");
        try {Thread.sleep(1000);} catch (Exception e) {}
        dlc.updateDevice("DT0000000A", "cool-setpoint", "125");
        try {Thread.sleep(1000);} catch (Exception e) {}



//unmute audio
        AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        amanager.setStreamMute(AudioManager.STREAM_RING, false);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);

    }


    public void nightlight(View view) {
        // This part will log in

        DigitalLifeController dlc = DigitalLifeController.getInstance();
        dlc.init("BE_9B3CF8E2249F53DB_1", "https://systest.digitallife.att.com");
        try {
            dlc.login("553474453", "NO-PASSWD");
        } catch (Exception e) {
            System.out.println("Logout Failed");
            e.printStackTrace();
            return;
        }
//Nightlight turns on


        dlc.updateDevice("PE00000002", "switch", "on");
        try {Thread.sleep(1000);} catch (Exception e) {}




    }


    public void temp(View view) {

// This part will log in

        DigitalLifeController dlc = DigitalLifeController.getInstance();
        dlc.init("BE_9B3CF8E2249F53DB_1", "https://systest.digitallife.att.com");
        try {
            dlc.login("553474453", "NO-PASSWD");
        } catch (Exception e) {
            System.out.println("Logout Failed");
            e.printStackTrace();
            return;
        }

// this part will change the thermostat



        dlc.updateDevice("DT0000000A", "thermostat-mode", "cool");
        try {Thread.sleep(1000);} catch (Exception e) {}
        dlc.updateDevice("DT0000000A", "cool-setpoint", "116");
        try {Thread.sleep(1000);} catch (Exception e) {}

    }



    public void donotdisturb(View view){

        Context context = getApplicationContext();
        CharSequence text = "Do Not Disturb Toggled";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        //mute audio
        AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        amanager.setStreamMute(AudioManager.STREAM_RING, true);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);


        //AudioManager audio = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        /**
         * To Enable silent mode.....
         */
        //audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);

        /**
         * To Enable Ringer mode.....
         */
        //audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);


    }

    public void adjustvolume(View view){


    }

    public void audio(View view){


    }

    public void light(View view){


// This part will log in

        DigitalLifeController dlc = DigitalLifeController.getInstance();
        dlc.init("BE_9B3CF8E2249F53DB_1", "https://systest.digitallife.att.com");
        try {
            dlc.login( "553474453", "NO-PASSWD");
        } catch (Exception e) {
            System.out.println("Logout Failed");
            e.printStackTrace();
            return;
        }

// this part will dim the light down to level 1

        dlc.updateDevice("DE00000005","multilevel", "1");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
    }

}
