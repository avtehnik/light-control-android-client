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

	boolean[] state = new boolean[(8*5)];
	
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
      int bytesize = (bits.length / 7)+1;
      byte[] bytes = new byte[bytesize];
      int bytepos = 1;
      int bitpos = 0;
      int pos = 0;
      String mybyte="";
      while(bitpos<bits.length){
      	  if(bits[bitpos]==true){
        	  mybyte+="1";
    	  }else{
        	  mybyte+="0";
    	  }
    	  pos++;
    	  if (pos==8){
    		  bytes[bytepos] = (byte) Integer.parseInt(mybyte, 2);
              bytepos++;
              mybyte="";
        	  pos=0;
    	  }
    	  bitpos++;
      }
      bytes[0]=(byte)(bytesize);
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


		case R.id.lamp1:
			state[0] = !state[0];
			aa.execute(state);
		break;
		case R.id.lamp2:
			state[1] = !state[1];
			aa.execute(state);
		break;
		case R.id.lamp3:
			state[2] = !state[2];
			aa.execute(state);
		break;
		case R.id.lamp4:
			state[3] = !state[3];
			aa.execute(state);
		break;
		case R.id.lamp5:
			state[4] = !state[4];
			aa.execute(state);
		break;
		case R.id.lamp6:
			state[5] = !state[5];
			aa.execute(state);
		break;
		case R.id.lamp7:
			state[6] = !state[6];
			aa.execute(state);
		break;
		case R.id.lamp8:
			state[7] = !state[7];
			aa.execute(state);
		break;
		case R.id.lamp9:
			state[8] = !state[8];
			aa.execute(state);
		break;
		case R.id.lamp10:
			state[9] = !state[9];
			aa.execute(state);
		break;
		case R.id.lamp11:
			state[10] = !state[10];
			aa.execute(state);
		break;
		case R.id.lamp12:
			state[11] = !state[11];
			aa.execute(state);
		break;
		case R.id.lamp13:
			state[12] = !state[12];
			aa.execute(state);
		break;
		case R.id.lamp14:
			state[13] = !state[13];
			aa.execute(state);
		break;
		case R.id.lamp15:
			state[14] = !state[14];
			aa.execute(state);
		break;
		case R.id.lamp16:
			state[15] = !state[15];
			aa.execute(state);
		break;
		case R.id.lamp17:
			state[16] = !state[16];
			aa.execute(state);
		break;
		case R.id.lamp18:
			state[17] = !state[17];
			aa.execute(state);
		break;
		case R.id.lamp19:
			state[18] = !state[18];
			aa.execute(state);
		break;
		case R.id.lamp20:
			state[19] = !state[19];
			aa.execute(state);
		break;
		case R.id.lamp21:
			state[20] = !state[20];
			aa.execute(state);
		break;
		case R.id.lamp22:
			state[21] = !state[21];
			aa.execute(state);
		break;
		case R.id.lamp23:
			state[22] = !state[22];
			aa.execute(state);
		break;
		case R.id.lamp24:
			state[23] = !state[23];
			aa.execute(state);
		break;
		case R.id.lamp25:
			state[24] = !state[24];
			aa.execute(state);
		break;
		case R.id.lamp26:
			state[25] = !state[25];
			aa.execute(state);
		break;
		case R.id.lamp27:
			state[26] = !state[26];
			aa.execute(state);
		break;
		case R.id.lamp28:
			state[27] = !state[27];
			aa.execute(state);
		break;
		case R.id.lamp29:
			state[28] = !state[28];
			aa.execute(state);
		break;
		case R.id.lamp30:
			state[29] = !state[29];
			aa.execute(state);
		break;
		case R.id.lamp31:
			state[30] = !state[30];
			aa.execute(state);
		break;
		case R.id.lamp32:
			state[31] = !state[31];
			aa.execute(state);
		break;
		case R.id.lamp33:
			state[32] = !state[32];
			aa.execute(state);
		break;
		case R.id.lamp34:
			state[33] = !state[33];
			aa.execute(state);
		break;
		case R.id.lamp35:
			state[34] = !state[34];
			aa.execute(state);
		break;
		case R.id.lamp36:
			state[35] = !state[35];
			aa.execute(state);
		break;
		case R.id.lamp37:
			state[36] = !state[36];
			aa.execute(state);
		break;
		case R.id.lamp38:
			state[37] = !state[37];
			aa.execute(state);
		break;
		case R.id.lamp39:
			state[38] = !state[38];
			aa.execute(state);
		break;
		case R.id.lamp40:
			state[39] = !state[39];
			aa.execute(state);
		break;
	 

		}
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
