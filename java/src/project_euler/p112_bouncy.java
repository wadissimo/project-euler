package project_euler;

public class p112_bouncy {
    public static void main(String[] args) {
        int MAX = 10000_000;
        int cnt = 0;
        for (int num = 100; num <= MAX; num++) {
            String s = Integer.toString(num);
            boolean bouncy = false;
            if(s.charAt(0) == s.charAt(s.length()-1)){
                for (int i = 1; i < s.length() ; i++) {
                    if(s.charAt(i) != s.charAt(0)){
                        bouncy = true;
                        break;
                    }
                }
            } else if(s.charAt(0) < s.charAt(s.length()-1)){
                for (int i = 1; i < s.length() ; i++) {
                    if(s.charAt(i) < s.charAt(i-1)){
                        bouncy = true;
                        break;
                    }
                }
            } else {
                for (int i = 1; i < s.length() ; i++) {
                    if(s.charAt(i) > s.charAt(i-1)){
                        bouncy = true;
                        break;
                    }
                }
            }
            if(bouncy)
                cnt++;
            if(cnt*100 >= num*99){
                System.out.println("num = " + num);
                break;
            }

        }
    }
}
