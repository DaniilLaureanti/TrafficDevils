package com.google.android.trafficdevils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String QUERY = "get";
    private static final String ERROR_CODE = "error";
    private Socket socket;
    private OnReadFromServerListener listener;

    public void setListener(OnReadFromServerListener listener) {
        this.listener = listener;
    }

    public void read(InetSocketAddress socketAddress){
        ConnectTask connectTask = new ConnectTask();
        connectTask.execute(socketAddress);
    }

    class ConnectTask extends AsyncTask<InetSocketAddress, Void, String> {

        @Override
        protected String doInBackground(InetSocketAddress... inetSocketAddresses) {
            InetSocketAddress socketAddress = inetSocketAddresses[0];
            String retValue = ERROR_CODE;
            try {
                socket = new Socket();
                socket.connect(socketAddress, 5000);
                if (socket.isConnected()){
                    sendQuery();
                    retValue = readAnswer();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("CONTROL", retValue);
            return retValue;
        }

        private String readAnswer() {
            try {
                Scanner scanner = new Scanner(socket.getInputStream());
                while (!scanner.hasNextLine()){

                }
                return scanner.nextLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ERROR_CODE;
        }

        private void sendQuery() {
            try {
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(QUERY);
                printWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(listener != null) {
                listener.action(s);
            }
        }
    }

    interface OnReadFromServerListener{
        void action(String s);
    }
}
