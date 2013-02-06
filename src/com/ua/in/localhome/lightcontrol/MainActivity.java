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

	boolean[] state = new boolean[48];
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
            	ASAS aa = new ASAS();
        		
       			Log.d("test" , "on");
       	    	aa.execute();
        	    	
            	Log.d("test" , "clik 1"+v.getId());
            	
            	
            }
        };
        
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    



    
    
    private class ASAS extends AsyncTask<boolean[], Void, Void>{

    	
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		@Override
		protected Void doInBackground(boolean[] ... sendState) {
			try {
				final int server_port = 1099;  
				final DatagramSocket s = new DatagramSocket();  
				final InetAddress local = InetAddress.getByName("192.168.1.4");
				
				byte[] out = BitArrayToByteArray(sendState[0]);
				Log.d("tag", "message sent"+sendState[0]);  
				DatagramPacket p = new DatagramPacket(out, out.length, local, server_port);  
				s.send(p);  
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
    

    public static byte[] BitArrayToByteArray(boolean[] bits){
      int bytesize = bits.length / 7;
      if (bits.length % 7 > 0)
        bytesize++;

      // For the result
      byte[] bytes = new byte[bytesize];
      byte value = 0;
      byte significance = 1;

      // Remember where in the input/output arrays
      int bytepos = 0;
      int bitpos = 0;

      while (bitpos < bits.length)
      {
        // If the bit is set add its value to the byte
        if (bits[bitpos])
          value += significance;

        bitpos++;

        if (bitpos % 7 == 0){
          bytes[bytepos] = value;
          bytepos++;
          value = 0;
          significance = 1;
        }
        else
        {
          // Another bit processed, next has doubled value
          significance *= 2;
        }
      }
      bytes[0]=(byte)2;
      return bytes;
    }

    
    public void ligthControlOnClick(View v){
    	ASAS aa = new ASAS();
		
		switch (v.getId()) {
		case R.id.all_on:
			for (int i = 0; i < state.length; i++) {
				state[i] = true;
	    	}
			Log.d("test" , "on");
	    	aa.execute(state);
			break;
		case R.id.all_off:
			for (int i = 0; i < state.length; i++) {
				state[i] = false;
	    	}
	    	aa.execute(state);
			Log.d("test" , "off");
			break;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
