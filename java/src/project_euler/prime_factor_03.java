package project_euler;

public class prime_factor_03 {
    public static void main(String[] args) {
        long num = 600851475143L;
        for (int div = 2; div*div <= num; div++) {
            if(num%div == 0){
                System.out.println("div = " + div);
                while(num%div == 0)
                    num/=div;
            }
        }
        System.out.println("num = " + num);
    }
}
