package com.company;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class NewSocket extends Thread {

    Socket sobj=null;
    ElectShopee eobj;


    public NewSocket(Socket s ,ElectShopee eobj)
    {
        this.sobj=s;
        this.eobj=eobj;
    }

    public void run()
    {
        try {
            System.out.println("--->>Connection Establish");
            DataOutputStream os=new DataOutputStream(sobj.getOutputStream());
            DataInputStream is=new DataInputStream(sobj.getInputStream());



            //Writing Vector data to file
            FileOutputStream ft=new FileOutputStream("C:\\Users\\hp\\Desktop\\abc.txt");
            ObjectOutputStream fout=new ObjectOutputStream(ft);  //file output object

            fout.writeObject(eobj.v);
            fout.flush();
            //Code for convert file data to byte
            File file = new File("C:\\Users\\hp\\Desktop\\abc.txt");
            FileInputStream fis=new FileInputStream(file);

            byte[] bytes = new byte[(int)file.length()];

            fis.read(bytes);

            os.write(bytes);
            os.flush();
            System.out.println("readCompleted");

            String name;
            String num;
            name =is.readUTF();

            do{
                num =is.readUTF();
                // int result=clientBuy(name,num);
                os.writeInt(eobj.clientBuy(name,num));
                name =is.readUTF();

            }while(!(name.compareToIgnoreCase("complete")==0));
            sobj.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
