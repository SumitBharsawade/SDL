package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ConnectingThread extends Thread {

        ElectShopee eobj;
    ServerSocket ss= null;
        public ConnectingThread(ElectShopee eobj)
        {
            this.eobj=eobj;
            try {
                ss = new ServerSocket(2020);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public  void run()
    {


        while(true)
        {

            try {

                Socket s=ss.accept();
                NewSocket obj=new NewSocket(s,eobj);
                obj.start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
