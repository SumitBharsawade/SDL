package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

public class ShowInfo {

    Vector<Product> v;
    Scanner sc=new Scanner(System.in);
    Stack<Product> buyStack = new Stack<>();
    Scanner cin=new Scanner(System.in);


    public void getProducts()
    {
        ListIterator litr = v.listIterator() ;
        Product temp;

        System.out.println("**************Avilable Products***********");


        while(litr.hasNext())
        {
            temp=(Product)litr.next();
            System.out.println(temp.name+"("+temp.ModelNumber+")");


        }
        System.out.println("\n---------------------------------------");
    }

    public void getProductWithInfo()
    {


        System.out.println("**************Avilable Products Information***********");
        for(Object temp:v)
        {
            //temp=(Product)temp;

           // ((Product) temp).ShowProductInfo();
            System.out.println(" Product Name :"+((Product) temp).name);
            System.out.println(" Product Model Number :"+((Product) temp).ModelNumber);
            System.out.println(" Product Company Name :"+((Product) temp).proObj.getProperty("company"));
            System.out.println(" Product Colour :"+((Product) temp).hashObj.get("colour") );
            System.out.println(" Product Weight :"+((Product) temp).hashObj.get("weight") +" gram");
            System.out.println(" Product Size :"+((Product) temp).proObj.getProperty("size"));
            System.out.println(" Product Price :"+((Product) temp).price+" Rupees");
            System.out.println("\n---------------------------------------");
        }
    }


    public void Buy(DataInputStream is, DataOutputStream os) throws IOException  //check product name and model number is right
    {
        String choice="no";
        boolean flag=false;
        do {
            System.out.println("**************Buying Product***********");
            System.out.print("\nEnter Product Name  :");
            String name=cin.next();

            System.out.print("\nEnter Model Number :");
            String num=cin.next();


            for(Object temp:v)
            {

                if(name.compareToIgnoreCase(((Product) temp).name)==0&&num.compareToIgnoreCase(((Product) temp).ModelNumber)==0)
                {

                    os.writeUTF(name);
                    os.writeUTF(num);

                    int msg=is.readInt();
                    if(msg==1)
                    {
                        buyStack.push((Product) temp);
                        System.out.println("\nItem added to your cart");
                        flag=true;

                    }
                    else if (msg==0)
                    {
                        System.out.println("\nProduct is out of Stock");
                    }

                    break;
                }

            }
            if(!flag)
            {
                System.out.println("\nProduct not found \n -----please Enter correct product!---");
            }
            flag=false;

            System.out.print("\nWant to Buy Another (yes or no) :");
            choice=cin.next();
        }while(choice.compareToIgnoreCase("yes")==0);
        os.writeUTF("complete");
        buy1();

    }


    public void buy1()
    {
        Product p;
        Double totalPrice=0.0;

        try
        {

            while(true)
            {
                p=buyStack.pop();
               // tempStack.push(p);
                ShowProductInfo(p);

                totalPrice+=p.price;

            }

        }
        catch(EmptyStackException e)
        {

        }

        System.out.println("\n Total Price :"+totalPrice);

        //System.out.print("\n Conform to Buying (yes or no):");


        System.out.println("\nThank you........");

    }

    private void ShowProductInfo(Product p) {

        System.out.println(" Product Name :"+p.name);
        System.out.println(" Product Model Number :"+p.ModelNumber);
        System.out.print("\n Product Company Name :"+p.proObj.getProperty("company"));
        System.out.print(" Product Colour :"+p.hashObj.get("colour") );
        System.out.print("\n Product Weight :"+p.hashObj.get("weight")+" gram" );
        System.out.print("\n Product Size :"+p.proObj.getProperty("size"));
        System.out.println(" Product Price :"+p.price+" Rupee");
        System.out.println("----------------------------------------------");
    }

}
