package com.ua.in.localhome.lightcontrol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private class ASAS extends AsyncTask<String, Void, Void>{

    	
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

		@Override
		protected Void doInBackground(String... task) {
			try {

				//String message = new StringBuilder().append((char) msg_length).append((char) b1).append((char) b2).toString();
				String message = Character.toChars(3).toString()+Character.toChars(255).toString()+Character.toChars(255).toString();
				  
				final int server_port = 1099;  
				final DatagramSocket s = new DatagramSocket();  
				final InetAddress local = InetAddress.getByName("192.168.1.4");  
				  
				byte[] messageBytes = message.getBytes();  
				
			    messageBytes[0] = (byte) 2;
				
				if(task[0] == "ON"){
				    messageBytes[1] = (byte) 255;
				    messageBytes[2] = (byte) 255;
				}
				
				if(task[0] == "OFF"){
				    messageBytes[1] = (byte) 0;
				    messageBytes[2] = (byte) 0;
				}
				Character.toChars(3);
				DatagramPacket p = new DatagramPacket(messageBytes, 3, local, server_port);  
				  
				s.send(p);  
				Log.d("tag", "message sent");  
				  
				  
				s.close(); 

			
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
    	
    }
    
    public void ligthControlOnClick(View v){
    	
    	ASAS aa = new ASAS();
		
		switch (v.getId()) {
		case R.id.all_on:
			Log.d("test" , "on");
	    	aa.execute("ON");
			break;
		case R.id.all_off:
	    	aa.execute("OFF");
			Log.d("test" , "off");
			break;
		}
    	
		
	}

	@Override
	public void onClick(View v) {
		
    	Log.d("test" , "clik 1");
		// TODO Auto-generated method stub
		
	}
    
}
