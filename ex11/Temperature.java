import java.util.Scanner;
/**
* class Temperature except degree and a unit of mesurement and calculate 
* and present the degree in all common format: Celsius, Fahrenheit and Kelvin
* @auther Shir Rashkovits    
* @version 209144013
**/

public class Temperature{
    public static void main(String[]args){
        
        Scanner scan = new Scanner(System.in);
        System.out.println("This program calculate degrees in all common format: Celsius, Fahrenheit and Kelvin");
        System.out.println("Please enter the tempature degree and the type of unit mesurement you want to transform into the"
        + " other common format" + "\n" + "(C for Celsius, F for Fahrenheit and K for Kelvin).");
        
        /* getting the wanted temperature and the unit of mesurement from the user*/
        String word = scan.next();
        char letter = word.charAt(0);
        double temperature = scan.nextDouble();
        
        /* Numbers that are needed for transfer from one unit of mesurement to an other*/
        final double RATIO_FROM_CELSIUS_AND_KELVIN_TO_FAHRENHEIT = 9.0/5;
        final double RATIO_FROM_FAHRENHEIT_TO_CELSIUS_AND_KELVIN = 5.0/9;
        final double CONSTANT_FROM_FAHRENHEIT_TO_CELSIUS_AND_KELVIN = 32;
        final double CONSTANT_FROM_KELVIN_TO_FAHRENHEIT_AND_CELSIUS = 273.15;
        final double ADDITION_FROM_CELSIUS_AND_FAHRENHEIT_TO_KELVIN = 273.15;
        final double ADDITION_FROM_CELSIUS_AND_KELVIN_TO_FAHRENHEIT = 32;
        /* initialize the temperature in each unit of mesurement*/
        double cTemp, fTemp, kTemp;
        cTemp = 0;
        fTemp = 0;
        kTemp = 0;
           
      /* Converting the user chosen temperture into other units of mesurement by formula of converting from C to F and K uinits*/  
        if (letter == 'C')
        {
            cTemp = temperature;
            fTemp = RATIO_FROM_CELSIUS_AND_KELVIN_TO_FAHRENHEIT * temperature + ADDITION_FROM_CELSIUS_AND_KELVIN_TO_FAHRENHEIT;
            kTemp = temperature + ADDITION_FROM_CELSIUS_AND_FAHRENHEIT_TO_KELVIN; 
            /* Converting the user chosen temperture into other units of mesurement by formula of converting from F to C and K uinits*/
        }
        else if (letter == 'F')
        {
            cTemp = RATIO_FROM_FAHRENHEIT_TO_CELSIUS_AND_KELVIN * (temperature - CONSTANT_FROM_FAHRENHEIT_TO_CELSIUS_AND_KELVIN);
            fTemp = temperature;
            kTemp = RATIO_FROM_FAHRENHEIT_TO_CELSIUS_AND_KELVIN * (temperature - CONSTANT_FROM_FAHRENHEIT_TO_CELSIUS_AND_KELVIN) + ADDITION_FROM_CELSIUS_AND_FAHRENHEIT_TO_KELVIN; 
        }
        /* Converting the user chosen temperture into other units of mesurement by formula of converting from K to C and F uinits*/
            else if (letter == 'K')
            {
                cTemp = temperature - CONSTANT_FROM_KELVIN_TO_FAHRENHEIT_AND_CELSIUS;
                fTemp = RATIO_FROM_CELSIUS_AND_KELVIN_TO_FAHRENHEIT * (temperature - CONSTANT_FROM_KELVIN_TO_FAHRENHEIT_AND_CELSIUS) + ADDITION_FROM_CELSIUS_AND_KELVIN_TO_FAHRENHEIT ;
                kTemp = temperature; 
            }
                
        // printing the outcomes to the user
        System.out.println(cTemp + " C" + "\n" + fTemp + " F" + "\n" + kTemp + " K");      
        
    } // end of method main
} //end of class Temperature
        