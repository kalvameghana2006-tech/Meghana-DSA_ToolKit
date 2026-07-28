import java.io.*;
import java.util.*;

class Main{

    public static final boolean DEBUG = true;

    // Modular Arithmetic Utilities (Class: Mod)
    static class Mod {
        public static final long MOD = 1_000_000_007L;

        // Purpose: Modular Addition (a + b) % MOD | Input: long valA, long valB | Output: long
        public static long add(long a, long b) { long res = (a % MOD + b % MOD + MOD) % MOD; DBG.log("Mod.add (" + a + "+" + b + ")", res); return res; }
        // Purpose: Modular Subtraction (a - b) % MOD | Input: long valA, long valB | Output: long
        public static long sub(long a, long b) { long res = (a % MOD - b % MOD + MOD) % MOD; DBG.log("Mod.sub (" + a + "-" + b + ")", res); return res; }
        // Purpose: Modular Multiplication (a * b) % MOD | Input: long valA, long valB | Output: long
        public static long mul(long a, long b) { long res = ((a % MOD) * (b % MOD)) % MOD; DBG.log("Mod.mul (" + a + "*" + b + ")", res); return res; }
        // Purpose: Modular Inverse via Fermat's Little Theorem (a^(MOD-2) % MOD) | Input: long val | Output: long
        public static long inv(long a) { long res = Mth.pow(a, MOD - 2, MOD); DBG.log("Mod.inv (" + a + ")", res); return res; }
        // Purpose: Modular Division (a / b) % MOD via Fermat's Little Theorem | Input: long valA, long valB | Output: long
        public static long div(long a, long b) { long res = mul(a, inv(b)); DBG.log("Mod.div (" + a + "/" + b + ")", res); return res; }
    }

    // Debug Utilities (Class: DBG)
    static class DBG {
        // Purpose: Log variable name and value | Input: String label, Object val | Output: void
        public static void log(String label, Object val) { if (DEBUG) System.err.println("[DEBUG] " + label + " = " + (val instanceof int[] ? Arrays.toString((int[]) val) : val instanceof long[] ? Arrays.toString((long[]) val) : val instanceof Object[] ? Arrays.deepToString((Object[]) val) : val)); }
        // Purpose: Print 2D char matrix | Input: String label, char[][] grid | Output: void
        public static void grid(String label, char[][] g) { if (!DEBUG) return; System.err.println("[DEBUG GRID] " + label + ":"); for (char[] r : g) System.err.println("  " + new String(r)); }
        // Purpose: Log execution milestone | Input: String infoMessage | Output: void
        public static void msg(String info) { if (DEBUG) System.err.println("[DEBUG LOG] " + info); }
    }

    // Edge Case & Test Case Generator (Class: Gen)
    static class Gen {
        private static final Random rnd = new Random();
        // Purpose: Generate random integer in range | Input: int minVal, int maxVal | Output: int
        public static int i(int min, int max) { int val = min + rnd.nextInt(max - min + 1); DBG.log("Gen.i [" + min + "," + max + "]", val); return val; }
        // Purpose: Generate random long in range | Input: long minVal, long maxVal | Output: long
        public static long l(long min, long max) { long val = min + (long)(rnd.nextDouble() * (max - min + 1)); DBG.log("Gen.l [" + min + "," + max + "]", val); return val; }
        // Purpose: Generate random integer array | Input: int size, int minVal, int maxVal | Output: int[]
        public static int[] iArr(int sz, int min, int max) { int[] a = new int[sz]; for (int k = 0; k < sz; k++) a[k] = i(min, max); DBG.log("Gen.iArr", a); return a; }
        // Purpose: Generate edge-case array (0, 1, -1, INT_MAX, INT_MIN) | Input: int size | Output: int[]
        public static int[] edgeArr(int sz) { int[] a = new int[sz]; int[] pool = {0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE}; for (int k = 0; k < sz; k++) a[k] = pool[rnd.nextInt(pool.length)]; DBG.log("Gen.edgeArr", a); return a; }
        // Purpose: Generate random lowercase English string | Input: int length | Output: String
        public static String str(int len) { StringBuilder sb = new StringBuilder(); for (int k = 0; k < len; k++) sb.append((char)('a' + rnd.nextInt(26))); String res = sb.toString(); DBG.log("Gen.str", res); return res; }
    }

    // Fast Input Reader (Class: IO)
    static class IO {
        private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private static StringTokenizer st;

        // Purpose: Read next space-separated token | Input: none | Output: String
        public static String next() { while (st == null || !st.hasMoreTokens()) { try { String line = br.readLine(); if (line == null) return null; st = new StringTokenizer(line); } catch (IOException e) { e.printStackTrace(); } } String res = st.nextToken(); DBG.log("IO.next", res); return res; }
        // Purpose: Read next integer | Input: none | Output: int
        public static int i() { int val = Integer.parseInt(next()); DBG.log("IO.i", val); return val; }
        // Purpose: Read next long integer | Input: none | Output: long
        public static long l() { long val = Long.parseLong(next()); DBG.log("IO.l", val); return val; }
        // Purpose: Read full remaining line | Input: none | Output: String
        public static String line() { try { String res = br.readLine(); DBG.log("IO.line", res); return res; } catch (IOException e) { e.printStackTrace(); return null; } }
        // Purpose: Read 1D integer array | Input: int size | Output: int[]
        public static int[] iArr(int sz) { int[] a = new int[sz]; for (int k = 0; k < sz; k++) a[k] = i(); DBG.log("IO.iArr", a); return a; }
        // Purpose: Read 1D long array | Input: int size | Output: long[]
        public static long[] lArr(int sz) { long[] a = new long[sz]; for (int k = 0; k < sz; k++) a[k] = l(); DBG.log("IO.lArr", a); return a; }
        // Purpose: Read 1D string array | Input: int size | Output: String[]
        public static String[] sArr(int sz) { String[] a = new String[sz]; for (int k = 0; k < sz; k++) a[k] = next(); DBG.log("IO.sArr", a); return a; }
    }

    // Monotonic Stack Utilities (Class: Stk)
    static class Stk {
        // Purpose: Find Next Greater Element values | Input: int[] array | Output: int[]
        public static int[] nge(int[] a) { DBG.log("Stk.nge Input", a); int n = a.length, res[] = new int[n]; Arrays.fill(res, -1); Deque<Integer> st = new ArrayDeque<>(); for (int k = n - 1; k >= 0; k--) { while (!st.isEmpty() && st.peek() <= a[k]) st.pop(); if (!st.isEmpty()) res[k] = st.peek(); st.push(a[k]); } DBG.log("Stk.nge Result", res); return res; }
        // Purpose: Find Next Greater Element indices | Input: int[] array | Output: int[]
        public static int[] ngei(int[] a) { DBG.log("Stk.ngei Input", a); int n = a.length, res[] = new int[n]; Arrays.fill(res, -1); Deque<Integer> st = new ArrayDeque<>(); for (int k = n - 1; k >= 0; k--) { while (!st.isEmpty() && a[st.peek()] <= a[k]) st.pop(); if (!st.isEmpty()) res[k] = st.peek(); st.push(k); } DBG.log("Stk.ngei Result", res); return res; }
    }

    // Grid Traversal Utilities (Class: Grd)
    static class Grd {
        private static final int[] dR = {-1, 1, 0, 0}, dC = {0, 0, -1, 1};

        // Purpose: Check if grid cell coordinates are valid | Input: int row, int col, int maxRows, int maxCols | Output: boolean
        public static boolean ok(int r, int c, int R, int C) { boolean valid = r >= 0 && r < R && c >= 0 && c < C; DBG.log("Grd.ok (" + r + "," + c + ")", valid); return valid; }
        // Purpose: 4-directional Depth-First Search on grid | Input: int row, int col, char[][] grid, boolean[][] visited | Output: void
        public static void dfs(int r, int c, char[][] g, boolean[][] vis) { vis[r][c] = true; DBG.log("Grd.dfs Visit", r + "," + c); for (int k = 0; k < 4; k++) { int nR = r + dR[k], nC = c + dC[k]; if (ok(nR, nC, g.length, g[0].length) && !vis[nR][nC] && g[nR][nC] != '#') dfs(nR, nC, g, vis); } }
        // Purpose: 4-directional Breadth-First Search on grid | Input: int startRow, int startCol, char[][] grid, boolean[][] visited | Output: void
        public static void bfs(int sR, int sC, char[][] g, boolean[][] vis) { Queue<int[]> q = new ArrayDeque<>(); q.add(new int[]{sR, sC}); vis[sR][sC] = true; DBG.log("Grd.bfs Start", sR + "," + sC); while (!q.isEmpty()) { int[] cur = q.poll(); DBG.log("Grd.bfs Cell", cur[0] + "," + cur[1]); for (int k = 0; k < 4; k++) { int nR = cur[0] + dR[k], nC = cur[1] + dC[k]; if (ok(nR, nC, g.length, g[0].length) && !vis[nR][nC] && g[nR][nC] != '#') { vis[nR][nC] = true; q.add(new int[]{nR, nC}); } } } }
    }

    // Disjoint Set Union (Class: DSU)
    static class DSU {
        private final int[] p, sz;

        // Purpose: Initialize DSU structure | Input: int nodeCount | Output: DSU instance
        public DSU(int n) { p = new int[n]; sz = new int[n]; for (int k = 0; k < n; k++) { p[k] = k; sz[k] = 1; } DBG.msg("DSU.Init with size " + n); }
        // Purpose: Find set representative with path compression | Input: int node | Output: int
        public int find(int k) { int root = p[k] == k ? k : (p[k] = find(p[k])); DBG.log("DSU.find (" + k + ")", root); return root; }
        // Purpose: Union two sets by size | Input: int nodeA, int nodeB | Output: boolean
        public boolean union(int a, int b) { int rA = find(a), rB = find(b); if (rA == rB) { DBG.log("DSU.union Redundant", a + "-" + b); return false; } if (sz[rA] < sz[rB]) { int t = rA; rA = rB; rB = t; } p[rB] = rA; sz[rA] += sz[rB]; DBG.log("DSU.union Merged", a + " into " + b); return true; }
        // Purpose: Check if two nodes belong to same set | Input: int nodeA, int nodeB | Output: boolean
        public boolean same(int a, int b) { boolean res = find(a) == find(b); DBG.log("DSU.same (" + a + "," + b + ")", res); return res; }
    }

    // Segment Tree for Range Sum (Class: ST)
    static class ST {
        private final int n; private final long[] t;

        // Purpose: Build Segment Tree from initial array | Input: long[] array | Output: ST instance
        public ST(long[] a) { this.n = a.length; this.t = new long[4 * n]; DBG.log("ST.Init Input", a); build(a, 0, 0, n - 1); }
        private void build(long[] a, int node, int s, int e) { if (s == e) { t[node] = a[s]; return; } int m = (s + e) / 2; build(a, 2 * node + 1, s, m); build(a, 2 * node + 2, m + 1, e); t[node] = t[2 * node + 1] + t[2 * node + 2]; }
        // Purpose: Perform point update | Input: int targetIndex, long newValue | Output: void
        public void upd(int idx, long val) { DBG.log("ST.upd Idx " + idx, val); upd(0, 0, n - 1, idx, val); }
        private void upd(int node, int s, int e, int idx, long val) { if (s == e) { t[node] = val; return; } int m = (s + e) / 2; if (s <= idx && idx <= m) upd(2 * node + 1, s, m, idx, val); else upd(2 * node + 2, m + 1, e, idx, val); t[node] = t[2 * node + 1] + t[2 * node + 2]; }
        // Purpose: Query range sum [left, right] | Input: int leftIndex, int rightIndex | Output: long
        public long q(int l, int r) { long res = q(0, 0, n - 1, l, r); DBG.log("ST.q [" + l + "," + r + "]", res); return res; }
        private long q(int node, int s, int e, int l, int r) { if (r < s || e < l) return 0; if (l <= s && e <= r) return t[node]; int m = (s + e) / 2; return q(2 * node + 1, s, m, l, r) + q(2 * node + 2, m + 1, e, l, r); }
    }

    // Math & Number Theory Utilities
static class Mth {

    // Purpose: Compute Greatest Common Divisor
    // Input: long a, long b
    // Output: long
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Purpose: Compute Least Common Multiple
    // Input: long a, long b
    // Output: long
    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    // Purpose: Fast Modular Exponentiation
    // Input: long base, long exponent, long mod
    // Output: long
    public static long pow(long b, long e, long mod) {
        long res = 1;
        b %= mod;

        while (e > 0) {
            if ((e & 1) == 1)
                res = (res * b) % mod;

            b = (b * b) % mod;
            e >>= 1;
        }

        return res;
    }

    // Purpose: Check whether a number is prime
    // Input: int number
    // Output: boolean
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }

    // Purpose: Generate Prime Table (Sieve of Eratosthenes)
    // Input: int limit
    // Output: boolean[]
    public static boolean[] sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);

        if (n >= 0) prime[0] = false;
        if (n >= 1) prime[1] = false;

        for (int i = 2; i * i <= n; i++)
            if (prime[i])
                for (int j = i * i; j <= n; j += i)
                    prime[j] = false;

        return prime;
    }

    // Purpose: Smallest Prime Factor (SPF)
    // Input: int limit
    // Output: int[]
    public static int[] spf(int n) {
        int[] spf = new int[n + 1];

        for (int i = 0; i <= n; i++)
            spf[i] = i;

        for (int i = 2; i * i <= n; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= n; j += i)
                    if (spf[j] == j)
                        spf[j] = i;
            }
        }

        return spf;
    }

    // Purpose: Prime Factorization using SPF
    // Input: int number, int[] spf
    // Output: ArrayList<Integer>
    public static ArrayList<Integer> primeFactors(int n, int[] spf) {
        ArrayList<Integer> ans = new ArrayList<>();

        while (n > 1) {
            ans.add(spf[n]);
            n /= spf[n];
        }

        return ans;
    }

    // Purpose: Precompute Factorials modulo mod
    // Input: int limit, int mod
    // Output: long[]
    public static long[] fact(int n, int mod) {
        long[] fact = new long[n + 1];
        fact[0] = 1;

        for (int i = 1; i <= n; i++)
            fact[i] = (fact[i - 1] * i) % mod;

        return fact;
    }

    // Purpose: Modular Inverse (mod must be prime)
    // Input: long number, int mod
    // Output: long
    public static long modInv(long a, int mod) {
        return pow(a, mod - 2, mod);
    }

    // Purpose: Compute nCr modulo mod
    // Input: int n, int r, int mod, long[] factorial
    // Output: long
    public static long nCr(int n, int r, int mod, long[] fact) {
        if (r < 0 || r > n)
            return 0;

        long num = fact[n];
        long den = (fact[r] * fact[n - r]) % mod;

        return (num * modInv(den, mod)) % mod;
    }

    // Purpose: Euler's Totient Function
    // Input: int number
    // Output: int
    public static int phi(int n) {
        int res = n;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;

                res -= res / i;
            }
        }

        if (n > 1)
            res -= res / n;

        return res;
    }
}

    // Array Binary Search & Prefix/Suffix Utilities (Class: Arr)
    static class Arr {
        // Purpose: Find lower bound index (first index where element >= target) | Input: int[] sortedArray, int targetValue | Output: int
        public static int lb(int[] a, int x) { int l = 0, h = a.length; while (l < h) { int m = (l + h) / 2; if (a[m] >= x) h = m; else l = m + 1; } DBG.log("Arr.lb for " + x, l); return l; }
        // Purpose: Find upper bound index (first index where element > target) | Input: int[] sortedArray, int targetValue | Output: int
        public static int ub(int[] a, int x) { int l = 0, h = a.length; while (l < h) { int m = (l + h) / 2; if (a[m] > x) h = m; else l = m + 1; } DBG.log("Arr.ub for " + x, l); return l; }

        // --- PREFIX / SUFFIX UTILITIES ---
        // Purpose: Compute 1-indexed Prefix Sum array | Input: int[] array | Output: long[]
        public static long[] pSum(int[] a) { int n = a.length; long[] p = new long[n + 1]; for (int k = 0; k < n; k++) p[k + 1] = p[k] + a[k]; DBG.log("Arr.pSum Result", p); return p; }
        // Purpose: Compute Suffix Sum array | Input: int[] array | Output: long[]
        public static long[] sSum(int[] a) { int n = a.length; long[] s = new long[n + 1]; for (int k = n - 1; k >= 0; k--) s[k] = s[k + 1] + a[k]; DBG.log("Arr.sSum Result", s); return s; }
        // Purpose: Compute Prefix Minimums | Input: int[] array | Output: int[]
        public static int[] pMin(int[] a) { int n = a.length, p[] = new int[n]; if (n == 0) return p; p[0] = a[0]; for (int k = 1; k < n; k++) p[k] = Math.min(p[k - 1], a[k]); DBG.log("Arr.pMin Result", p); return p; }
        // Purpose: Compute Suffix Minimums | Input: int[] array | Output: int[]
        public static int[] sMin(int[] a) { int n = a.length, s[] = new int[n]; if (n == 0) return s; s[n - 1] = a[n - 1]; for (int k = n - 2; k >= 0; k--) s[k] = Math.min(s[k + 1], a[k]); DBG.log("Arr.sMin Result", s); return s; }
        // Purpose: Compute Prefix Maximums | Input: int[] array | Output: int[]
        public static int[] pMax(int[] a) { int n = a.length, p[] = new int[n]; if (n == 0) return p; p[0] = a[0]; for (int k = 1; k < n; k++) p[k] = Math.max(p[k - 1], a[k]); DBG.log("Arr.pMax Result", p); return p; }
        // Purpose: Compute Suffix Maximums | Input: int[] array | Output: int[]
        public static int[] sMax(int[] a) { int n = a.length, s[] = new int[n]; if (n == 0) return s; s[n - 1] = a[n - 1]; for (int k = n - 2; k >= 0; k--) s[k] = Math.max(s[k + 1], a[k]); DBG.log("Arr.sMax Result", s); return s; }
        // Purpose: Compute Prefix GCDs | Input: int[] array | Output: long[]
        public static long[] pGcd(int[] a) { int n = a.length; long[] p = new long[n]; if (n == 0) return p; p[0] = a[0]; for (int k = 1; k < n; k++) p[k] = Mth.gcd(p[k - 1], a[k]); DBG.log("Arr.pGcd Result", p); return p; }
        // Purpose: Compute Suffix GCDs | Input: int[] array | Output: long[]
        public static long[] sGcd(int[] a) { int n = a.length; long[] s = new long[n]; if (n == 0) return s; s[n - 1] = a[n - 1]; for (int k = n - 2; k >= 0; k--) s[k] = Mth.gcd(s[k + 1], a[k]); DBG.log("Arr.sGcd Result", s); return s; }
    }
        // Bit Manipulation Utilities (Class: Bit)
    static class Bit {

        // Purpose: Check whether ith bit is set | Input: int number, int bitPosition (0-based) | Output: boolean
        public static boolean get(int n, int i) { boolean res = ((n >> i) & 1) == 1; DBG.log("Bit.get (" + n + "," + i + ")", res); return res; }

        // Purpose: Set ith bit to 1 | Input: int number, int bitPosition (0-based) | Output: int
        public static int set(int n, int i) { int res = n | (1 << i); DBG.log("Bit.set (" + n + "," + i + ")", res); return res; }

        // Purpose: Clear ith bit to 0 | Input: int number, int bitPosition (0-based) | Output: int
        public static int clear(int n, int i) { int res = n & ~(1 << i); DBG.log("Bit.clear (" + n + "," + i + ")", res); return res; }

        // Purpose: Toggle ith bit | Input: int number, int bitPosition (0-based) | Output: int
        public static int toggle(int n, int i) { int res = n ^ (1 << i); DBG.log("Bit.toggle (" + n + "," + i + ")", res); return res; }

        // Purpose: Count number of set bits | Input: int number | Output: int
        public static int count(int n) { int res = Integer.bitCount(n); DBG.log("Bit.count (" + n + ")", res); return res; }

        // Purpose: Check whether number is a power of two | Input: int number | Output: boolean
        public static boolean isPow2(int n) { boolean res = n > 0 && (n & (n - 1)) == 0; DBG.log("Bit.isPow2 (" + n + ")", res); return res; }

        // Purpose: Get value of lowest set bit | Input: int number | Output: int
        public static int lowBit(int n) { int res = n & -n; DBG.log("Bit.lowBit (" + n + ")", res); return res; }

        // Purpose: Remove lowest set bit | Input: int number | Output: int
        public static int removeLowBit(int n) { int res = n & (n - 1); DBG.log("Bit.removeLowBit (" + n + ")", res); return res; }

        // Purpose: Check whether ith bit is set using mask | Input: int number, int bitPosition (0-based) | Output: boolean
        public static boolean check(int n, int i) { boolean res = (n & (1 << i)) != 0; DBG.log("Bit.check (" + n + "," + i + ")", res); return res; }

        // Purpose: Generate bitmask with lowest n bits set | Input: int bitCount | Output: int
        public static int mask(int n) { int res = (1 << n) - 1; DBG.log("Bit.mask (" + n + ")", res); return res; }
    }
        // Graph Utilities (Adjacency List)
    static class Gr {

        // Purpose: Perform Depth First Search (DFS) Traversal
        // Input: ArrayList<ArrayList<Integer>> graph, int startVertex
        // Output: ArrayList<Integer> traversalOrder
        public static ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> g, int src) {
            int n = g.size();
            boolean[] vis = new boolean[n];
            ArrayList<Integer> ans = new ArrayList<>();
            dfsUtil(src, g, vis, ans);
            return ans;
        }

        // Purpose: DFS Helper Function
        // Input: currentVertex, graph, visitedArray, answerList
        // Output: None
        private static void dfsUtil(int u, ArrayList<ArrayList<Integer>> g, boolean[] vis, ArrayList<Integer> ans) {
            vis[u] = true;
            ans.add(u);

            for (int v : g.get(u)) {
                if (!vis[v]) dfsUtil(v, g, vis, ans);
            }
        }

        // Purpose: Perform Breadth First Search (BFS) Traversal
        // Input: ArrayList<ArrayList<Integer>> graph, int startVertex
        // Output: ArrayList<Integer> traversalOrder
        public static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> g, int src) {
            int n = g.size();
            boolean[] vis = new boolean[n];
            Queue<Integer> q = new LinkedList<>();
            ArrayList<Integer> ans = new ArrayList<>();

            q.offer(src);
            vis[src] = true;

            while (!q.isEmpty()) {
                int u = q.poll();
                ans.add(u);

                for (int v : g.get(u)) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.offer(v);
                    }
                }
            }

            return ans;
        }
                // Purpose: Perform Topological Sort using DFS
        // Input: ArrayList<ArrayList<Integer>> graph
        // Output: ArrayList<Integer> topologicalOrder
        public static ArrayList<Integer> topoDFS(ArrayList<ArrayList<Integer>> g) {
            int n = g.size();
            boolean[] vis = new boolean[n];
            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < n; i++)
                if (!vis[i])
                    topoDFSUtil(i, g, vis, st);

            ArrayList<Integer> ans = new ArrayList<>();
            while (!st.isEmpty())
                ans.add(st.pop());

            return ans;
        }

        // Purpose: Topological Sort DFS Helper
        // Input: currentVertex, graph, visitedArray, stack
        // Output: None
        private static void topoDFSUtil(int u, ArrayList<ArrayList<Integer>> g, boolean[] vis, Stack<Integer> st) {
            vis[u] = true;

            for (int v : g.get(u))
                if (!vis[v])
                    topoDFSUtil(v, g, vis, st);

            st.push(u);
        }

        // Purpose: Perform Topological Sort using Kahn's Algorithm (BFS)
        // Input: ArrayList<ArrayList<Integer>> graph
        // Output: ArrayList<Integer> topologicalOrder
        public static ArrayList<Integer> topoKahn(ArrayList<ArrayList<Integer>> g) {
            int n = g.size();
            int[] indegree = new int[n];

            for (int i = 0; i < n; i++)
                for (int v : g.get(i))
                    indegree[v]++;

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < n; i++)
                if (indegree[i] == 0)
                    q.offer(i);

            ArrayList<Integer> ans = new ArrayList<>();

            while (!q.isEmpty()) {
                int u = q.poll();
                ans.add(u);

                for (int v : g.get(u)) {
                    indegree[v]--;
                    if (indegree[v] == 0)
                        q.offer(v);
                }
            }

            return ans;
        }
                // Purpose: Find shortest distance from source using Dijkstra's Algorithm
        // Input: ArrayList<ArrayList<int[]>> graph (neighbor, weight), int source
        // Output: int[] shortestDistances
        public static int[] dijkstra(ArrayList<ArrayList<int[]>> g, int src) {
            int n = g.size();
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

            dist[src] = 0;
            pq.offer(new int[]{src, 0});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int u = cur[0];
                int d = cur[1];

                if (d > dist[u]) continue;

                for (int[] edge : g.get(u)) {
                    int v = edge[0];
                    int wt = edge[1];

                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                        pq.offer(new int[]{v, dist[v]});
                    }
                }
            }

            return dist;
        }
        
    }
        // Binary Tree Utilities
    static class Tree {

        // Tree Node
        static class TreeNode {
            int val;
            TreeNode left, right;

            TreeNode(int val) {
                this.val = val;
            }
        }

        // -------------------- TRAVERSALS --------------------

        // Purpose: Perform Inorder Traversal (Left -> Root -> Right)
        // Input: TreeNode root
        // Output: ArrayList<Integer>
        public static ArrayList<Integer> inorder(TreeNode root) {
            ArrayList<Integer> ans = new ArrayList<>();
            inorderUtil(root, ans);
            return ans;
        }

        private static void inorderUtil(TreeNode root, ArrayList<Integer> ans) {
            if (root == null) return;
            inorderUtil(root.left, ans);
            ans.add(root.val);
            inorderUtil(root.right, ans);
        }

        // Purpose: Perform Preorder Traversal (Root -> Left -> Right)
        // Input: TreeNode root
        // Output: ArrayList<Integer>
        public static ArrayList<Integer> preorder(TreeNode root) {
            ArrayList<Integer> ans = new ArrayList<>();
            preorderUtil(root, ans);
            return ans;
        }

        private static void preorderUtil(TreeNode root, ArrayList<Integer> ans) {
            if (root == null) return;
            ans.add(root.val);
            preorderUtil(root.left, ans);
            preorderUtil(root.right, ans);
        }

        // Purpose: Perform Postorder Traversal (Left -> Right -> Root)
        // Input: TreeNode root
        // Output: ArrayList<Integer>
        public static ArrayList<Integer> postorder(TreeNode root) {
            ArrayList<Integer> ans = new ArrayList<>();
            postorderUtil(root, ans);
            return ans;
        }

        private static void postorderUtil(TreeNode root, ArrayList<Integer> ans) {
            if (root == null) return;
            postorderUtil(root.left, ans);
            postorderUtil(root.right, ans);
            ans.add(root.val);
        }

        // Purpose: Perform Level Order Traversal
        // Input: TreeNode root
        // Output: ArrayList<Integer>
        public static ArrayList<Integer> levelOrder(TreeNode root) {
            ArrayList<Integer> ans = new ArrayList<>();
            if (root == null) return ans;

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                ans.add(cur.val);

                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }

            return ans;
        }

        // -------------------- BASIC OPERATIONS --------------------

        // Purpose: Find Height of Binary Tree
        // Input: TreeNode root
        // Output: int
        public static int height(TreeNode root) {
            if (root == null) return 0;
            return 1 + Math.max(height(root.left), height(root.right));
        }

        // Purpose: Count Total Nodes
        // Input: TreeNode root
        // Output: int
        public static int countNodes(TreeNode root) {
            if (root == null) return 0;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }

        // Purpose: Count Leaf Nodes
        // Input: TreeNode root
        // Output: int
        public static int countLeaves(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            return countLeaves(root.left) + countLeaves(root.right);
        }

        // Purpose: Find Maximum Value in Tree
        // Input: TreeNode root
        // Output: int
        public static int max(TreeNode root) {
            if (root == null) return Integer.MIN_VALUE;
            return Math.max(root.val, Math.max(max(root.left), max(root.right)));
        }

        // Purpose: Find Minimum Value in Tree
        // Input: TreeNode root
        // Output: int
        public static int min(TreeNode root) {
            if (root == null) return Integer.MAX_VALUE;
            return Math.min(root.val, Math.min(min(root.left), min(root.right)));
        }
    }

// String Utilities
static class Str {

    // Purpose: Reverse a String
    // Input: String
    // Output: String
    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // Purpose: Check if a String is Palindrome
    // Input: String
    // Output: boolean
    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    // Purpose: Character Frequency
    // Input: String
    // Output: HashMap<Character,Integer>
    public static HashMap<Character, Integer> freq(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        return map;
    }

    // Purpose: Count Vowels
    // Input: String
    // Output: int
    public static int vowels(String s) {
        int cnt = 0;

        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1)
                cnt++;
        }

        return cnt;
    }

    // Purpose: Count Consonants
    // Input: String
    // Output: int
    public static int consonants(String s) {
        int cnt = 0;

        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetter(c) && "aeiou".indexOf(c) == -1)
                cnt++;
        }

        return cnt;
    }
     // Purpose: Compute Longest Prefix Suffix (LPS) Array
        // Input: String pattern
        // Output: int[]
        public static int[] lps(String pat) {
            int n = pat.length();
            int[] lps = new int[n];

            int len = 0;
            int i = 1;

            while (i < n) {
                if (pat.charAt(i) == pat.charAt(len)) {
                    len++;
                    lps[i] = len;
                    i++;
                } else {
                    if (len != 0)
                        len = lps[len - 1];
                    else
                        i++;
                }
            }

            return lps;
        }
        // Purpose: Sort characters in a string
    // Input: String
    // Output: String
    public static String sort(String s) {
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }

    // Purpose: Remove all spaces from a string
    // Input: String
    // Output: String
    public static String removeSpaces(String s) {
        return s.replace(" ", "");
    }

    // Purpose: Reverse the order of words
    // Input: String
    // Output: String
    public static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0)
                sb.append(" ");
        }

        return sb.toString();
    }

    // Purpose: Toggle case of every alphabet
    // Input: String
    // Output: String
    public static String toggleCase(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c))
                sb.append(Character.toLowerCase(c));
            else if (Character.isLowerCase(c))
                sb.append(Character.toUpperCase(c));
            else
                sb.append(c);
        }

        return sb.toString();
    }

    // Purpose: Convert string to uppercase
    // Input: String
    // Output: String
    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    // Purpose: Convert string to lowercase
    // Input: String
    // Output: String
    public static String toLower(String s) {
        return s.toLowerCase();
    }

    // Purpose: Check whether two strings are anagrams
    // Input: String first, String second
    // Output: boolean
    public static boolean isAnagram(String a, String b) {
        a = a.replaceAll("\\s+", "").toLowerCase();
        b = b.replaceAll("\\s+", "").toLowerCase();

        if (a.length() != b.length())
            return false;

        char[] x = a.toCharArray();
        char[] y = b.toCharArray();

        Arrays.sort(x);
        Arrays.sort(y);

        return Arrays.equals(x, y);
    }

    // Purpose: Count number of words in a string
    // Input: String
    // Output: int
    public static int countWords(String s) {
        s = s.trim();

        if (s.isEmpty())
            return 0;

        return s.split("\\s+").length;
    }
}
public static void main(String[] args) {

        IO in = new IO();
        PrintWriter out = new PrintWriter(System.out);

        // CALL SYNTAX: Gen Class
        // int[] arr = Gen.iArr(size, minVal, maxVal);
        // String str = Gen.str(length);

        // CALL SYNTAX: IO Class
        // int n = IO.i();
        // long val = IO.l();
        // String s = IO.next();
        // String line = IO.line();
        // int[] arr = IO.iArr(size);
        // long[] lArr = IO.lArr(size);
        // String[] sArr = IO.sArr(size);

        out.flush();

    }
}
