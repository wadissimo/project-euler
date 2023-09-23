package common;

public class Fenwick {
        public int n;
        public long[] t;
        int maxBit;

        public Fenwick(int n) {
            this.n = n;
            t = new long[n];
            maxBit = Integer.highestOneBit(n);
        }

        public long get(int r) {
            long ans = 0;
            while (r >= 0) {
                ans += t[r];
                r = (r & (r + 1)) - 1;
            }
            return ans;
        }

        public void add(int pos, long val) {
            while (pos < n) {
                t[pos] += val;
                pos |= pos + 1;
            }
        }

        public int findG(long sum) {
            int idx = 0;
            int bitMask = maxBit;
            while (bitMask != 0) {
                int tIdx = idx + bitMask;
                bitMask /= 2;
                if (tIdx >= t.length)
                    continue;
                if (sum >= t[tIdx]) {
                    idx = tIdx;
                    sum -= t[tIdx];
                }
            }
            if (sum != 0) {
                return -1;
            }
            return idx;
        }

    public static void main(String[] args) {
        Fenwick tree = new Fenwick(18);
        tree.add(17, 2);
        tree.add(7, 5);
        tree.add(0, 3);
        System.out.println(tree.get(17));

    }
}
