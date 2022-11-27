class BaseClass {
public:
  void PublicFunc();

protected:
  void ProtectedFunc();

private:
  void PrivateFunc();
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
  DerivedClass1 class1;
  DerivedClass2 class2;
  class1.PublicFunc();
  // class1.ProtectedFunc() is inaccessible
  // class2.PublicFunc() is inaccessible
  // class2.ProtectedFunc() is inaccessible
  // class2.PrivateFunc() is inaccessible
}