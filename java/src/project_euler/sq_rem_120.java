package project_euler;

public class sq_rem_120 {
    public static void main(String[] args) {
        long sum = 0;
        long testSum = 0;
        for (int a = 3; a <= 1000 ; a++) {
        //int a = 13;

            int max_rem = 2*a;
            for (int n = 1; n <= 2*a; n+=2) {
                int rem = 2 * n % a * a;
//                System.out.println("1 n = " + n);
//                System.out.println("1 rem = " + rem);
                max_rem = Math.max(max_rem, rem);
            }
            int aa = a*a;
            long a1 = 1, a2 = 1;
            long test = 0;
            for (int n = 1; n < 20*a ; n++) {
                a1= a1*(a-1)%aa;a2=a2*(a+1)%aa;
                long rem = (a1 + a2) % aa;
                test = Math.max(test, rem);
//                System.out.println("2 n = " + n);
//                System.out.println("2 rem = " + rem);
            }
            testSum += test;
//            System.out.print("a = " + a);
//            System.out.print(", max_rem = " + max_rem);
//            System.out.println(", test = " + test);
            sum += max_rem;

        }
        System.out.println("testSum = " + testSum);
        System.out.println("sum = " + sum);
    }
}
