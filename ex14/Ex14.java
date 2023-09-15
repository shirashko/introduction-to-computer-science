/**
 * Maman 14 functions
 * @author Shir Rashkovits
 * @209144013
 */
public class Ex14
{
    /** first 
     * memory complexity- O(1) - the number of variables does not depend on the the size of the array.
     * time complexity- O(log n) - the function divides the array to two parts until a single number is found. in the worst case the division 
     * 
     * by two coninues until only one value in the subarray remmains, returned as the single value. 
     * getting an array with integer numbers, containing couples of identical values located nearby, 
     * with one randomly placed single value. The function returns the single value when found.
     * @param a - an array with an odd length, with couples of nearby identical values, and one randomly placed single value
     * @return - the single randomly placed value
     */ 
    public static int findSingle (int [] a)
    {
        int start = 0;
        int end = a.length-1;
        int middle = start+end/2;
        while (start <= end)
        {
            middle = (start+end)/2;
            if(start == end) //the size of the subarray is one
                return a[middle];
            // if the middle is an even number- the middle should be equal to middle+1, if the left side does'nt include the single value
            // if the middle is an odd number- the middle should be equal to middle-1, if the left side doesn't include the single value
            if (middle % 2 == 0)  //middle is an even index
            {
                if (a[middle] == a[middle+1]) 
                start = middle+2; //the right side contains the single number
                else if (a[middle] == a[middle-1]) 
                    end = middle-2; //the left side contains the single number
                else //middle is the single number
                    return a[middle];
            }
            else //middle is an odd index
            { 
                if (a[middle] == a[middle-1]) //the right side contains the single number
                        start = middle+1;
                    else if (a[middle] == a[middle+1]) //the left side contain the single number
                        end = middle-1;
                            else //the middle is the single number
                                return a[middle];
            }
        }
        return -1; //in case the input isn't standing in the conditions of the questions
    }
    
    /** second
    * memory complexity: O(1) - the number of variables does not depend on the the size of the array.
    * time complexity: O(n). eventually, the function moves on the array from the start until the end twice- one time with the right variable
    * and the second time with the left variable. we start with a subsarray that contains only the first number in the array. then, if the sum
    * of the current subarray is not bigger than x, we enlarge the subarray with the right index of the array. Else, if the sum of the
    * current subarray values is bigger than x, the subarray lessens the left index of the array. 
    * that is why the while loop iterates as many times as the array length (n) * 2. 
    * When a subarray of length 1 with value is bigger than x is found, the function immediately returns 1, since no smaller subarray can be found. 
    * Therefore, in this case no need to continue the scan the array.
    * 
    * getting an array of integers and an int number, and returning the minimum number of the adjacent cells that reach to a bigger sum than the given number.
    * if there isn't any fitting number of adjacent cells that is bigger than the given number, or if the array is empty, the function returns -1
    * @param arr - an array with natural numbers
    * @param x- natural number that the function needs to find for the smallest sum of numbers in the given array that is bigger than it
    * @return - the minimum length of adjacent cells that reach to a bigger sum than the given param x
    */
    public static int smallestSubSum(int arr[], int x) //{1,4,6} x = 200 sum = 
    {
        if (arr == null)
            return -1;
        int sum = 0; // sum of the current subarray
        int min = arr.length+1; //describes the smallest subarray that we found that it's sum is bigger than x. setting it into impossible value at first.
        int left = 0; //describes the subarray first index
        int right = 0; //describes the subarray last index
        while (right <= arr.length) 
        {
            if (sum > x){
                if (right-left < min) //cheking if the subarray is smaller than the minimum lengthh subarray we have already found
                    min = right-left; // set new minimum 
                    if (min == 1) // minimum possible subarray length
                        return 1; // no need to continue scanning
                sum -= arr[left]; 
                left++; //subarray for the next iteration is left without the left index
            }
            else //if the sum is smaller or equal to x
            {
                if (right >= arr.length) //exception of boundary
                    break; 
                sum += arr[right]; // update current subraay sum
                right++; //continue to check subarray, this time with the addition of the right index
            }
        }
        if (min == arr.length+1) //in case the array there isn't any subarray that is bigger than x
            return -1;  
        return min; 
    } 
    
    /**
    * three
    * getting a natural number and returning the number of equations of three numbers in the range of 1-10 (including) summing up to that number 
    * in addition, the function prints the equations. If the given number is bigger than 30 or smaller than 3, the function returns 0
    * @param num - the number to calculate for the number of results
    * @return - the number of equations
    */
    public static int solutions(int num)
    {
      if (num > 30 || num < 3)
            return 0;
      return solutions(num,1,1,1);
    }
    //one for the first number (x1), two for the second number (x2), and three for the three and last number (x3)
    //the function checks all the combintions, and when it finds the suitable one it prints it and adss 1 to 
    // the number of solutions found
    private static int solutions(int num, int x1, int x2, int x3)
    {
        if (x1 > 10 || x2 >10 || x3 > 10) //end of searching
            return 0;
        int foundSolution = 0; //indicates if we found a suitable result, and adding it to the number of results to be returned from the method 
        if(x1 + x2 + x3 == num) 
        {
            System.out.println(x1 + " + " + x2 + " + " + x3);
            foundSolution = 1;
        }
        if (x2 == 10 && x3 == 10)
            return foundSolution + solutions(num, x1+1, 1, 1); //increment x1
          else
            if(x3 == 10)
                return foundSolution + solutions(num, x1, x2+1, 1); //increment x2
            else 
            {
                //avoid checking un-necessery equations
                if (foundSolution == 1 && x2 < 10)
                    return foundSolution + solutions(num, x1, x2+1, 1); //increment x2
                else if (foundSolution == 1 && x2 == 10)
                    return foundSolution + solutions(num, x1+1, 1, 1); //increment x1
                else
                    return foundSolution + solutions(num, x1, x2, x3+1); //increment x3
            }   
    }
    
    /** four
     * getting two dimensional array with boolean values. reutrn the number of true area in the array.
     * we define a true area as a group of adjacent true cells that are surrounded by false cells from all sides
     * @param mat - two dimensional array with boolean values. 
     * @return - the number of true area in the array
     */
    public static int cntTrueReg (boolean[][]mat)
    {
        if (mat == null)
            return 0;
        return cntTrueReg(mat, 0,0);
    }

    //mooving on the array line by line using recursion starting at the upper left cell and ending at the bottom right cell. On the way, searching for true areas. 
    // If a true cell is found, setAreaToZero() function is invoked to it and it's true neighbors of this area to false, if exists, in order to avoid repeatition
    // of true areas. Then, we increment the number of true areas found and recurse to the next cell searching for more true area.
    private static int cntTrueReg (boolean[][]mat, int row, int column)
    {
        if (row >= mat.length || column >= mat[0].length || row < 0 || column < 0) //invalid indexes 
            return 0;
        int foundTrueArea = 0; //indicating that true area not found yet 
        if (mat[row][column])
        {
            setAreaToZero(mat, row, column); // set false to all cells in this area
            foundTrueArea = 1; // indicate true area was found
        }
        if (column >= mat[0].length-1) // end of the row
            return foundTrueArea + cntTrueReg(mat, row+1, 0);
        else 
            return foundTrueArea + cntTrueReg(mat, row, column+1);   
    }
    
    //the function helps us to avoid counting the same true area more than once, and to differentiate the linear search in the array
    //from the search that happens when a true area is found and we want to find all his true neighbors(and there's neighbors) and to set them to false  
    private static void setAreaToZero(boolean[][] mat, int row, int column)
    {
        if (column >= mat[0].length || column < 0 || row >= mat.length || row < 0) //illegal indexes
            return;
        if (mat[row][column])
        {
            mat[row][column] = false; //turning true cell into false to aviod repeatition and infinte recursion
            //checking all directions 
            setAreaToZero(mat, row+1, column);
            setAreaToZero(mat, row-1, column);
            setAreaToZero(mat, row, column+1);
            setAreaToZero(mat, row, column-1);
        }
    }
}