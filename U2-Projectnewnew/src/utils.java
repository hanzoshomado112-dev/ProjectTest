import java.util.Scanner;

public class utils
{
    public static void printEllipses(int dots, int delay)
    {
        try
        {
            Thread.sleep(300);
        }
        catch(InterruptedException e)
        {
            System.out.println("failed!");
        }
        for (int i = 0; i < dots; i++)
        {
            try
            {
                System.out.print(".");
                Thread.sleep(delay);
            }
            catch(InterruptedException exception)
            {
                System.out.println("Ellipses animation failed! (This shouldn't happen)");
            }
        }
        System.out.println();
    }

    public static void delay(int delay)
    {
        try
        {
            Thread.sleep(300);
        }
        catch(InterruptedException e)
        {
            System.out.println("failed!");
        }
        System.out.println();
    }

    public static int randomInt(int min, int max)
    {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public static double randomDouble(double min, double max)
    {
        return Math.random() * (max - min) + min;
    }


    public static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // discard invalid input
        }
        return scanner.nextInt();
    }
}
