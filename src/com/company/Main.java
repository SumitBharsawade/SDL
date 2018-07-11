package com.company;


import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args)throws Exception {

        ShowInfo E1=new ShowInfo();
        Scanner cin=new Scanner(System.in);
        Socket s=new Socket("localhost",3355);

        System.out.println("--->>connection establish");
        DataInputStream is=new DataInputStream(s.getInputStream());
        DataOutputStream os=new DataOutputStream(s.getOutputStream());

        //ObjectInputStream din=new ObjectInputStream(is);

        byte []bytes=new byte[1024*4];
       int length=is.read(bytes);

       if(length==0)
       {
           System.out.println("null data accepted");
           System.exit(0);
       }

        File file = new File("C:\\Users\\hp\\Desktop\\abc2.txt");
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(bytes,0,length);


        FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\abc2.txt");
        ObjectInputStream fout=new ObjectInputStream(fis);  //file output object

        int count=1;
        //Vector<Product>temp=new Vector<>();
//     while(E1.v.add((Product) fout.readObject()));

try {
    E1.v = (Vector<Product>) fout.readObject();
}catch (Exception e)
{
    e.printStackTrace();
}
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
                  /*  while(true)
                    {

                       // E1.Buy();
                        System.out.print("\nWant to Buy Another (yes or no) :");


                        System.out.print("\nWant to Buy Another (yes or no) :");
                        String choice=cin.next();

                        if(choice.compareToIgnoreCase("no")==0)
                        {
                            //E1.buy1();

                            break;
                        }

                    }*/
                    String choice="no";
                  do {
                      System.out.println("**************Buying Product***********");
                      System.out.print("\nEnter Product Name  :");
                      String name=cin.next();

                      System.out.print("\nEnter Model Number :");
                      String num=cin.next();

                      os.writeUTF(name);
                      os.writeUTF(num);

                      int msg=is.readInt();
                      if(msg==1)
                      {
                          System.out.println("\nItem added to your cart");
                          E1.Buy(name,num);
                      }
                      else if (msg==0)
                      {
                          System.out.println("\nProduct is out of Stock");
                      }
                      else if(msg==-1)
                      {
                          System.out.println("\nProduct not found \n -----please Enter correct product!---");
                      }
                      System.out.print("\nWant to Buy Another (yes or no) :");
                       choice=cin.next();
                  }while(choice.compareToIgnoreCase("yes")==0);
                  os.writeUTF("complete");
                  E1.buy1();
                   // break;

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

