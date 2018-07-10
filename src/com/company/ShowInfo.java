package com.company;

import java.util.*;

public class ShowInfo {

    Vector<Product> v;
    Scanner sc=new Scanner(System.in);

    Stack<Product> buyStack = new Stack<>();
    Stack<Product> tempStack = new Stack<>();
    TreeSet<Product> tset = new TreeSet<>();

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
}
