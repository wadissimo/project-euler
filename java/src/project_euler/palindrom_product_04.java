package project_euler;

public class palindrom_product_04 {
    public static void main(String[] args) {
        int max = 0;
        for (int n1 = 100; n1 < 1000; n1++) {
            for (int n2 = 100; n2 < 1000; n2++) {
                String s = Integer.toString(n1*n2);
                boolean palindrom = true;
                for (int i = 0; i < s.length() / 2; i++) {
                    if(s.charAt(i) != s.charAt(s.length()-1-i)){
                        palindrom = false;
                        break;
                    }
                }
                if(palindrom)
                    max = Math.max(max, n1*n2);
            }
        }
        System.out.println("max = " + max);
    }
}
