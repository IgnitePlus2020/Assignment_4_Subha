package com.tgt.igniteplus;
import java.util.Scanner;
/* Problem Statement:
Use case – Check Swiggy delivery to your area.
• Develop a method that accepts a zip code and validates against non-deliverable zip
codes.
• Example – zip codes 123, 456, 789 are non-deliverable.
• If the customer resides at a non-deliverable zip code, throw a custom exception.
• Handle the exception and display an appropriate message to the customer.
• If the customer resides at a deliverable zip code, display a message “Delivery available
in your area! “.
• Invoke this method from public static void main(String[] args) which takes the zip code as
an input parameter.
 */
class NonDeliverableException extends Exception
{
    NonDeliverableException(String s)
    {
        super(s);
    }
}

public class Exception_Handling {
    static void checkDelivery(int zipcode) throws NonDeliverableException
    {   int flag=0;
        int[] nonDeliverableCodes={123,456,789};
        for(int i: nonDeliverableCodes)
        {
            if(i==zipcode) {
                flag=1;
                throw new NonDeliverableException("Sorry, Delivery not available in your area!");

            }
        }
        if(flag==0)
            System.out.println("Delivery available in your area!");

    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter the zip code of your area:");
        int zip=in.nextInt();
        try{
            checkDelivery(zip);
        }
        catch(NonDeliverableException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
/*
OUTPUT 1:
Enter the zip code of your area:
234
Delivery available in your area!

OUTPUT 2:
Enter the zip code of your area:
123
Sorry, Delivery not available in your area!

 */
