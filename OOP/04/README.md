# BigIntegerMethods.solve について

この関数はモジュラ逆数を用いて、ある線形合同方程式を解くためのものです。

線形合同方程式 `a * x ≡ b (mod p)` は、次のように理解できます：
整数 x に a を掛けた結果を p で割った余りが b となるような x を求めること。

この関数の解説を行います。

### コードの内容
```java
BigInteger solve(BigInteger a, BigInteger b, BigInteger p) {
    return a.modInverse(p).multiply(b).mod(p);
}
```

### 1. `a.modInverse(p)`
- `modInverse(p)` は、BigInteger のメソッドで、モジュラ逆数を返します。つまり、`a * a^-1 ≡ 1 (mod p)` となるような `a^-1` の値を返します。
- `a^-1` は `a` の逆元とも呼ばれます。

### 2. `.multiply(b)`
- 次に、その逆元と b を掛け合わせます。

### 3. `.mod(p)`
- 最後に、その結果を p で割った余りを取得します。

### 効果
上記の手順は以下の数式変形に相当します：

`a * x ≡ b (mod p)`
`=> x ≡ a^-1 * b (mod p)`

この数式変形を用いることで、与えられた線形合同方程式の x の解を効率的に求めることができます。

### おわりに
コメントに書かれている条件、すなわち `a ≠ 0` および `p` が素数であること、はモジュラ逆数が存在するための必要条件です。これらの条件のもとで、この関数は与えられた線形合同方程式の解 x を効率的に計算します。
