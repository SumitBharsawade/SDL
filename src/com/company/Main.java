package com.company;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ElectShopee E1=new ElectShopee();
        Scanner cin=new Scanner(System.in);
        ConnectingThread obj=new ConnectingThread(E1);
        obj.start();
        System.out.println(" Server  Started ");
        while(true)
        {
            System.out.println("\n-------------------------------------");
            System.out.println("--->>Enter option as follows :");

            System.out.print("1.Add Product\n2.Show Product\n3.Show product Info\n4.UnavilableProducts\n5.exit\n--->>>");
            int ch=cin.nextInt();

            System.out.println("\n-------------------------------------");

            switch(ch)
            {

                case 1:
                    E1.AddProduct1();
                    break;

                case 2:
                    E1.getProducts1();

                    break;

                case 3:
                    E1.getProductWithInfo1();

                    break;

                    
                    case 4:
                    
                    E1.unProduct1();
                    break;
                    
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.print("\nChoice Not Found \n     try again.....");

            }

        }
    }
}

