package com.company;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class NewSocket extends Thread {

    Socket sobj=null;
    ElectShopee eobj;
    Vector<Product>v;

    public NewSocket(Socket s ,ElectShopee eobj)
    {
        this.sobj=s;
        this.eobj=eobj;
    }

    public void run()
    {
        File file = null;
        FileOutputStream ft = null;
        ObjectOutputStream fout=null;
        FileInputStream fis = null;
        try {
            Connection con=eobj.getConnection();
            DataOutputStream os=new DataOutputStream(sobj.getOutputStream());
            DataInputStream is=new DataInputStream(sobj.getInputStream());

            v=new Vector<>();
            Statement stmt= null;
            try {
                stmt = con.createStatement();
                Product p;
                ResultSet rs=stmt.executeQuery("select * from Products");

                while(rs.next()) {

                    p=new Product();
                    p.setData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7));
                    v.add(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            file = new File("data.txt"+Thread.currentThread().getName());
            //Writing Vector data to file
             ft=new FileOutputStream(file);
             fout=new ObjectOutputStream(ft);  //file output object

            fout.writeObject(v);
            fout.flush();
            //Code for convert file data to byte

             fis=new FileInputStream(file);

            byte[] bytes = new byte[(int)file.length()];

            fis.read(bytes);

            os.write(bytes);
            os.flush();

            String name;
            String num;
            name =is.readUTF();

            do{
                num =is.readUTF();

                os.writeInt(eobj.clientBuy(name,num));
                name =is.readUTF();

            }while(!(name.compareToIgnoreCase("complete")==0));
            sobj.close();


        } catch (IOException e) {
            //e.printStackTrace();
            //System.out.println("For Thread "+Thread.currentThread().getName()+e);
        } finally {
            try {
                fis.close();
                fout.close();
                ft.close();
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {

            }




        }
    }

}
