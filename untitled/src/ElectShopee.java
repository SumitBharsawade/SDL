import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;


class ElectShopee {

    Vector v=new Vector();
    Scanner sc=new Scanner(System.in);

    Stack<Product> buyStack = new Stack<Product>();
    Stack<Product> tempStack = new Stack<Product>();


    public void AddProduct()
    {

        System.out.print("\nEnter Product Name :");
        String n=sc.next();

        System.out.print("\nEnter Model Number :");
        String num=sc.next();

        System.out.print("\nEnter Product Price :");
        Double pr=sc.nextDouble();

        System.out.print("\nEnter Product Quantity :");
        int quantity=sc.nextInt();


        Product p=new Product(n,num,pr,quantity);
        p.setProperties();

        p.setBproperties();


        v.add(p);
    }

    public void getProducts()
    {
        System.out.println("**************Avilable Products***********");
        for(Object temp:v)
        {
            temp=(Product)temp;

            System.out.println( ((Product) temp).getName());

        }
        System.out.println("\n---------------------------------------");
    }

    public void getProductWithInfo()
    {


        System.out.println("**************Avilable Products Information***********");
        for(Object temp:v)
        {
            temp=(Product)temp;

            ((Product) temp).ShowProductInfo();

        }
    }


    public void Buy()
    {

        System.out.println("**************Buying Product***********");
        System.out.print("\nEnter Product Name  :");
        String name=sc.next();

        System.out.print("\nEnter Model Number :");
        String num=sc.next();

        for(Object temp:v)
        {
            temp=(Product)temp;

            if(name.compareToIgnoreCase(((Product) temp).getName())==0&&num.compareToIgnoreCase(((Product) temp).getMnumber())==0)
            {
                if(!(((Product) temp).getQuantity()>0))
                {
                    System.out.println("\nCurrently Product not Avilable:");


                }


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
                tempStack.push(p);
                p.ShowProductInfo();

                p.decQuantity();
                totalPrice+=p.getPrice();

            }

        }
        catch(EmptyStackException e)
        {

        }

        System.out.println("\n Total Price :"+totalPrice);

        System.out.print("\n Conform to Buying (yes or no):");


        System.out.println("\nThank you........");

    }



}

