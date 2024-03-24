#include <iostream>
void UnscopedEnumerations() {
  /// Declares an unscoped enumeration type whose underlying type is not fixed
  enum Color1 { red1, green1, blue1 };
  int m = red1;
  int n = Color1::red1;
  Color1 color = static_cast<Color1>(7);

  /// Declares an unscoped enumeration type whose underlying type is fixed
  enum Color2 : char { red2, green2, blue2 };

  /// Opaque enum declaration for an unscoped enumeration
  /// must specify the name and the underlying type.
  enum Color3 : char;

  /// When an unscoped enumeration is a class member,
  /// its enumerators may be accessed using class member access operators
  struct X {
    enum direction { left = 'l', right = 'r' };
  };
  X x;
  X *p = &x;
  int xa = X::direction::left; // allowed only in C++11 and later
  int xb = X::left;
  int xc = x.left;
  int xd = p->left;

  /// If the first enumerator does not have an initializer,
  /// the associated value is zero.
  /// For any other enumerator whose definition does not have an initializer,
  /// the associated value is the value of the previous enumerator plus one.
  enum Foo { a, b, c = 10, d, e = 1, f, g = f + c };
  // a = 0, b = 1, c = 10, d = 11, e = 1, f = 2, g = 12
}

void ScopedEnumerations() {
  /// declares a scoped enumeration type whose underlying type is int
  enum class Color1 { red1, green1 = 20, blue1 };

  /// declares a scoped enumeration type whose underlying type is type
  enum class Color2 : unsigned int { red2, green2 = 20, blue2 };

  /// opaque enum declaration for a scoped enumeration
  /// whose underlying type is int
  enum class Color3;

  /// opaque enum declaration for a scoped enumeration
  /// whose underlying type is type
  enum class Color4 : unsigned int;

  /// no implicit conversions from the values of a scoped enumerator
  /// to integral types
  // int m = Color1::red1;
  int n = static_cast<int>(Color1::red1);
}