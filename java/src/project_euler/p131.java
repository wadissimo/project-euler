package project_euler;/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class p131
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int MAX = 1000_000;
		int cnt = 0;
		for(int p = 2; p < MAX; p++){
			boolean prime = true;
			for(int div = 2; div*div <= p; div++){
				if(p%div == 0){
					prime = false;
					break;
				}
			}
			if(prime){
				long r = (long)Math.sqrt(12*p-3);
				if(r*r == 12*p-3 && (r-3)%6 == 0){
					long n = (r-3)/6;
					long m = n+1;
					if(p != (n*n+m*m+n*m))
						throw new RuntimeException("failed");
					cnt++;
					System.out.println("p = "+ p);
					System.out.println("n = "+ n);
					System.out.println("m = "+ m);
					
				}
			}
		}
		System.out.println("ans = "+ cnt);
	}
}
