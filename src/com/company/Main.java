package com.company;


import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args)throws Exception {

        ShowInfo E1=new ShowInfo();
        Scanner cin=new Scanner(System.in);
        Socket s=new Socket("localhost",3344);

        System.out.println("--->>connection establish");
        DataInputStream is=new DataInputStream(s.getInputStream());
        //ObjectInputStream din=new ObjectInputStream(is);

        byte []bytes=new byte[1024*4];
       int length=is.read(bytes);

       if(length==0)
       {
           System.out.println("null data accepted");
       }

        File file = new File("C:\\Users\\hp\\Desktop\\abc2.txt");
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(bytes,0,length);


        FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\abc2.txt");
        ObjectInputStream fout=new ObjectInputStream(fis);  //file output object
       E1.v=(Vector<Product>) fout.readObject();


      // E1.v=(Vector)din.readObject();
        System.out.println("--->>Vector accepted");

        while(true)
        {
            System.out.println("\n-------------------------------------");
            System.out.println("--->>Enter option as follows :");

            System.out.print("1.Show Product\n2.Show product Info\n3.Buy\n4.exit\n--->>>");
            int ch=cin.nextInt();

            System.out.println("\n-------------------------------------");

            switch(ch)
            {

                case 1:

                    E1.getProducts();

                    break;

                case 2:
                    E1.getProductWithInfo();

                    break;

                case 3:
                    while(true)
                    {

                       // E1.Buy();

                        System.out.print("\nWant to Buy Another (yes or no) :");
                        String choice=cin.next();

                        if(choice.compareToIgnoreCase("no")==0)
                        {
                            //E1.buy1();

                            break;
                        }

                    }
                    break;

                case 4:
                    s.close();
                    System.exit(0);
                 break;
                 default:
                     System.out.print("\nChoice Not Found \n     try again.....");

            }

        }

    }
}

