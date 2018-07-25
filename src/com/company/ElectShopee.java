package com.company;
import java.sql.*;
import java.util.*;
import java.io.*;

class ElectShopee implements Serializable {

    private  Connection con;
    Scanner sc=new Scanner(System.in);


    ElectShopee()
    {
          String url="jdbc:mysql://localhost:3306/ElectShopee?useSSL=false";
         String user="root";
         String password="root";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
             con=DriverManager.getConnection(url,user,password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(" Database Connected Successfully");
    }

    void AddProduct1()
    {
        System.out.print("\nEnter Product Name :");
        String n=sc.next();

        System.out.print("\nEnter Model Number :");
        String num=sc.next();

        System.out.print("\nEnter Product Company name:");
        String company=sc.next();


        System.out.print("\nEnter Product Colour :");
        String colour=sc.next();

        System.out.print("\nEnter Product Weight in kg:");
        String weight=sc.next();

        System.out.print("\nEnter Product Size:");
        String size=sc.next();


        System.out.print("\nEnter Product Price in Rupees:");
        Double pr=sc.nextDouble();

        System.out.print("\nEnter Product Quantity :");
        int quantity=sc.nextInt();


        //inserting into database
        try {


            PreparedStatement pstmt=con.prepareStatement("insert into Products values(?,?,?,?,?,?,?,?)");
            pstmt.setString(1,n.trim());
            pstmt.setString(2,num.trim());
            pstmt.setString(3,company.trim());
            pstmt.setString(4,colour.trim());
            pstmt.setString(5,weight.trim());
            pstmt.setString(6,size.trim());
            pstmt.setDouble(7,pr);
            pstmt.setInt(8,quantity);

           boolean i= pstmt.execute();
           if(i)
           {

               System.out.println("\n Product not Inserted try Again");
           }
           else
               System.out.println("\n Product Inserted Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    void getProducts1()
    {
        Statement stmt= null;
        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select name,model_name from Products");
            System.out.println("\n--------All products (Model number--------)");
            while(rs.next())
                System.out.println(rs.getString(1)+"("+rs.getString(2)+")");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


        void getProductWithInfo1()
        {
            Statement stmt= null;
            try {
                stmt = con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from Products");
                System.out.println("*****************************************");
                while(rs.next()) {

                    System.out.println("Name :"+rs.getString(1));
                    System.out.println("Model Name :"+rs.getString(2));
                    System.out.println("Company Name :"+rs.getString(3));
                    System.out.println("Colour :"+rs.getString(4));
                    System.out.println("Weight :"+rs.getString(5));
                    System.out.println("Size :"+rs.getString(6));
                    System.out.println("Price :"+rs.getDouble(7));
                    System.out.println("Quantity:"+rs.getInt(8));
                    System.out.println("----------------------------------------");
                }
                System.out.println("*****************************************");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    void unProduct1()
    {
        Statement stmt= null;
        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select name,model_name from Products where quantity=0");

            if(!rs.next())
            {
                System.out.println("\nCurrently noting in Unavilable Product");
                return;
            }
            System.out.println("\n--------Unavilable Products--------");
            while(rs.next())
                System.out.println(rs.getString(1)+"("+rs.getString(2)+")");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    Connection getConnection()
    {
        return con;
    }

    public int clientBuy(String name, String num) {

        Statement stmt= null;
        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select name,model_name,quantity from Products");
            while(rs.next())
                if(rs.getString(1).compareToIgnoreCase(name)==0&&rs.getString(2).compareToIgnoreCase(num)==0)
                {
                    if(rs.getInt(3)>0)
                    {
                        String query="UPDATE Products SET quantity = quantity-1 WHERE name=\""+name+"\"AND model_name=\""+num+"\"";
                        stmt.execute(query.trim());
                        return 1;
                    }
                    else
                        return 0;
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }


}

