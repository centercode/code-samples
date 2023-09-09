# 算法学习笔记

## 相关数学公式

### 余数运算公式

余运算相关的公式有：

1. 除法求余公式：a = b * q + r，其中a是被除数，b是除数，q是商，r是余数。
2. 余数的性质：对于任意整数a和正整数b，有a ≡ r (mod b)，其中r是a除以b的余数。
3. 同余定理：如果a ≡ b (mod m)，则对于任意整数c，有a + c ≡ b + c (mod m)，a - c ≡ b - c (mod m)，ac ≡ bc (mod m)。
4. 余数的加法：(a + b) mod m = (a mod m + b mod m) mod m。
5. 余数的乘法：(a * b) mod m = (a mod m * b mod m) mod m。
6. 余数的幂运算：a^b mod m = ((a mod m)^b) mod m。
7. 模反元素：如果a和m互质，那么存在一个整数b，使得ab ≡ 1 (mod m)，b被称为a的模反元素。
8. 费马小定理：如果p是质数，a是整数且不是p的倍数，那么a^(p-1) ≡ 1 (mod p)。

这些公式可以用于求解余数相关的问题，例如模运算、同余方程等。

