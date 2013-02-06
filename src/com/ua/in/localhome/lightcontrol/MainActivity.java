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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener  {

	boolean[] out ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
            	ASAS aa = new ASAS();
        		
       			Log.d("test" , "on");
       	    	aa.execute("ON");
        	    	
            	Log.d("test" , "clik 1"+v.getId());
            	
            	
            }
        };
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        int btnid = 0;
        for (int i = 0; i < 5; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < 2; j++) {
                Button btnTag = new Button(this);
                btnTag.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                btnTag.setText(" " + (j + 1 + (i * 4 )));
                btnTag.setId(btnid);
                btnTag.setOnClickListener(handler);
                row.addView(btnTag);
                btnid++;
            }
            layout.addView(row);
        }
        setContentView(layout);
        //setContentView(R.layout.activity_main);
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
				final int server_port = 1099;  
				final DatagramSocket s = new DatagramSocket();  
				final InetAddress local = InetAddress.getByName("192.168.1.4");  


				byte[] messageBytes = new byte[3];
				messageBytes[0] = (byte) 2;
				
				if(task[0] == "ON"){
				    messageBytes[1] = (byte) 255;
				    messageBytes[2] = (byte) 255;
				}
				
				if(task[0] == "OFF"){
				    messageBytes[1] = (byte) 0;
				    messageBytes[2] = (byte) 0;
				}
				
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
    
    private byte[] boolsAsBytes(boolean[] data) {
    	byte[] out = new byte[(data.length / 8) + 2];
    	out[0] = (byte) (out.length - 1);
    	
    	for (int i = 0, j = out.length, k = 7; i < data.length; i++) {
    		out[j] |= (data[i] ? 1 : 0) << k--;
    	    if (k < 0) {
    	    	j++;
    	    	k = 7;
    	    }
    	}
    	return out;
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
