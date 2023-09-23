package project_euler;

public class p091_right_triangles {
    public static void main(String[] args) {
        int LIM = 50;
        int cnt = 0;
        for (int x1 = 0; x1 <= LIM; x1++) {
            for (int y1 = 0; y1 <= LIM; y1++) {
                if(x1 == 0 && y1 == 0)
                    continue;
                for (int x2 = x1; x2 <= LIM; x2++) {
                    for (int y2 = 0; y2 <= LIM; y2++) {
                        if(x2 == 0 && y2== 0)
                            continue;
                        if(x2 == x1 && y2 >= y1)
                            break;
                        if(x1*x2 + y1*y2 == 0 || x1*(x2-x1) + y1*(y2-y1) == 0 || x2*(x2-x1) + y2*(y2-y1) == 0){
                            cnt++;
                            //System.out.printf("(%d, %d) , (%d, %d)%n", x1,y1,x2,y2);
                        }

                    }
                }

            }
        }
        System.out.println("cnt = " + cnt);
    }
}
