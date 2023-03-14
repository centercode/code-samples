#include <iostream>
using std::boolalpha;
using std::cout;
using std::endl;

class DefaultedCtorClass {
public:
  int val;
  //  DefaultedCtorClass() = delete;
};

class UserProvidedCtorClass {
public:
  int val;
  UserProvidedCtorClass(){};
};

void DefaultInitialization() {
  cout << ">>> DefaultInitialization:" << endl;
  /// non-class, the value is indeterminate
  int n;
  cout << "n=" << n << endl;
  int nums[3];
  for (auto &num : nums) {
    cout << "num=" << num << endl;
  }

  /// class, calls implicit default ctor
  DefaultedCtorClass c1;
  DefaultedCtorClass *c2 = new DefaultedCtorClass;
  std::cout << "c1.val=" << c1.val << std::endl;
  std::cout << "c2->val=" << c2->val << std::endl;

  ///  every element of the array is default-initialized
  DefaultedCtorClass objects[3];
  cout << "Array Values:" << endl;
  for (auto &obj : objects) {
    std::cout << "element=" << obj.val << std::endl;
  }

  /// error: a const non-class
  // const int n2;

  /// error: const class with implicit default ctor
  // const DefaultedCtorClass d;

  /// a const-default-constructible class type
  const UserProvidedCtorClass u1;
  const UserProvidedCtorClass *u2 = new UserProvidedCtorClass;
  cout << "u1.val=" << u1.val << endl;
  cout << "u2->val=" << u2->val << endl;
}

void ZeroInitialization() {
  cout << ">>> ZeroInitialization:" << endl;
  //  every named variable with static or thread-local (since C++11) storage
  //  duration that is not subject to constant initialization, before any other
  //  initialization.
  static int *sp;
  cout << "sp == nullptr: " << std::boolalpha << (sp == nullptr) << endl;

  static int ints[3];
  cout << "Array value: " << ints[0] << ", " << ints[1] << ", " << ints[2]
       << endl;

  // the third char is initialized to '\0'
  char carr[5] = "abc";
  for (int i = 0; i < 5; i++) {
    cout << "carr[" << i << "]==0: " << boolalpha << (carr[i] == 0) << endl;
  }

  double d{};
  cout << "d=" << d << endl;

  int *p{};
  cout << "p == nullptr: " << boolalpha << (p == nullptr) << endl;

  // DefaultedCtorClass c = {};
  // DefaultedCtorClass c = DefaultedCtorClass{};
  DefaultedCtorClass c{};
  cout << "c.val=" << c.val << endl;
}

int main() {
  DefaultInitialization();
  ZeroInitialization();
  return 0;
}