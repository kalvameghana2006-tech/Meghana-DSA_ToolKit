# Java Competitive Programming Template

Java template containing commonly used algorithms, data structures, and helper functions for faster implementation during coding contests.

---

# Classes

## `IO` — Fast Input Utilities
**Purpose:** Read input efficiently with minimal boilerplate.

| Function | Purpose |
|----------|---------|
| `next()` | Read the next space-separated token |
| `i()` | Read an integer |
| `l()` | Read a long |
| `line()` | Read an entire line |
| `iArr(n)` | Read an integer array of size `n` |
| `lArr(n)` | Read a long array of size `n` |
| `sArr(n)` | Read a string array of size `n` |

---

## `Mod` — Modular Arithmetic
**Purpose:** Perform arithmetic operations under modulo `1e9+7`.

| Function | Purpose |
|----------|---------|
| `add(a,b)` | Compute `(a + b) % MOD` |
| `sub(a,b)` | Compute `(a - b) % MOD` |
| `mul(a,b)` | Compute `(a × b) % MOD` |
| `inv(a)` | Find modular multiplicative inverse |
| `div(a,b)` | Compute `(a / b) % MOD` |

---

## `Mth` — Mathematical Utilities
**Purpose:** Frequently used mathematical and number theory algorithms.

| Function | Purpose |
|----------|---------|
| `gcd(a,b)` | Greatest Common Divisor |
| `lcm(a,b)` | Least Common Multiple |
| `pow(b,e,m)` | Fast modular exponentiation |
| `isPrime(n)` | Check if a number is prime |
| `sieve(n)` | Generate prime table using Sieve of Eratosthenes |
| `spf(n)` | Generate Smallest Prime Factor table up to `n` |
| `primeFactors(n,spf)` | Get prime factorization of `n` using SPF table |
| `fact(n,mod)` | Precompute factorials modulo `mod` up to `n` |
| `modInv(a,mod)` | Modular inverse of `a` (mod must be prime) |
| `nCr(n,r,mod,fact)` | Compute `nCr % mod` using precomputed factorials |
| `phi(n)` | Compute Euler's Totient Function of `n` |

---

## `Arr` — Array Utilities
**Purpose:** Binary search and prefix/suffix preprocessing.

| Function | Purpose |
|----------|---------|
| `lb(arr,x)` | First index where value ≥ `x` |
| `ub(arr,x)` | First index where value > `x` |
| `pSum(arr)` | Compute prefix sums |
| `sSum(arr)` | Compute suffix sums |
| `pMin(arr)` | Prefix minimum array |
| `sMin(arr)` | Suffix minimum array |
| `pMax(arr)` | Prefix maximum array |
| `sMax(arr)` | Suffix maximum array |
| `pGcd(arr)` | Prefix GCD array |
| `sGcd(arr)` | Suffix GCD array |

---

## `Stk` — Monotonic Stack
**Purpose:** Solve Next Greater Element type problems.

| Function | Purpose |
|----------|---------|
| `nge(arr)` | Next Greater Element values |
| `ngei(arr)` | Next Greater Element indices |

---

## `Grd` — Grid Algorithms
**Purpose:** Common utilities for grid traversal.

| Function | Purpose |
|----------|---------|
| `ok(r,c,R,C)` | Check whether a cell is inside the grid |
| `dfs(r,c,grid,vis)` | Perform Depth First Search |
| `bfs(r,c,grid,vis)` | Perform Breadth First Search |

---

## `DSU` — Disjoint Set Union
**Purpose:** Maintain connected components efficiently.

| Function | Purpose |
|----------|---------|
| `find(x)` | Find representative of a set |
| `union(a,b)` | Merge two disjoint sets |
| `same(a,b)` | Check if two nodes belong to the same set |

---

## `ST` — Segment Tree
**Purpose:** Efficient range query and point update operations.

| Function | Purpose |
|----------|---------|
| `upd(idx,val)` | Update value at an index |
| `q(l,r)` | Query range sum over `[l, r]` |

---

## `Bit` — Bit Manipulation Utilities
**Purpose:** Common bitwise operations and bitmask helpers.

| Function | Purpose |
|----------|---------|
| `get(n,i)` | Check whether the `i`-th bit is set |
| `set(n,i)` | Set the `i`-th bit to 1 |
| `clear(n,i)` | Clear the `i`-th bit to 0 |
| `toggle(n,i)` | Toggle the `i`-th bit |
| `count(n)` | Count number of set bits |
| `isPow2(n)` | Check whether a number is a power of two |
| `lowBit(n)` | Get value of the lowest set bit |
| `removeLowBit(n)` | Remove the lowest set bit |
| `check(n,i)` | Check whether the `i`-th bit is set (mask-based) |
| `mask(n)` | Generate a bitmask with the lowest `n` bits set |

---

## `Gr` — Graph Utilities (Adjacency List)
**Purpose:** Common graph traversal, ordering, and shortest-path algorithms.

| Function | Purpose |
|----------|---------|
| `dfs(graph,src)` | Depth First Search traversal order from `src` |
| `bfs(graph,src)` | Breadth First Search traversal order from `src` |
| `topoDFS(graph)` | Topological Sort using DFS |
| `topoKahn(graph)` | Topological Sort using Kahn's Algorithm (BFS) |
| `dijkstra(graph,src)` | Shortest distances from `src` using Dijkstra's Algorithm |

---

## `Tree` — Binary Tree Utilities
**Purpose:** Common binary tree traversals and basic operations.

| Function | Purpose |
|----------|---------|
| `TreeNode(val)` | Binary tree node with `left`/`right` children |
| `inorder(root)` | Inorder Traversal (Left → Root → Right) |
| `preorder(root)` | Preorder Traversal (Root → Left → Right) |
| `postorder(root)` | Postorder Traversal (Left → Right → Root) |
| `levelOrder(root)` | Level Order Traversal |
| `height(root)` | Compute height of the tree |
| `countNodes(root)` | Count total number of nodes |
| `countLeaves(root)` | Count number of leaf nodes |
| `max(root)` | Find maximum value in the tree |
| `min(root)` | Find minimum value in the tree |

---

## `Str` — String Utilities
**Purpose:** Frequently used string processing helpers.

| Function | Purpose |
|----------|---------|
| `reverse(s)` | Reverse a string |
| `isPalindrome(s)` | Check if a string is a palindrome |
| `freq(s)` | Compute character frequency map |
| `vowels(s)` | Count vowels |
| `consonants(s)` | Count consonants |
| `lps(pat)` | Compute Longest Prefix Suffix (LPS) array (KMP) |
| `sort(s)` | Sort characters in a string |
| `removeSpaces(s)` | Remove all spaces from a string |
| `reverseWords(s)` | Reverse the order of words |
| `toggleCase(s)` | Toggle case of every alphabet |
| `toUpper(s)` | Convert string to uppercase |
| `toLower(s)` | Convert string to lowercase |
| `isAnagram(a,b)` | Check whether two strings are anagrams |
| `countWords(s)` | Count number of words in a string |

---

## `Gen` — Test Case Generator
**Purpose:** Generate random and edge-case test data.

| Function | Purpose |
|----------|---------|
| `i(min,max)` | Generate random integer |
| `l(min,max)` | Generate random long |
| `iArr(size,min,max)` | Generate random integer array |
| `edgeArr(size)` | Generate edge-case integer array |
| `str(len)` | Generate random lowercase string |

---

## `DBG` — Debug Utilities
**Purpose:** Print debugging information during development.

| Function | Purpose |
|----------|---------|
| `log(name,val)` | Print a variable with its label |
| `grid(name,grid)` | Print a 2D character grid |
| `msg(text)` | Print a custom debug message |

---

# Debug Mode

```java
public static final boolean DEBUG = true;
```

Set `DEBUG = false` before submitting your solution.

---

# Quick Example

```java
int[] a = IO.iArr(n);

long g = Mth.gcd(a[0], a[1]);

int pos = Arr.lb(a, x);

DSU dsu = new DSU(n);
dsu.union(u, v);

ST seg = new ST(longArray);
long sum = seg.q(l, r);

long ans = Mod.mul(x, y);

int[] spf = Mth.spf(100000);
ArrayList<Integer> factors = Mth.primeFactors(a[0], spf);

int[] dist = Gr.dijkstra(adjWeighted, src);

boolean pal = Str.isPalindrome(s);
```
