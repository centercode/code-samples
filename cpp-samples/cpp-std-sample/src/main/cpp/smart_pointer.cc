#include <iosfwd>
#include <iostream>
#include <memory>
#include <vector>

using std::cout;
using std::endl;
using std::make_shared;
using std::make_unique;
using std::shared_ptr;
using std::string;
using std::unique_ptr;
using std::vector;
using std::weak_ptr;

class Numeric {
  int i;

public:
  explicit Numeric(int i) : i(i) {}
  ~Numeric() { cout << "~destroy Numeric[" << i << "]" << endl; }
  int print() const { cout << "this is Numeric[" << i << "]" << endl; }
  int index() const { return i; }
};

void PrintIndex(const Numeric &s) {
  cout << "Numeric index=" << s.index() << endl;
}

void PrintIndex(const Numeric *s) {
  cout << "Numeric index=" << s->index() << endl;
}

void UniquePtr1() {
  cout << ">>> UniquePtr1:" << endl;
  // initialized with nullptr
  unique_ptr<Numeric> p0;
  cout << "p0 is null:" << std::boolalpha << (p0 == nullptr) << endl;
  unique_ptr<Numeric> p1(new Numeric(123));
  // '->' is overloaded
  p1->print();
  // '*' is overloaded
  PrintIndex(*p1);
  // use raw pointer
  PrintIndex(p1.get());
  // copy constructor is deleted
  // unique_ptr<Numeric> p2 = p1;
  unique_ptr<Numeric> p2(new Numeric(456));
  p2.reset();
  cout << "p2 was reset" << endl;
} // p1 is deleted automatically when function block goes out of scope.

void UniquePtr2() {
  cout << ">>> UniquePtr2:" << endl;
  vector<unique_ptr<Numeric>> vectors;
  vectors.push_back(make_unique<Numeric>(1));
  vectors.push_back(make_unique<Numeric>(2));
  vectors.push_back(make_unique<Numeric>(3));

  for (const auto &v : vectors) {
    v->print();
  }
}

void SharedPtr1() {
  cout << ">>> SharedPtr1:" << endl;
  // initialized with nullptr
  shared_ptr<Numeric> p1;
  cout << "p1 is null:" << std::boolalpha << (p1 == nullptr) << endl;
  shared_ptr<Numeric> p2(nullptr);
  cout << "p2 is null:" << std::boolalpha << (p2 == nullptr) << endl;

  shared_ptr<Numeric> p3 = std::make_shared<Numeric>(1);
  // pass by value
  shared_ptr<Numeric> p4(p3);
  // pass by value
  shared_ptr<Numeric> p5 = p4;
  p4->print();
  p1.swap(p5);
  p1->print();
  cout << "p5 is null:" << std::boolalpha << (p5 == nullptr) << endl;
}

void SharedPtr2() {
  cout << ">>> SharedPtr2:" << endl;
  shared_ptr<Numeric> p1 = std::make_shared<Numeric>(1);
  shared_ptr<Numeric> dpc = std::dynamic_pointer_cast<Numeric>(p1);
  cout << "dynamic cast success:" << (dpc != nullptr) << endl;
  shared_ptr<Numeric> spc = std::static_pointer_cast<Numeric>(p1);
}

void SharedPtr3() {
  cout << ">>> SharedPtr3:" << endl;
  shared_ptr<Numeric> s1(new Numeric(1));
  shared_ptr<Numeric> s2(s1);
  cout << "s1==s2:" << std::boolalpha << (s1 == s2) << endl;
}

void SharedPtr4() {
  cout << ">>> SharedPtr4:" << endl;
  shared_ptr<Numeric> p1(new Numeric(1));
  weak_ptr<Numeric> p2(p1);
  cout << "p2.expired()=" << std::boolalpha << p2.expired() << endl;
  // execute 'expired() ? shared_ptr<T>() : shared_ptr<T>(*this)' atomically.
  shared_ptr<Numeric> p3 = p2.lock();
  p1.reset();
  p3.reset();
  cout << "p2.expired()=" << std::boolalpha << p2.expired() << endl;
}

int main() {
  UniquePtr1();
  UniquePtr2();
  SharedPtr1();
  SharedPtr2();
  SharedPtr3();
  SharedPtr4();
  return 0;
}