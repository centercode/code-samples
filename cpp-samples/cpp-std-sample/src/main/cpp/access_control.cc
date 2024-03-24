#include <iostream>

class BaseClass {
public:
  void PublicFunc() { std::cout << "BaseClass::PublicFunc()" << std::endl; }

protected:
  void ProtectedFunc() {
    std::cout << "BaseClass::ProtectedFunc()" << std::endl;
  }

private:
  void PrivateFunc() { std::cout << "BaseClass::PrivateFunc()" << std::endl; }
};

class DerivedClass1 : public BaseClass {
  void foo() {
    PublicFunc();
    ProtectedFunc();
    // PrivateFunc() is inaccessible
  }
};

// Same as 'class DerivedClass2 : private BaseClass'
class DerivedClass2 : BaseClass {
  void foo() {
    PublicFunc();
    ProtectedFunc();
    // PrivateFunc() is inaccessible
  }
};

int main() {
  DerivedClass1 derivedClass1;
  derivedClass1.PublicFunc();
  // derivedClass1.ProtectedFunc() is inaccessible

  DerivedClass2 derivedClass2;
  // derivedClass2.PublicFunc() is inaccessible
  // derivedClass2.ProtectedFunc() is inaccessible
  // derivedClass2.PrivateFunc() is inaccessible
}