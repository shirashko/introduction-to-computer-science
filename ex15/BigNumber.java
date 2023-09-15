/**
 * class BigNumber represents a number by linked IntNode objects that point at each other in one direction, when the first digit (the _head) is the right digit
 * of the number, and the last IntNode in the list is the left digit in the number (left -123 - right).
 * the class enables representing numbers bigger than the range of long.
 * @author Shir Rashkovits
 * ID: 209144013
 */
public class BigNumber
{
    private IntNode _head; // the head points to the right digit

    //isZero checks if the number stored in BigNumber object is zero
    private boolean isZero()
    {
        return _head.getValue() == 0 && _head.getNext() == null;
    }
    
    /**
    * time complexity: O(1)- fixed
    * memory complexity: O(1)- fixed- always one variable
    * 
    * constructs a BigNumber object that represents the number zero
    */
    public BigNumber()
    {
        _head = new IntNode(0);
    }
    
    /**
     * time complexity: O(n)- depends on the number of the digits in the given parameter (n)
    * memory complexity: O(n)- creating n IntNode objects, depends on the number of digits in the given parameter(n)
    * 
    * construct a BigNumber that will represent the given parameter
    * @param num - long number that will be represented as a BigNumber object
    */
    public BigNumber(long num)
    {
        _head = new IntNode((int)(num%10));
        IntNode current = _head;
        num = num/10;
        while (num >=1) //after dividing num n times by 10 num becomes zero
        {
            IntNode digit = new IntNode((int)(num%10)); //initializing the new node to the next digit from the right 
            current.setNext(digit); //current node will point on the new node
            current = current.getNext();
            num = num/10; //continue without the rightest digit
        }
    }

    /**
     * time complexity: O(n)- depends on the number of digits (n) in the number that other parameter represents
     * memory complexity: O(n)- creating n IntNode objects, depends on the number of digits(n) of the given parameter (the number it represents)
     * 
     * copy constructor for BigNumber
     * construct a BigNumber object that represent the same number as other BigNumber represents
     * @param other - the BigNumber object from which to construt the new BigNumber
     */
    public BigNumber(BigNumber other)
    {
        _head = new IntNode(other._head.getValue());
        IntNode thisCurrent = _head;
        IntNode otherCurrent = other._head.getNext();
        while (otherCurrent != null) 
        {
            IntNode nextNode = new IntNode(otherCurrent.getValue()); //initializing the new node to the next digit from the right
            thisCurrent.setNext(nextNode); //current node will point on the new node
            otherCurrent = otherCurrent.getNext();
            thisCurrent = thisCurrent.getNext();
        }
    }
    
    /**
    * time complexity: O(n)- depends on the number of digits(n) in the number that this BigNumber object represents
    * memory complexity: O(n)- creating a new String object for Each digit/s from the right to the left using recursion. the return String  contains all digits
    * 
    * the method returns a String that represents the BigNumber object as a number
    * @return - the number that BigNumber represents as a String
    */
    public String toString()
    {
        return toString(_head);
    }

    //getting the right digit of the number that this object represents and recurse until we are in the leftiest digit. then, returning the digits as a string
    //in the desired order - the leftiest digit until the rightest digit
    private String toString(IntNode current) 
    {
        if (current == null)
            return "";
        return toString(current.getNext()) + "" + current.getValue();
    }
    
    /**
    * time complexity: O(n)- depends on the number of digits of the smaller number
    * memory complexity: O(1)- fixed number of variables are being used 
    * 
    * getting a number represnted as a BigNumber object, and comparing it's value to the value of this number(also represented as BigNumber object). 
    * the method retuens -1 if this number is smaller than other number, 1 if this number is bigger than other, and if the both numbers are equals - returns 0.
    * @param - the other number this will be compared with 
    * @return - 1 if this number is bigger than other, -1 if this number is smaller than other, and 0 if both equal
    */
    public int compareTo (BigNumber other) 
    {
       IntNode thisCurrent = _head;
       IntNode otherCurrent = other._head;
       int returnValue = 0; //indicates which number is bigger in case both numbers have the same number of digits (assuming equals at default)
       while (thisCurrent != null && otherCurrent != null) 
       {
           if (thisCurrent.getNext() != null && otherCurrent.getNext() == null) //this is bigger
                return 1; 
           if (thisCurrent.getNext() == null && otherCurrent.getNext() != null) //this is smaller
                return -1; 
           if(thisCurrent.getValue() > otherCurrent.getValue()) //check which digit is bigger in case both numbers have the same size
                returnValue = 1;
           else if (thisCurrent.getValue() < otherCurrent.getValue()) //check which digit is bigger in case both numbers have the same size
                returnValue = -1;
           if (thisCurrent.getNext() == null && otherCurrent.getNext() == null)//the numbers have the same size
                return returnValue; 
           //getting to the start of the number
           thisCurrent = thisCurrent.getNext();
           otherCurrent = otherCurrent.getNext();
       }
       return Integer.MIN_VALUE; //will never reach here
    }  
    
    /**
    * time complexity: O(n)- depends on the number of digits of the bigger number(n)
    * memory complexity: O(n)- depends on the number of digits of the bigger number(n)- creating new BigNumber, with n/n+1 IntNode
    * 
    * calculate the sum of this number and a given number (both numbers being represented as a BigNumber objects), and return the result as a BigNumber object.
    * @param - other, a number being repersented as a BigNumber object
    * @return - the sum of this and other as a BigNumber object
    */
    public BigNumber addBigNumber (BigNumber other)
    {
        IntNode thisCurrent = _head;
        IntNode otherCurrent = other._head;
        int carry; //carry =1 indicates if previous add overflow ten  
        BigNumber sumResult = new BigNumber((_head.getValue() + other._head.getValue()) % 10);
        carry = (_head.getValue() + other._head.getValue()) / 10; //or 0 or 1
        IntNode sumCurrentDigit = sumResult._head;
        while (thisCurrent.getNext() != null && otherCurrent.getNext() != null) //didn't reach to the end of any number
        {
            int value = thisCurrent.getNext().getValue() + otherCurrent.getNext().getValue() + carry; 
            IntNode sumNextDigit = new IntNode(value % 10); //taking the rightest digit from the current addition 
            carry = value / 10; //set to 1 when current addition is bigger than ten
            sumCurrentDigit.setNext(sumNextDigit);
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
            sumCurrentDigit = sumCurrentDigit.getNext();
        }   
        IntNode remainCurrent;
        if (otherCurrent.getNext() == null) //if other is not longer than this
            remainCurrent = thisCurrent;
        else 
            remainCurrent = otherCurrent;
        while (remainCurrent.getNext() != null) //continue with the bigger number
        {
                int value = remainCurrent.getNext().getValue() + carry; 
                IntNode sumNextDigit = new IntNode(value % 10); //taking the rightest digit from the current addition
                carry = value / 10; //set to 1 when current addition is bigger than ten
                sumCurrentDigit.setNext(sumNextDigit);
                remainCurrent = remainCurrent.getNext();
                sumCurrentDigit = sumCurrentDigit.getNext();
        }
        if (carry == 1) //we have one more overflow digit to add
        {
            IntNode sumNextDigit = new IntNode(carry);
            sumCurrentDigit.setNext(sumNextDigit);
        }  
        return sumResult;
    }
    
    /**
    * time complexity: O(n)- creating a new BigNumber object with n (the number of digits of the given paramter) iterations. Then, using the addBigNumber function that 
    * it's efficienty depends on the number of digits of the bigger number(n, assuming the order of BigNumber object and given paramter is the same)
    * memory complexity: O(n)- depends on the number of digits of the bigger number(n)- creating new BigNumber, with n/n+1 IntNode
    * 
    * getting a long number, creating a BigNumber object that represents it, and calculating the sum of this and other by the addBigNumber method.
    * @param - other, represents the number to be added to this number
    * @return - the sum of this and other as a BigNumber object
    */
    public BigNumber addLong (long num)
    {
        BigNumber longAsBN = new BigNumber(num); //representing the given long parameter into BN - BigNumber object 
        return addBigNumber(longAsBN); 
    } 
    
    /**
    * time complexity: O(n)- depends on the number of digits of the bigger number
    * memory complexity: O(n)- depends on the number of digits of the bigger number(n)- creating new BigNumber, with n IntNode
    * 
    * getting a BigNumber and checking if it is bigger than this number(both being representd s a BigNumber objects). then, subtract the smaller number from
    * the bigger, and return the calculation result as a BigNumber object.
    * @param - other, represents the given number to be checked and use for the reduction calculation
    * @return - the reduction calculation result, as a list representd as BigNumber object
    */
    public BigNumber subtractBigNumber (BigNumber other)
    {
        IntNode biggerCurrent; //moves on the nodes of the bigger number
        IntNode smallerCurrent; //moves on the nodes of the smaller/ equal number
        if(compareTo(other) == 0)
            return new BigNumber(); //return an object which represents the number zero, which is the reduction result of two equal numbers
        if (compareTo(other) == 1)//this is bigger
        {
            biggerCurrent = _head; 
            smallerCurrent = other._head;
        }
        else //this is smaller than other
        {
            biggerCurrent = other._head; 
            smallerCurrent = _head;
        }
        int carry = 0; // -1 if we borrow 10 from the next digit
        int currentResult = biggerCurrent.getValue() - smallerCurrent.getValue(); //the subtraction result
        if (currentResult < 0) //check if we need to borrow 10 from the next digit
        {
            currentResult += 10; //"taking" 10 from the next IntNode
            carry = -1; //we borrowed 10 thus set carry to -1
        }
        BigNumber diffResult = new BigNumber(currentResult); 
        IntNode diffCurrentDigit =  diffResult._head;
        IntNode zeroToDelete = null; //avoiding zeros at the left (000123 - 123)
        while (smallerCurrent.getNext() != null)
        {
            currentResult = biggerCurrent.getNext().getValue() - smallerCurrent.getNext().getValue() + carry;
            if (currentResult < 0) //check if we need to borrow 10 from the next digit
            {
                currentResult += 10; //"taking" 10 from the next IntNode
                carry = -1; //we borrowed 10 thus set carry to -1
            }
            else
                carry = 0; //we did not borrow 10 from next digit
            
            IntNode diffNextDigit = new IntNode(currentResult);
            diffCurrentDigit.setNext(diffNextDigit);

            if (diffNextDigit.getValue() == 0) // diffNextDigit may be the start of zero sequence at the left 
            {
                if (zeroToDelete == null) //if diffNextDigit if the first zero on the sequence
                    zeroToDelete =  diffCurrentDigit; //keep diffCurrentDigit so we can set it's _next to null if it turns out that diffNextDigit needs to be ignored
            }
            else // no zero sequence is starting
                zeroToDelete = null; 
            
             diffCurrentDigit =  diffCurrentDigit.getNext();
            biggerCurrent = biggerCurrent.getNext();
            smallerCurrent = smallerCurrent.getNext();
        }
        if (biggerCurrent.getNext() != null) // biggerNumber is bigger than smallerNumber, hence no zero sequence
            zeroToDelete = null; 
        while (biggerCurrent.getNext() != null) // need to continue calculation with the rest of biggerNumber digits
        {
            currentResult = biggerCurrent.getNext().getValue() + carry; 
            if (currentResult < 0) //check if we need to borrow 10 from the next digit
            {
                currentResult += 10; //"taking" 10 from the next IntNode
                carry = -1; //we borrowed 10 thus set carry to -1
            }
            else
                carry = 0; // //we did not borrowed 10 from next digit
            IntNode diffNextDigit = new IntNode(currentResult);
            diffCurrentDigit.setNext(diffNextDigit);
            diffCurrentDigit =  diffCurrentDigit.getNext();
            biggerCurrent = biggerCurrent.getNext();
        }
        if (zeroToDelete != null) //we have unnecessary zero's at the left that we want to ignore 
            zeroToDelete.setNext(null); //setting the last number that it's value was different than zero to point on null
        return  diffResult;
    }
    
    
    /**
    * time complexity: O(n^2) - assuming both numbers length's are in order (n). The method multiplies every digit of one BigNumber with all the digits of the other 
    * BigNumber. Move on all the digits of this number as many times as the number of digits in other. That is why the efficiency depends on the number of digits of 
    * this BigNumber multiply by the number of digits of the other BigNumber
    * memory complexity: O(n) - n is the number of digits of the bigger number. at the worst case, we will create 2n new IntNodes.
    * 
    * getting a BigNumber object and calculate the multiplication result of this number and the given number, and return the multuplication result
    * as a BigNumber object 
    * and the number this object represents and return the result as BigNumber object
    * @param - other is a BigNumber object that represents a number, that will be multiplied by this number(which being represents as a BigNumber object)
    * @return - the multuplication result as a BigNumber result 
    */
    public BigNumber multBigNumber (BigNumber other)

    {
        if(isZero() || other.isZero()) //if this or other are zero (or both), the result is 0
            return new BigNumber();
            
        IntNode otherCurrent = other._head;
        IntNode thisCurrent = _head;
        BigNumber sum = new BigNumber(); // for summing all the lists. the first one is 0, and it adds up all the multplication results
        int counter = 0; //indicates how many zero nodes we need to add at the right of the current result of the iteration
        int i;
        int carry = 0; // indicates how much to add to the next calculation
        BigNumber multiplyResult = null; //at the end of every iteration multiplyResult represents the multuplication result of the current other digit 
        //by all this digits(inclusing zeros if necessary
        IntNode currentNewDigit; //in every iteration, inside the loop, currentNewDigit represent the current digit that we add to the result of this iteration 
        while (otherCurrent != null)
        {
            if (counter != 0) //we need to add as many zero to th end of the result as we progress with the other digits
            {
                multiplyResult = new BigNumber(); // set _head to zero
                currentNewDigit = multiplyResult._head; //_head value is 0
                for (i=1; i < counter; i++) //starting from 1 because the constructor initialize the first number to be zero already
                {
                    IntNode nextNewDigit = new IntNode(0); // the next digit
                    currentNewDigit.setNext(nextNewDigit);
                    currentNewDigit = currentNewDigit.getNext();
                }
                IntNode nextNewDigit = new IntNode((thisCurrent.getValue() * otherCurrent.getValue() + carry) % 10); 
                carry = (thisCurrent.getValue() * otherCurrent.getValue() + carry) / 10 ; // if overflow carry becomes > 0
                currentNewDigit.setNext(nextNewDigit);
                currentNewDigit = currentNewDigit.getNext();
            }
            else //in the first digit (when counter == 0) we don't need to add zero to the and of the result like in the other digits
            {
                multiplyResult = new BigNumber((thisCurrent.getValue() * otherCurrent.getValue() + carry) % 10);
                currentNewDigit = multiplyResult._head;
            }
            carry = (thisCurrent.getValue() * otherCurrent.getValue() + carry) / 10; 
            while (thisCurrent.getNext() != null) // continue until last digit of this 
            {
                int currentResult = thisCurrent.getNext().getValue() * otherCurrent.getValue() + carry;
                IntNode nextNewDigit = new IntNode(currentResult % 10); 
                carry = currentResult / 10 ; 
                currentNewDigit.setNext(nextNewDigit);
                thisCurrent = thisCurrent.getNext();
                currentNewDigit = currentNewDigit.getNext();
            }
            if (carry != 0) // at the end we still have a carry
            {
                IntNode nextNewDigit = new IntNode(carry); // create a node to hold the last carry
                currentNewDigit.setNext(nextNewDigit);
            }
            //we finished the multiplication of this digit of other with all the digits of this
            sum = sum.addBigNumber(multiplyResult); //summing the numbers we found
            counter++; //need to add one more zero to the end(to the the right) of the next multiplication result
            otherCurrent = otherCurrent.getNext();
            carry = 0;
            thisCurrent = _head;
        }
        return sum;
    }
}
