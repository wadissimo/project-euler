package project_euler;

public class p9 {
    public static void main(String[] args) {
        for (int a = 1; a <= 1000; a++) {
            for (int b = 1; b <= 1000; b++) {
                if(a + b < 1000){
                    int c = 1000-a-b;
                    if(a*a + b*b == c*c){
                        System.out.println("a = " + a);
                        System.out.println("b = " + b);
                        System.out.println("c = " + c);
                        System.out.println("(a*b*c) = " + (a * b * c));
                    }
                }
            }
        }
    }
}
