package com.example.beast2;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import static android.provider.Settings.NameValueTable.NAME;

public class MainActivity extends AppCompatActivity {

    private  BluetoothServerSocket mmServerSocket=null;
    OutputStream outputStream=null;
    public final UUID u = UUID.fromString("00000000-0000-1000-8000-00805F9B34FB");
    private BluetoothAdapter bluetoothAdapter=null;
    private BluetoothSocket bluetoothSocket=null;
    private BluetoothDevice hc05=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /** This part of code is used to show the paired device.     */
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter==null)
        {
            System.out.println("Bluetooth not supported.----------------------------------------------------------------------");
        }
        else
        {
            System.out.println(bluetoothAdapter.getBondedDevices());
            hc05 = bluetoothAdapter.getRemoteDevice(/*"AC:9E:17:C5:0A:DE"*/"B0:68:E6:29:30:5E");
            System.out.println(hc05.getName());
        }
        /** and ends here.  */
        System.out.println();



        /** This part of code is used to show off my device.
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 1000);
        startActivity(discoverableIntent);
         and ends here.  */

        System.out.println(hc05.getAddress()+"------------------------------------------------");

        int c=0;

            /*try {
                bluetoothSocket = hc05.createRfcommSocketToServiceRecord(u);
                System.out.println(bluetoothSocket.isConnected() + "---------------------------------------------");
                //c++;
                //bluetoothSocket.connect();
                //System.out.println(bluetoothSocket.isConnected() + "------------------------------------------------------------------------");

            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Error while creating socket.--------------------------------------------------");
            }*/



        /*OutputStream outputStream= null;
        try {
            outputStream = bluetoothSocket.getOutputStream();
            System.out.println(outputStream+"123456789---------------------------------");
            String str="xxx";
            byte b[]=str.getBytes();
            outputStream.write(b);
            outputStream.flush();
            System.out.println("Sent S--------------------------------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error----------------------------------------------");
        }*/


        /*BluetoothServerSocket tmp = null;

        try {
            tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, u);
            System.out.println("Listening---------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try {
                bluetoothSocket = tmp.accept();
                System.out.println("Accepting---------------------------------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (NullPointerException n){
            System.out.println("Exception kheye gechii--------------------------------------------");
        }

        if (bluetoothSocket != null) {
            // A connection was accepted. Perform work associated with
            // the connection in a separate thread.
            System.out.println("Creating------------------------------------------");
            createConnection(bluetoothSocket);
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //createConnection();


        /*try {
            OutputStream outputStream=bluetoothSocket.getOutputStream();
            outputStream.write(69);
            System.out.println("Writing data------------------------------------");
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Unsuccessfull-----------------------------");
        }*/



        try {
            openBT();
        }catch (IOException e){
            System.out.println("SErious Exception-------------------------------");
        }

    }


    void openBT() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        bluetoothSocket = hc05.createRfcommSocketToServiceRecord(uuid);
        bluetoothSocket.connect();
        outputStream = bluetoothSocket.getOutputStream();
    }

    public void createConnection(BluetoothSocket bluetoothSocket)
    {
        try {
            System.out.println("Trying to connect---------------------------------------------------");
            bluetoothSocket.connect();
            System.out.println("Connected successfully.------------------------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while communication. -------------------------------------------------------------------");
        }
    }


}
