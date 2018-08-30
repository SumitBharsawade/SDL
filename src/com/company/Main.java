package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args)throws Exception {

        ShowInfo E1=new ShowInfo();
        Scanner cin=new Scanner(System.in);
        Socket s=new Socket("localhost",2022);

        System.out.println("--->>connection establish");
        DataInputStream is=new DataInputStream(s.getInputStream());
        DataOutputStream os=new DataOutputStream(s.getOutputStream());
        ObjectInputStream ois=new ObjectInputStream(s.getInputStream());

         E1.v=(Vector<Product>) ois.readObject();
        System.out.println("--->>read complete");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProductFrame frame=new ProductFrame(E1.v,is,os);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });


////////////////////////////
   /*     while(true)
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

                    E1.Buy(is,os);

                case 4:
                    s.close();
                    System.exit(0);
                 break;
                 default:
                     System.out.print("\nChoice Not Found \n     try again.....");

            }

        }
*/
    }
}

