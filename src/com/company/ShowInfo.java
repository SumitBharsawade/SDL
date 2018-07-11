package com.company;

import java.util.*;

public class ShowInfo {

    Vector<Product> v;
    Scanner sc=new Scanner(System.in);

    Stack<Product> buyStack = new Stack<>();
    //Stack<Product> tempStack = new Stack<>();


    public void getProducts()
    {
        ListIterator litr = v.listIterator() ;
        Product temp;

        System.out.println("**************Avilable Products***********");
        /**  for(Object temp:v)
         {
         temp=(Product)temp;

         System.out.println( ((Product) temp).getName());

         }*/

        while(litr.hasNext())
        {
            temp=(Product)litr.next();
            System.out.println(temp.getName());

        }
        System.out.println("\n---------------------------------------");
    }

    public void getProductWithInfo()
    {


        System.out.println("**************Avilable Products Information***********");
        for(Object temp:v)
        {
            //temp=(Product)temp;

            ((Product) temp).ShowProductInfo();

        }
    }


    public void Buy(String name,String num)
    {

        for(Object temp:v)
        {

            if(name.compareToIgnoreCase(((Product) temp).getName())==0&&num.compareToIgnoreCase(((Product) temp).getMnumber())==0)
            {

                buyStack.push((Product) temp);

            }

        }


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
                p.ShowProductInfo();

                totalPrice+=p.getPrice();

            }

        }
        catch(EmptyStackException e)
        {

        }

        System.out.println("\n Total Price :"+totalPrice);

        //System.out.print("\n Conform to Buying (yes or no):");


        System.out.println("\nThank you........");

    }

}
