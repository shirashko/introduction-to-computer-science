import java.util.Scanner;
/**
* class Circle accept two cordinates points (x1,y1), (x2, y2), when the first point represent the upper left corner and the 
* second point represent the bottem right corner of a rectangle that is parallel to the x and y axes. After, it 
* calculates the perimeter and area of the excircle and the incircle of this rectangle and present in on the screen.
* @auther: Shir Rashkovits    
* @version 209144013
*/

public class Circle
{
    public static void main (String[]args)
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.println ("This program calculates the areas " 
            + "and the perimeters of the excircle and the incircle "
            +  "of a given rectangle ");
            
        // getting the values of the upper left corner (x1, y1) 
        System.out.println ("\n" + "Plesse enter the two coordinates of the "
            + "left-upper point of the rectangle");
        int leftUpX = scan.nextInt();
        int leftUpY = scan.nextInt();
        System.out.println("You chose the cordinates (" + leftUpX + "," + leftUpY + ").");
       
        // getting the values of the bottem right corner (x2, y2)
        System.out.println ("Plesse enter the two coordinates of the " 
            + "right-lower point of the rectangle");
        int rightLowX = scan.nextInt();
        int rightLowY = scan.nextInt();
        System.out.println("You chose the cordinates (" + rightLowX + "," + rightLowY + ").");
        
        /* Excirecle calculation */
        // the diagnoal of the rectangle is the diameter of the excircle. using Pythagoras to find the distance
        double diameterExcircle = Math.sqrt(Math.pow(leftUpX - rightLowX,2) + Math.pow(leftUpY - rightLowY,2)); 
        double radiusExcircle = diameterExcircle / 2.0 ;
        double areaExcircle = Math.PI * Math.pow(radiusExcircle,2);
        double perimeterExcircle = 2 * Math.PI * radiusExcircle;
        
        /* Incircle calculation */
        // Assuming the height of the rectangle is smaller than it's width, the height equals the diameter of the incircle
        double diameterIncircle = leftUpY - rightLowY;
        double radiusIncircle = diameterIncircle / 2.0;
        double areaIncircle = Math.PI * Math.pow(radiusIncircle,2);
        double perimeterIncircle = 2 * radiusIncircle * Math.PI;
        
        /* printing the results */
        System.out.println("Incircle: radius = " + radiusIncircle +
        " area = " + areaIncircle + " perimeter = " + perimeterIncircle + "\n" +
        "Excircle: radius = " + radiusExcircle + " area = " + areaExcircle +
        " perimeter = " + perimeterExcircle);
    }  // end of method main
}  //end of class Circle
        
        
        
        
        
    