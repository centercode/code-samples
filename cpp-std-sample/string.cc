#include <iostream>
#include <sstream>

using std::boolalpha;
using std::cout;
using std::endl;
using std::string;
using std::stringstream;

void BuildString() {
  stringstream ss;
  ss << "abc";
  ss << "def";
  string s = ss.str();
  cout << "s = '" << s << "'" << endl;
}

void Compare() {
  string s1 = "abc";
  string s2 = "ABC";
  string s3 = "abcd";
  string s4 = "def";
  cout << "s1.compare(s2) = " << s1.compare(s2) << endl;
  cout << "s1.compare(s3) = " << s1.compare(s3) << endl;
  cout << "s1.compare(s4) = " << s1.compare(s4) << endl;
}

void ReverseString() {
  string s = "abc";
  string reversed(s.rbegin(), s.rend());
  cout << "reversed of '" << s << "' = '" << reversed << "'" << endl;
}

int main() {
  BuildString();
  Compare();
  ReverseString();
  return 0;
}