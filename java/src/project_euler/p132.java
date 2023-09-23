package project_euler;/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.BigInteger;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int MAX = 1000_000;
		
		int cnt = 40;
		long ans = 0;
		BigInteger K = BigInteger.valueOf(1000_000_000);
		for(int p = 2; p < MAX && cnt > 0; p++){
			boolean prime = true;
			for(int div = 2; div*div <= p; div++){
				if(p%div == 0){
					prime = false;
					break;
				}
			}
			if(prime){
				BigInteger pow = BigInteger.valueOf(10).modPow(K, BigInteger.valueOf(p*9));
				if(pow.equals(BigInteger.ONE)){
					cnt--;
					ans += p;
				}
			}
		}
		System.out.println("cnt = "+ cnt);
		System.out.println("ans = "+ ans);
	}
}
