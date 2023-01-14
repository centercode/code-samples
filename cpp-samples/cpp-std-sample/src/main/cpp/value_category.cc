/*
 * Reference: https://en.cppreference.com/w/cpp/language/value_category
 */
#include <iostream>
#include <string>
using std::string;

class C {
public:
  int m;
  int f();
};
C o, *o_ptr = &o;

/// a glvalue is an expression whose evaluation
/// determines the identity of an object or function
void GeneralizedLValue() {
  // the name of a variable
  std::string str;
  // the name of a function
  void f();
  // a data member
  std::cin;
  // a function call or an overloaded operator expression,
  // whose return type is lvalue reference
  std::getline(std::cin, str);
  std::cout << 1;

  int a, b, *p;
  // built-in assignment and compound assignment expressions
  a = b;
  a += b;
  a %= b;
  // built-in pre-increment and pre-decrement expressions
  ++a;
  --a;
  // built-in indirection expression
  *p;
  // built-in subscript expressions
  p[0];

  int C::*pm = &C::m;
  // member of object expression
  o.m;
  // member of pointer expression
  o_ptr->m;
  // pointer to member of object expression
  o.*pm;
  // pointer to member of pointer expression
  o_ptr->*pm;

  // built-in comma expression, where b is glvalue
  a, b;

  /// conditional expression: E1 ? E2 : E3
  // Either E2 or E3 is a throw-expression.
  // The result of the conditional operator has the type and the value category
  // of the other expression.
  1 + 1 == 2 ? "OK" : throw std::logic_error("1 + 1 != 2");

  /// ...

  /// string literal
  "Hello, world!";
  /// a cast expression to lvalue reference type
  static_cast<int &>(a);

  /// a non-type template parameter of an lvalue reference type

  /// a function call or an overloaded operator expression,
  /// whose return type is rvalue reference to function

  /// a cast expression to rvalue reference to function type
//  static_cast<void (&&)(int)>((a++));
}

/// a prvalue is an expression whose evaluation:
// 1. computes the value of an operand of a built-in operator
// (has no result object), or
// 2. initializes an object (has a result object).
void PureRValue() {
  ///a literal (except for string literal)
  123;
  true;
  nullptr;

  std::string str, str1, str2;
  /// a function call or an overloaded operator expression,
  /// whose return type is non-reference
  str.substr(1, 2);
  str1 + str2;
//  it++;

  int a, b;
  /// the built-in post-increment and post-decrement expressions
  a++;
  a--;

  /// built-in arithmetic expressions
  a + b;
  a % b;
  a &b;
  a << b;

  /// built-in comparison expressions
  a < b;
  a == b;
  a >= b;

  /// the built-in address-of expression
  &a;

  /// the member of object expression,
  /// where m is a member enumerator or a non-static member function

  /// the built-in pointer to member of pointer expression,
  /// where mp is a pointer to member function

  /// the built-in comma expression, where b is an rvalue
  a, 123;

  /// the ternary conditional expression for certain b and c
  1 + 1 == 2 ? throw 123 : throw 456;


  /// a cast expression to non-reference type
  static_cast<double>(a);

  /// the this pointer

  /// an enumerator

  /// a non-type template parameter of a scalar type

  /// a lambda expression
  [](int x) { return x * x; };

  /// a requires-expression
  //  requires(T i) { typename T::type; };
}

/// an xvalue is a glvalue that denotes an object whose resources can be reused
void EXpiringValue() {
  /// the member of object expression,
  /// where a is an rvalue and m is a non-static data member of an object type
  // a.m;

  /// the pointer to member of object expression,
  /// where a is an rvalue and mp is a pointer to data member
  // a.*mp;

  /// the ternary conditional expression for certain b and c

  int a;
  /// a function call or an overloaded operator expression,
  /// whose return type is rvalue reference to object
  std::move(a);

  /// the built-in subscript expression,
  /// where one operand is an array rvalue
//  a[n];

  ///  a cast expression to rvalue reference to object type
  static_cast<char&&>(a);
}

int main() {
  return 0;
}