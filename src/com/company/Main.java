package com.company;


import java.util.Scanner;

public class Main {

    public static void main(String[] args)throws Exception {

        ElectShopee E1=new ElectShopee();
        Scanner cin=new Scanner(System.in);


        while(true)
        {
            System.out.println("\n-------------------------------------");
            System.out.println("--->>Enter option as follows :");

            System.out.print("1.Add Product\n2.Show Product\n3.Show product Info\n4.Buy\n5.UnavilableProducts\n6.On Server\n7.exit\n--->>>");
            int ch=cin.nextInt();

            System.out.println("\n-------------------------------------");

            switch(ch)
            {

                case 1:
                    E1.AddProduct();
                    break;

                case 2:
                    E1.getProducts();

                    break;

                case 3:
                    E1.getProductWithInfo();

                    break;

                case 4:
                    while(true)
                    {

                        E1.Buy();

                        System.out.print("\nWant to Buy Another (yes or no) :");
                        String choice=cin.next();

                        if(choice.compareToIgnoreCase("no")==0)
                        {
                            E1.buy1();

                            break;
                        }

                    }
                    break;
                    
                    case 5:
                    
                    E1.unProduct();
                    break;
                    
                case 6:
                    E1.ServerOn();
                    break;

                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.print("\nChoice Not Found \n     try again.....");

            }

        }
    }
}

