import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ElectShopee E1=new ElectShopee();
        Scanner cin=new Scanner(System.in);


        while(true)
        {
            System.out.println("\n-------------------------------------");
            System.out.println("--->>Enter option as follows :");

            System.out.print("1.Add Product\n2.Show Product\n3.Show product Info\n4.Buy\n5.exit\n--->>>");
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

                    System.exit(0);

            }

        }
    }
}

