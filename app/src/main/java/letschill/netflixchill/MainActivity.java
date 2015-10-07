package letschill.netflixchill;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import android.os.AsyncTask;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
//import org.apache.http.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


import android.os.AsyncTask;

import com.example.digitallifesampleandroidapp.DigitalLifeController;

public class MainActivity extends ActionBarActivity {




    public void settings(View view) {  // this is the function performed when the settings button is pressed


//unmute audio
        AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        amanager.setStreamMute(AudioManager.STREAM_RING, false);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);


// This part displays a message on the computer
        Log.v("tag", "Settings");


        // this part displays a message on the screen to let them now they are going to the settings/ customize menu
        Context context = getApplicationContext();
        CharSequence text = "Customizing Chill";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();



// this part moves them to the next activity of the customizing menu
        Intent intent = new Intent(this, Customize.class);
        startActivity(intent);



    }

    public void message(View view) { // this part sends a message to "bae" or a significant other with the invitation to netflix & chill


// this part displays a message to the user explaining that they are going to send a message
        Context context = getApplicationContext();
        CharSequence text = "Initiating Chill";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


// this part opens allows people to chose which application to send the message with and then defaults the text to "netflix & Chill?"
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Netflix & Chill?");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);


    }


    public void chill(View view) {  // this is the main feature of the program that *will automatically dim lights, turn down thermostat, and other romantic options


        // this part sends a message to the user to let them know the button has been activated
        Context context = getApplicationContext();
        CharSequence text = "Initializing Chill";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


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

// this part will dim the lights

        dlc.updateDevice("DE00000005", "multilevel", "1");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        // this part will lower the thermostat

        dlc.updateDevice("DT0000000A", "thermostat-mode", "cool");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        dlc.updateDevice("DT0000000A", "cool-setpoint", "115");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }


        //Nightlight turns on


        dlc.updateDevice("PE00000002", "switch", "on");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

// This part is supposed to enable silent mode/ do not disturb mode/ priority mode/
        AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        amanager.setStreamMute(AudioManager.STREAM_RING, true);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);




    }


    public void netflixlink(View view){
// this part is a temporary feature that automatically launches to the netflix app
        Uri webpage = Uri.parse("http://www.netflix.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
