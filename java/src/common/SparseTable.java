package common;
/**
 * Created on 21.09.2018.
 * todo:untested
 */
public class SparseTable {
     int MAX_LOG_N;
     int MAX_N;
     long [][] lookup;

     int[] log2;

    public SparseTable(int MAX_LOG_N, int MAX_N) {
        this.MAX_LOG_N = MAX_LOG_N;
        this.MAX_N = MAX_N;
        lookup = new long[MAX_N][MAX_LOG_N];
        log2 = new int[MAX_LOG_N];
        preCalcLog(log2, MAX_N);
    }

    public
    SparseTable(int MAX_LOG_N, int MAX_N, int[] log2) {
        this.MAX_LOG_N = MAX_LOG_N;
        this.MAX_N = MAX_N;
        this.log2 = log2;
        lookup = new long[MAX_N][MAX_LOG_N];
    }

    //todo:test
    public static void preCalcLog(int log2[], int MAX_N) {
        for (int i = 0, j = 0; i < MAX_N; ++i) {
            if (1 << (j + 1) <= i) ++j;
            log2[i] = j;
        }
    }

    public void buildSparseTable(long arr[], int n) {
        for (int i = 0; i < n; i++)
            lookup[i][0] = arr[i];

        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 0; (i + (1 << j) - 1) < n; i++) {
                if (lookup[i][j - 1] <
                        lookup[i + (1 << (j - 1))][j - 1])
                    lookup[i][j] = lookup[i][j - 1];
                else
                    lookup[i][j] =
                            lookup[i + (1 << (j - 1))][j - 1];
            }
        }
    }

    public long query(int L, int R) {
        int j = (int)Math.log(R - L + 1);
        if (lookup[L][j] <= lookup[R - (1 << j) + 1][j])
            return lookup[L][j];
        else
            return lookup[R - (1 << j) + 1][j];
    }
    public long query(int L, int R ,int[] log2) {
        int j = log2[R - L + 1];
        if (lookup[L][j] <= lookup[R - (1 << j) + 1][j])
            return lookup[L][j];
        else
            return lookup[R - (1 << j) + 1][j];
    }

}