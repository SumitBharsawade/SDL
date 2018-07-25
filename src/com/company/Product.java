package com.company;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Properties;


class Product implements Serializable {


    public String name;
    public String ModelNumber;
    public Double price;


    public HashMap<String,String> hashObj=new HashMap<>();

    public Properties proObj=new Properties();



   void setData(String name,String mo_num,String cmp_name,String colour,String weight,String size,Double price)
   {

       this.name=name;
       ModelNumber=mo_num;
       this.price=price;
       hashObj.put("colour",colour);           //hashmap putting
       hashObj.put("weight",weight);

       proObj.setProperty("company",cmp_name);      //properties putting
       proObj.setProperty("size",size);

   }

}

