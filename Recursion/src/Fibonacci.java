/*
 /*
* Data Structures & Algorithms. *
* @version 1.0 15 Sept 2015
* @author Victor Lora
* @description 
*   Recursive implementations of
*   a function to compute Fibonacci numbers.
*/

import java.util.Scanner;

public class Fibonacci 
{
	private static int count = 0;  // Counts the number of recursive
	                               // calls in computing a fibonacci number.
	
	private static int n;          // Which Fibonacci number we're computing
	private static long nth_fib;   // Stores the nth Fibonacci number
	
	private static long[] F = new long[200];  // Stores computed Fibonacci numbers
	                                          // so we don't have to recompute them.
	public static void main(String[] args)
	{
		Scanner keyb = new Scanner(System.in);  // Keyboard scanner.
		
		for (int i = 0; i < 200; i++)  // For fibB algorithm, all fib numbers
			F[i] = -1;                 // are initially not computed.
		
		// Compute a sequence of fibonacci numbers.
		
		// First use the inefficient algorithm with repeated recalculations.
		
		for (int n = 0; n <= 40; n++) {
			
			count = 0;
			nth_fib = fib(n);
			
			System.out.printf("%d   %d  %d\n", n, nth_fib, count);
		}
		
		// Next test the more efficient algorithm, fibB(), using dynamic programming
		// with no recomputations. 
		
		do {
			System.out.println("\nEnter n and I'll compute the nth Fibonocci number.");
			System.out.print("Enter n (-1 to quit): ");
		    n = keyb.nextInt();
			
			if (n != -1) {
				count = 0;
			    nth_fib = fibB(n);
			    System.out.printf("%d   %d   %d\n", n, nth_fib, count);
			}
		} while (n != -1);
	}
	
	/*
	 *     fib()
	 *     
	 *     
	 * 
	 */
	public static long fib(int n)
	{
		if(n<=1) {
			count++;
			return n;
		} else {
			count++;
			return fib(n-1) + fib(n-2);
		}
	}
		
	public static long fibB(int n)
	{
	    if(F[n]==-1){		// checks if fib(n) has been calculated
	    	if(n<=1) {		// for n = 0|1...
				count++;
				F[n] = n;	// Adds F[0]=0 | F[1]=1 to the array
			} else {
				count++;
				F[n] = fibB(n-1) + fibB(n-2);
			}
	    } 
	    return F[n];
	}
}