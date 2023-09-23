package project_euler;

public class even_fi_2 {
    public static void main(String[] args) {
        long sum = 0;
        int a = 1;
        int b = 1;
        for (int i = 0; i < 1000; i++) {
            int next = a+b;
            if(next > 4_000_000)
                break;
            if(next%2 == 0){
                sum+= next;
                System.out.println("next = " + next);
            }
            b = a;
            a = next;

        }
        System.out.println("(a+b) = " + (a + b));
        System.out.println("sum = " + sum);
    }
}
