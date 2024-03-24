#include <iostream>
using std::cout;
using std::endl;

void IntegerLiteral() {
  cout << "sizeof(int)=" << sizeof(int) << endl;
  cout << "sizeof(long int)=" << sizeof(long int) << endl;
  cout << "sizeof(long long int)=" << sizeof(long long int) << endl;

  /// decimal-literal no suffix
  auto di4 = 9223372036854775808;   //__int128_t
  auto di3 = 9223372036854775807;   // long
  auto di2 = 2147483648;            // long
  auto di1 = 2147483647;            // int
  auto di0 = 0;                     // int
  auto di_1 = -2147483647;          // int
  auto di_2 = -2147483648;          // long
  auto di_3 = -9223372036854775807; // long
  auto di_4 = -9223372036854775808; //__int128_t

  /// binary-literal no suffix
  auto bi7 = 0b1111111111111111111111111111111111111111111111111111111111111111;   // unsigned long
  auto bi6 = 0b1000000000000000000000000000000000000000000000000000000000000000;   // unsigned long
  auto bi5 = 0b111111111111111111111111111111111111111111111111111111111111111;    // long
  auto bi4 = 0b100000000000000000000000000000000;                                  // long
  auto bi3 = 0b11111111111111111111111111111111;                                   // unsigned int
  auto bi2 = 0b10000000000000000000000000000000;                                   // unsigned int
  auto bi1 = 0b1111111111111111111111111111111;                                    // int
  auto bi0 = 0b0;                                                                  // int
  auto bi_1 = -0b1111111111111111111111111111111;                                  // int
  auto bi_2 = -0b10000000000000000000000000000000;                                 // int
  auto bi_3 = -0b11111111111111111111111111111111;                                 // int
  auto bi_4 = -0b100000000000000000000000000000000;                                // long
  auto bi_5 = -0b111111111111111111111111111111111111111111111111111111111111111;  // long
  auto bi_6 = -0b1000000000000000000000000000000000000000000000000000000000000000; // long
  auto bi_7 = -0b1111111111111111111111111111111111111111111111111111111111111111; // long

  /// octal-literal no suffix
  auto oi7 = 01777777777777777777777;   // unsigned long
  auto oi6 = 01000000000000000000000;   // unsigned long
  auto oi5 = 0777777777777777777777;    // long
  auto oi4 = 040000000000;              // long
  auto oi3 = 037777777777;              // unsigned int
  auto oi2 = 020000000000;              // unsigned int
  auto oi1 = 017777777777;              // int
  auto oi0 = 00;                        // int
  auto oi_1 = -017777777777;            // int
  auto oi_2 = -020000000000;            // int
  auto oi_3 = -037777777777;            // int
  auto oi_4 = -040000000000;            // long
  auto oi_5 = -0777777777777777777777;  // long
  auto oi_6 = -01000000000000000000000; // long
  auto oi_7 = -01777777777777777777777; // long

  /// hexadecimal-literal no suffix
  auto hi7 = 0xFFFFFFFFFFFFFFFF;   // unsigned long
  auto hi6 = 0x8000000000000000;   // unsigned long
  auto hi5 = 0x7FFFFFFFFFFFFFFF;   // long
  auto hi4 = 0x100000000;          // long
  auto hi3 = 0xFFFFFFFF;           // unsigned int
  auto hi2 = 0x80000000;           // unsigned int
  auto hi1 = 0x7FFFFFFF;           // int
  auto hi0 = 0x0;                  // int
  auto hi_1 = -0x7FFFFFFF;         // int
  auto hi_2 = -0x80000000;         // int
  auto hi_3 = -0xFFFFFFFF;         // int
  auto hi_4 = -0x100000000;        // long
  auto hi_5 = -0x7FFFFFFFFFFFFFFF; // long
  auto hi_6 = -0x8000000000000000; // long
  auto hi_7 = -0xFFFFFFFFFFFFFFFF; // long

  auto diu = 123U;
  auto dil = 123L;
  auto diul = 123UL;
  auto dill = 123LL;
  auto diull = 123ULL;
}

int main() {
  IntegerLiteral();
  return 0;
}