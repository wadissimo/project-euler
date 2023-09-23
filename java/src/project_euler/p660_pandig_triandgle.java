package project_euler;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;

public class p660_pandig_triandgle {

    public static final int MAX = 1_000_000_000;
    static long recB(long a, int bmask, long b, int cmask, int D){
        if(bmask == 0){
            if(a > b)
                return 0;
            if(a > MAX || b > MAX)
                throw new RuntimeException();
            long c2 = b * b + b * a + a * a;
            long c = (long)Math.sqrt(c2);
            if(c*c == c2 && c > b){
                long cc = c;
                int cnt = 0;
                while(cc > 0){
                    int dig = (int)(cc%D);
                    if((cmask&(1<<dig)) == 0){
                        return 0;
                    }
                    cc/=D;
                    cnt++;
                }
                if(cnt != Integer.bitCount(cmask))
                    return 0;
                System.out.print("a = " + a);
                System.out.print(", b = " + b);
                System.out.println(", c = " + c);
                return c;
            }
        }
        long res = 0;
        for (int i = 0; i < D; i++) {
            if(i == 0 && b == 0)
                continue;
            if((bmask&(1<<i)) != 0){
                res+=recB(a, bmask^(1<<i), b*D+i, cmask, D);
            }
        }
        return res;

    }
    static long recA(int amask, long a, int bmask, int cmask, int D){
        if(amask == 0){
            return recB(a, bmask, 0, cmask, D);
        }
        long res = 0;
        for (int i = 0; i < D; i++) {
            if(i == 0 && a == 0)
                continue;
            if((amask&(1<<i)) != 0){
                res+=recA(amask^(1<<i), a*D+i, bmask, cmask, D);
            }
        }
        return res;
    }
    static class Node{
        BigInteger A, B, C;
        long a,b,c;
        int al, bl, cl;
        int used;
        void init(int a, int b, int c) {
            al = bl = cl = 1;
            this.a = a;
            this.b = b;
            this.c = c;
            this.A = BigInteger.valueOf(a);
            this.B = BigInteger.valueOf(b);
            this.C = BigInteger.valueOf(c);
            used = (1<<a)|(1<<b)|(1<<c);
        }
    }

    /*static long go(Node cur, int al, int bl, int cl, int D, int dig){
        long res = 0;
        BigInteger MD = BigInteger.valueOf(D).pow(dig);
        BigInteger MD1 = BigInteger.valueOf(D).pow(dig+1);
        if(dig < al && dig < bl && dig < cl){
            for (int ad = 0; ad < D; ad++) {
                if((cur.used&(1<<ad)) != 0) continue;
                for (int bd = 0; bd < D; bd++) {
                    if (bd == ad) continue;
                    if((cur.used&(1<<bd)) != 0) continue;
                    for (int cd = 0; cd < D; cd++) {
                        if (cd == ad || cd == bd) continue;
                        if((cur.used&(1<<cd)) != 0) continue;
                        BigInteger A = BigInteger.valueOf(ad).multiply(MD).add(cur.A);
                        BigInteger B = BigInteger.valueOf(bd).multiply(MD).add(cur.B);
                        BigInteger C = BigInteger.valueOf(cd).multiply(MD).add(cur.C);
                        if (A.multiply(A).add(B.multiply(B)).add(A.multiply(B)).mod(MD1).equals(C.multiply(C).mod(MD1))) {
                            Node node = new Node();
                            node.used = cur.used|(1<<ad)|(1<<bd)|(1<<cd);
                            node.A = A; node.B = B; node.C = C;
                            res += go(node, al, bl, cl, D, dig+1);
                        }
                    }
                }
            }
        } else if(dig < bl && dig < cl){
            for (int bd = 0; bd < D; bd++) {
                if((cur.used&(1<<bd)) != 0) continue;
                for (int cd = 0; cd < D; cd++) {
                    if (cd == bd) continue;
                    if((cur.used&(1<<cd)) != 0) continue;
                    BigInteger A = cur.A;
                    BigInteger B = BigInteger.valueOf(bd).multiply(MD).add(cur.B);
                    BigInteger C = BigInteger.valueOf(cd).multiply(MD).add(cur.C);
                    if (A.multiply(A).add(B.multiply(B)).add(A.multiply(B)).mod(MD1).equals(C.multiply(C).mod(MD1))) {
                        Node node = new Node();
                        node.used = cur.used|(1<<bd)|(1<<cd);
                        node.A = A; node.B = B; node.C = C;
                        res += go(node, al, bl, cl, D, dig+1);
                    }
                }
            }
        } else if(dig<cl){
            for (int cd = 0; cd < D; cd++) {
                if((cur.used&(1<<cd)) != 0) continue;
                BigInteger A = cur.A;
                BigInteger B = cur.B;
                BigInteger C = BigInteger.valueOf(cd).multiply(MD).add(cur.C);
                if (A.multiply(A).add(B.multiply(B)).add(A.multiply(B)).mod(MD1).equals(C.multiply(C).mod(MD1))) {
                    Node node = new Node();
                    node.used = cur.used|(1<<cd);
                    node.A = A; node.B = B; node.C = C;
                    res +=go(node, al, bl, cl, D, dig+1);
                }
            }
        } else {
            if(cur.used != (1<<D)-1) {
                throw new RuntimeException("wrong mask");
            }
            if(cur.A.compareTo(cur.B) < 0 && cur.B.compareTo(cur.C) < 0){
                BigInteger A = cur.A;
                BigInteger B = cur.B;
                BigInteger C = cur.C;
                BigInteger left = A.multiply(A).add(B.multiply(B)).add(A.multiply(B));
                BigInteger right = C.multiply(C);
                boolean test = left.equals(right);
                if(!test)
                    return 0;
//                System.out.println("left = " + left.toString(D));
//                System.out.println("right = " + right.toString(D));
//                System.out.println("test = " + test);
                System.out.print("cur.A = " + cur.A);
                System.out.print(", cur.B = " + cur.B);
                System.out.println(", cur.C = " + cur.C);
                System.out.print("Dig cur.A = " + cur.A.toString(D));
                System.out.print(", cur.B = " + cur.B.toString(D));
                System.out.println(", cur.C = " + cur.C.toString(D));
                return cur.C.longValue();
            }
        }
        return res;
    }
*/
    static long MAX_INT = 1_000_000_000;
    static long total = 0;
    static long passed = 0;
    static long go(Node cur, int al, int bl, int cl, int D, int dig){
        long res = 0;

        BigInteger MD = BigInteger.valueOf(D).pow(dig);
        long md = MD.longValue();
        long md1 = md*D;
        if(dig < al && dig < bl && dig < cl) {
            for (int ad = 0; ad < D; ad++) {
                if((cur.used&(1<<ad)) != 0) continue;
                if(ad == 0 && dig == al-1) continue;
                for (int bd = 0; bd < D; bd++) {
                    if (bd == ad) continue;
                    if((cur.used&(1<<bd)) != 0) continue;
                    if(bd == 0 && dig == bl-1) continue;
                    for (int cd = 0; cd < D; cd++) {
                        if (cd == ad || cd == bd) continue;
                        if((cur.used&(1<<cd)) != 0) continue;
                        if(cd == 0 && dig == cl-1) continue;
                        long a = (ad*md+cur.a)%md1;
                        long b = (bd*md+cur.b)%md1;
                        long c = (cd*md+cur.c)%md1;
                        if ((a*a + b*b +b*a)%md1 ==c*c%md1) {
                            Node node = new Node();
                            node.used = cur.used|(1<<ad)|(1<<bd)|(1<<cd);
                            node.a = ad*md+cur.a;node.b = bd*md+cur.b;node.c =cd*md+cur.c;
                            res += go(node, al, bl, cl, D, dig+1);
                        }
                    }
                }
            }
        } else if(dig < bl && dig < cl){
            for (int bd = 0; bd < D; bd++) {
                if((cur.used&(1<<bd)) != 0) continue;
                if(bd == 0 && dig == bl-1) continue;
                for (int cd = 0; cd < D; cd++) {
                    if (cd == bd) continue;
                    if((cur.used&(1<<cd)) != 0) continue;
                    if(cd == 0 && dig == cl-1) continue;
                    long a = cur.a%md1;
                    long b = (bd*md+cur.b)%md1;
                    long c = (cd*md+cur.c)%md1;
                    if ((a*a + b*b +b*a)%md1 ==c*c%md1) {
                        Node node = new Node();
                        node.used = cur.used|(1<<bd)|(1<<cd);
                        node.a = cur.a;node.b = bd*md+cur.b;node.c = cd*md+cur.c;
                        res += go(node, al, bl, cl, D, dig+1);
                    }
                }
            }
        } else if(dig<cl) {
            for (int cd = 0; cd < D; cd++) {
                if((cur.used&(1<<cd)) != 0) continue;
                if(cd == 0 && dig == cl-1) continue;
                long a = cur.a%md1;
                long b = cur.b%md1;
                long c = (cd*md+cur.c)%md1;
                if ((a*a + b*b +b*a)%md1 ==c*c%md1) {
                    Node node = new Node();
                    node.used = cur.used|(1<<cd);
                    node.a = cur.a;node.b = cur.b;node.c = cd*md+cur.c;
                    res += go(node, al, bl, cl, D, dig+1);
                }
            }
        } else {
            if(cur.used != (1<<D)-1) {
                throw new RuntimeException("wrong mask");
            }
            if(cur.a < cur.b && cur.b < cur.c){
                total++;
                if(cur.a > MAX_INT || cur.b > MAX_INT || cur.c > MAX_INT) {
                    BigInteger A = BigInteger.valueOf(cur.a);
                    BigInteger B = BigInteger.valueOf(cur.b);
                    BigInteger C = BigInteger.valueOf(cur.c);
                    BigInteger left = A.multiply(A).add(B.multiply(B)).add(A.multiply(B));
                    BigInteger right = C.multiply(C);
                    boolean test = left.equals(right);
                    if (!test)
                        return 0;
                } else {
                    if(cur.a*cur.a + cur.b*cur.b+cur.b*cur.a != cur.c*cur.c){
                        return 0;
                    }
                }
                passed++;
                BigInteger A = BigInteger.valueOf(cur.a);
                BigInteger B = BigInteger.valueOf(cur.b);
                BigInteger C = BigInteger.valueOf(cur.c);
//                System.out.println("left = " + left.toString(D));
//                System.out.println("right = " + right.toString(D));
//                System.out.println("test = " + test);
                System.out.print("cur.A = " + A);
                System.out.print(", cur.B = " + B);
                System.out.println(", cur.C = " + C);
                System.out.print("Dig cur.A = " + A.toString(D));
                System.out.print(", cur.B = " + B.toString(D));
                System.out.println(", cur.C = " + C.toString(D));
                return cur.c;
            }
        }
        return res;
    }

    LinkedList<Node> triples(int D){
        long md = D*D*D;
        LinkedList<Node> res = new LinkedList<>();
        for (int a1 = 0; a1 < D; a1++) {
            for (int a2 = 0; a2 < D; a2++) {
                if(a1==a2) continue;
                for (int a3 = 0; a3 < D; a3++) {
                    if(a3==a2||a3==a1) continue;
                    for (int b1 = 0; b1 < D; b1++) {
                        if(b1 == a1 || b1 == a2 || b1 == a3) continue;
                        for (int b2 = 0; b2 < D; b2++) {
                            if(b2 == b1 || b2 == a1 || b2 == a2 || b2 == a3) continue;
                            for (int b3 = 0; b3 < D; b3++) {
                                if(b3 == b2 || b3 == b1 || b3 == a1 || b3 == a2 || b3 == a3) continue;
                                for (int c1 = 0; c1 < D; c1++) {
                                    if(c1 ==b3 || c1 == b2 || c1 == b1 || c1 == a1 || c1 == a2 || c1 == a3) continue;
                                    for (int c2 = 0; c2 < D; c2++) {
                                        if(c2 == c1 || c2 ==b3 || c2 == b2 || c2 == b1 || c2 == a1 || c2 == a2 || c2 == a3) continue;
                                        for (int c3 = 0; c3 < D; c3++) {
                                            if(c3 == c2 || c3 == c1 || c3 ==b3 || c3 == b2 || c3 == b1 || c3 == a1 || c3 == a2 || c3 == a3) continue;
                                            long a = a1+a2*D+a3*D*D;
                                            long b = b1+b2*D+b3*D*D;
                                            long c = c1+c2*D+c3*D*D;
                                            if((a*a +b*b+b*a)%md == c*c%md){
                                                System.out.println("a = " + a);
                                                System.out.println("b = " + b);
                                                System.out.println("c = " + c);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        long mod = 1_000_000_007;
        long ans = 0;
        for (int D = 9; D <=18 ; D++) {
            LinkedList<Node> first = new LinkedList<>();
            for (int a = 0; a < D; a++) {
                for (int b = 0; b < D; b++) {
                    if (b == a) continue;
                    for (int c = 0; c < D; c++) {
                        if (c == a || c == b) continue;
                        if ((a * a + a * b + b * b)%D == (c * c)%D) {
                            Node node = new Node();
                            node.init(a,b,c);
                            first.add(node);
                        }
                    }
                }
            }
            System.out.println("D = " + D);
            System.out.println("first.size() = " + first.size());
            System.out.println("ans = " + ans);
            System.out.println("total = " + total);
            System.out.println("passed = " + passed);
            System.out.println("rejected = " + (total - passed));
            for (int cl = D/3; cl <=D/2 ; cl++) {
                for (int al = D/3-1; al <=D/3 ; al++) {
                    int bl = D-cl-al;
                    if(al<=bl&&bl<=cl){
                        for (Node node : first) {
                            long res = go(node, al, bl, cl, D, 1);
                            ans+=res;
                        }
                    }
                }
            }
        }
        /*int dd = 9;
        int [] digits = new int[12];
        for (int a = 1; a <1000 ; a++) {
            for (int b = a+1; b < 5000; b++) {
                int left = a*a+b*b+a*b;
                for (int c = b+1; c < 9000; c++) {
                    if(left != c*c) continue;
                    Arrays.fill(digits, 0);
                    boolean valid = true;
                    int aa = a;
                    while(aa > 0){
                        int dig = aa%dd;
                        if(digits[dig] != 0){
                            valid = false;
                            break;
                        }
                        digits[dig] = 1;
                        aa /= dd;
                    }
                    if(valid){
                        aa = b;
                        while(aa > 0){
                            int dig = aa%dd;
                            if(digits[dig] != 0){
                                valid = false;
                                break;
                            }
                            digits[dig] = 1;
                            aa /= dd;
                        }
                    }
                    if(valid){
                        aa = c;
                        while(aa > 0){
                            int dig = aa%dd;
                            if(digits[dig] != 0){
                                valid = false;
                                break;
                            }
                            digits[dig] = 1;
                            aa /= dd;
                        }
                    }
                    if(valid){
                        System.out.print("a = " + a);
                        System.out.print(", b = " + b);
                        System.out.println(", c = " + c);
                    }
                }
            }
        }*/
        System.out.println("ans = " + ans);
        if(true) return;

        for (int D = 9; D <=18; D++) {
            System.out.println("D = " + D);
            System.out.println("ans = " + ans);
            int max = 1<<D;
            for (int cmask = 1; cmask < max; cmask++) {
                int ccnt = Integer.bitCount(cmask);
                if(ccnt < D/3 || ccnt > D/2)
                    continue;
                int rem = (max-1)^cmask;
                for(int bmask = (rem-1)&rem; bmask > 0; bmask = (bmask-1)&rem){
                    int amask = rem^bmask;
                    int bcnt = Integer.bitCount(bmask);
                    int acnt = D-bcnt-ccnt;
                    if(acnt <= bcnt && bcnt <= ccnt){
                        //long res = recA(amask, 0, bmask, cmask, D);
                        //ans += res;
                        ans++;
                    }
                }
            }
        }
        System.out.println("ans = " + ans);
    }
}
