#include <iostream>
#include <string>
#include <utility>

void NullptrType() {
  auto p1 = std::make_pair(NULL, 3.14);
  auto p2 = std::make_pair(nullptr, 3.14);
}

void CharType() {
  char ch1{'a'}; // or { u8'a' }
  wchar_t ch2{L'a'};
  char16_t ch3{u'a'};
  char32_t ch4{U'a'};
}

void ArithmeticType() {
  /// floating-point types and their cv-qualified versions
  /// integral types:
  ///   - the type bool
  ///   - character types
  /// signed integer types
  /// unsigned integer types

  class A {};
  std::cout << ">>> Arithmetic type:" << std::endl
            << std::boolalpha
            << "DefaultedCtorClass:" << std::is_arithmetic_v<A> << '\n'           // false
            << "bool:              " << std::is_arithmetic_v<bool> << '\n'        // true
            << "int:               " << std::is_arithmetic_v<int> << '\n'         // true
            << "int const:         " << std::is_arithmetic_v<int const> << '\n'   // true
            << "int &:             " << std::is_arithmetic_v<int &> << '\n'       // false
            << "int *:             " << std::is_arithmetic_v<int *> << '\n'       // false
            << "float:             " << std::is_arithmetic_v<float> << '\n'       // true
            << "float const:       " << std::is_arithmetic_v<float const> << '\n' // true
            << "float &:           " << std::is_arithmetic_v<float &> << '\n'     // false
            << "float *:           " << std::is_arithmetic_v<float *> << '\n'     // false
            << "char:              " << std::is_arithmetic_v<char> << '\n'        // true
            << "char const:        " << std::is_arithmetic_v<char const> << '\n'  // true
            << "char &:            " << std::is_arithmetic_v<char &> << '\n'      // false
            << "char *:            " << std::is_arithmetic_v<char *> << '\n'      // false
      ;
}

void TypeConversion() {
  // cast int to string
  std::string s0 = std::to_string(123);
  double d = static_cast<int64_t>(123L);
}

int main() {
  ArithmeticType();
  CharType();
  return 0;
}