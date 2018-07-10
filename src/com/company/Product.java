package com.company;


import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

class Product implements Comparable,Serializable {


   String name;
   String ModelNumber;
   Double price;
   int Quantity;

   HashMap<String,String> Bproperties=new HashMap<String,String>();

   Properties proObj=new Properties();


   BitSet properties=new BitSet(3);  //1.bit=HasDiscount 2.bit=Ispopular 3.isReplacable

   transient  Scanner sc=new Scanner(System.in);



   Product(String n,String num,Double pr,int Quant){

       name=n;
       ModelNumber=num;
       price=pr;
       Quantity=Quant;

   }


   public String getName()
   {
       return name;
   }

   public void decQuantity()
   {
       --Quantity;
   }

   public String getMnumber()
   {
       return ModelNumber;
   }

   public int getQuantity()
   {
       return Quantity;
   }

   public Double getPrice()
   {
       return price;
   }


   public void setProperties()
   {
       System.out.print("\nEnter Has Discount :");
       if(sc.next().compareToIgnoreCase("yes")==0)
       {
           properties.set(0);

       }


       System.out.print("\nEnter Is Product very Popular :");
       if(sc.next().compareToIgnoreCase("yes")==0)
       {
           properties.set(1);

       }

       System.out.print("\nEnter is Product Replacable :");
       if(sc.next().compareToIgnoreCase("yes")==0)
       {
           properties.set(2);

       }
   }

   public void getProperties()
   {

       if(properties.get(0))
       {
           System.out.println("Product Have Discount:");

       }
       else
       {
           System.out.println("Product Not Have Discount:");
       }


       if(properties.get(1))
       {
           System.out.println(" Product is Popular :");

       }
       else
       {
           System.out.println(" Product is not much Popular :");
       }

       if(properties.get(2))
       {
           System.out.println(" Product is Replacable :");

       }
       else
       {
           System.out.println(" Product is not  Replacable :");
       }

   }


   public void setBproperties()
   {
       System.out.print("\nEnter Product Colour :");
       String colour=sc.next();

       System.out.print("\nEnter Product Weight :");
       String weight=sc.next();

       System.out.print("\nEnter Product Company name:");
       String company=sc.next();

       System.out.print("\nEnter Product Size:");
       String size=sc.next();



       Bproperties.put("colour",colour);           //hashmap puting
       Bproperties.put("weight",weight);

       proObj.setProperty("company",company);
       proObj.setProperty("size",size);



   }

   public void getBproperties()
   {

       System.out.print(" Product Colour :"+Bproperties.get("colour") );
       System.out.print("\n Product Weight :"+Bproperties.get("weight") );
       System.out.print("\n Product Company Name :"+proObj.getProperty("company"));
       System.out.print("\n Product Size :"+proObj.getProperty("size"));



   }

   void ShowProductInfo()
   {
       System.out.println(" Product Name :"+name);
       System.out.println(" Product Model Number :"+ModelNumber);
       System.out.println(" Product Price :"+price);

       getProperties();
       getBproperties();
       System.out.println("\n---------------------------------------");

   }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

