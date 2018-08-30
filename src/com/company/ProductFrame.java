package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

public class ProductFrame extends JFrame {

    public ProductFrame(Vector<Product> v, DataInputStream is, DataOutputStream os){

        //getRootPane().setJMenuBar(new MenuBar());
        getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, new java.awt.Color(220,220,220)));
        setTitle("Computer Shopee");
        //setBackground(new java.awt.Color(229, 255, 204));
        ImageIcon icon=new ImageIcon("src\\com\\company\\icon.png");
        setIconImage(icon.getImage().getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screenSize.width-screenSize.width*0.1), (int)(screenSize.height-screenSize.height*0.1));
        setLocation((int) (screenSize.width*0.05), (int) (screenSize.height*0.05));

        setLayout(new BorderLayout());
        //Container c =getContentPane();
        //c.setBackground(new java.awt.Color(229, 255, 204));
        int vsize=v.size();
        String [][]data= new String[vsize][7];
        for(int i=0;i<vsize;i++)
        {
            Product p=v.get(i);
            data[i][0]=p.name;
            data[i][1]=p.ModelNumber;
            data[i][2]=p.proObj.getProperty("company");
            data[i][3]=p.hashObj.get("colour");
            data[i][4]=p.hashObj.get("weight");
            data[i][5]=p.proObj.getProperty("size");
            data[i][6]=p.price.toString();

        }

        String[]column={"Name","Model_name","Company_name","colour","Weight","Size","price"};
        JTable jt=new JTable(data,column);
        jt.getTableHeader().setBackground(new java.awt.Color(238,232,170));
        jt.setEnabled(false);
        jt.setRowHeight(50);
        //JScrollPane scpane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane sp=new JScrollPane(jt);
        sp.setBackground(new java.awt.Color(229, 255, 204));


        add(sp,BorderLayout.CENTER);

        JPanel mainPanel=new JPanel(new BorderLayout());
        JPanel panel=new JPanel(new FlowLayout());
        String title = "Buy product";
        Border border = BorderFactory.createTitledBorder(title);
        panel.setBorder(border);

        JPanel pan1=new JPanel(new GridLayout(1,2));
        pan1.add(new JLabel("Product Name: "));
        JTextField tname=new JTextField();
        pan1.add(tname);



        JPanel pan2=new JPanel(new GridLayout(1,2));
        pan2.add(new JLabel("Model Name:  "));
        JTextField tmodel=new JTextField();
        pan2.add(tmodel);


        panel.add(pan1);
        panel.add(pan2);
        JButton button=new JButton("Buy");
        button.setBackground(new java.awt.Color(238,232,170));
        panel.add(button);
        panel.setBackground(new java.awt.Color(229, 255, 204));


        JPanel texpanel=new JPanel(new BorderLayout());
        String title2 = "Details";
        Border border2 = BorderFactory.createTitledBorder(title2);
        texpanel.setBorder(border2);
        JTextArea ta=new JTextArea();
        ta.setEnabled(false);
        Font font = new Font("Segoe Script", Font.BOLD, 20);
        ta.setFont(font);
        ta.setForeground(Color.BLACK);


        JScrollPane js=new JScrollPane(ta);
        texpanel.add(js);
        mainPanel.add(panel,BorderLayout.NORTH);
        mainPanel.add(texpanel);
        add(mainPanel,BorderLayout.EAST);




        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name=tname.getText();
                String mnum=tmodel.getText();


               boolean flag=false;
               try {


                   for (Object temp : v) {

                       if (name.compareToIgnoreCase(((Product) temp).name) == 0 && mnum.compareToIgnoreCase(((Product) temp).ModelNumber) == 0) {

                           os.writeUTF(name);
                           os.writeUTF(mnum);

                           int msg = is.readInt();
                           if (msg == 1) {
                               //buyStack.push((Product) temp);
                               ta.setText("         Product Details"
                                       +"\n\n   Product Name :"+((Product) temp).name
                                +"\n\n   Product Model Number :"+((Product) temp).ModelNumber
                               +"\n\n   Product Company Name :"+((Product) temp).proObj.getProperty("company")
                               +"\n\n   Product Weight :"+((Product) temp).hashObj.get("weight") +" gram"
                               +"\n\n   Product Size :"+((Product) temp).proObj.getProperty("size")
                               +"\n\n    Price :"+((Product) temp).price+" Rupees"
                               +"\n\n   Thanks For Shopping with us.");
                               flag = true;
                                tname.setText("");
                                tmodel.setText("");
                           } else if (msg == 0) {
                               ta.setText("      Product is out of Stock");
                               tname.setText("");
                               tmodel.setText("");
                           }

                           break;
                       }

                   }
                   if (!flag) {
                       ta.setText("      Product not found \n      please Enter correct product!");
                   }
               }catch (Exception i)
               {
                   ta.setText(i.toString());
               }

            }
        });




    }



}
