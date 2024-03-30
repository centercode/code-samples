#include <algorithm>
#include <iostream>
#include <sstream>
#include <cassert>

using std::boolalpha;
using std::cout;
using std::endl;
using std::string;
using std::stringstream;

namespace std_string_sample {

void BuildString() {
  stringstream ss;
  ss << "hello";
  ss << " ";
  ss << "世界";
  string s = ss.str();
  assert(ss.str() == "hello 世界");
}

void CompareString() {
  string s1{"abc"};
  string s2{"ABC"};
  string s3{"abcd"};
  string s4{"def"};
  assert(s1.compare(s2) > 0);
  assert(s1.compare(s3) < 0);
  assert(s1.compare(s4) < 0);
}

void ConvertStringCase() {
  string input{"Hello, 世界"};
  // to lowercase in place
  auto result = std::transform(input.begin(), input.end(), input.begin(), ::tolower);
  assert(result == input.end());
  assert(input == "hello, 世界");
  // to uppercase copy
  string output;
  output.resize(input.size());
  std::transform(input.begin(), input.end(), output.begin(), ::toupper);
  assert(output == "HELLO, 世界");
}
void Front() {
  string str{"hello"};
  assert(str.front() == 'h');
  if (!str.empty()) {
    str.front() = 'H';
    assert(str == "Hello");
  }
}

void FindString() {
  std::string str{"The sixth sick sheik's sixth sheep's sick."};
  string prefix{"The sixth"};
  string iprefix{"the sixth"};
  std::string sixth{"sixth"};
  std::string sick{"sick"};
  std::string alpha{"abcde"};

  /// find_first_of: Search the first character that matches any of the characters
  assert(str.find_first_of(alpha) == 2);
  assert(str.find_first_of("abcde") == 2);
  assert(str.find_first_of(alpha, 3) == 12);
  // search for individual char
  assert(str.find_first_of('e') == 2);

  /// find_first_not_of: Search the first character that doesn't matches any of the characters
  assert(str.find_first_not_of(alpha) == 0);

  /// find_last_of: Search the last character that matches any of the characters
  assert(str.find_last_of(alpha) == 39);
  /// find_last_not_of: Search the last character that  doesn't matches any of the characters
  assert(str.find_last_not_of(alpha) == 41);

  /// find: Search the first occurrence of the sequence
  assert(str.find(sixth) == 4);
  assert(str.find(sixth) != string::npos);
  assert(str.find(sixth, 4) != string::npos);
  // If 'pos' greater than the string length, the function never finds matches.
  assert(str.find(sixth, str.length()) == string::npos);
  // search for individual char
  assert(str.find('e') == 2);
  assert(str.find('e', 2) == 2);

  /// rfind: Search the last occurrence of sequence
  assert(str.rfind(sixth) == 23);
  assert(str.rfind(sixth) != string::npos);
  // '4' is the first position that match 'sixth'
  assert(str.rfind(sixth, 4) != string::npos);
  // no matched beginning found until the 4th character
  assert(str.rfind(sick, 4) == string::npos);
  // only match the first 5 chars
  assert(str.rfind("sixths", string::npos, 5) != string::npos);
  // search for individual char
  assert(str.rfind('e') == 32);

  /// start with
  assert(prefix.length() <= str.length() && str.substr(0, prefix.length()) == prefix);

  /// start with ignore case
  std::function icmp = [](char a, char b) { return toupper(a) == toupper(b); };
  assert(prefix.length() <= str.length() && std::equal(iprefix.begin(), iprefix.end(), str.begin(), icmp));
}

void ReverseString() {
  string str{"abc"};
  string reversed(str.rbegin(), str.rend());
  assert(reversed == "cba");
}

void SubString() {
  string str{"abc"};
  assert(str.substr(1) == "bc");
  assert(str.substr(3).empty());
}

void TypeConversion() {
  string str = "13.14 1024";
  size_t idx;

  /// string to others
  cout << "int=" << std::stoi(str, &idx, 10) << ", idx=" << idx << endl;
  cout << "long=" << std::stol(str, &idx, 10) << ", idx=" << idx << endl;
  cout << "long long=" << std::stoll(str, &idx, 10) << ", idx=" << idx << endl;
  cout << "unsigned long=" << std::stoul(str, &idx, 10) << ", idx=" << idx << endl;
  cout << "unsigned long long=" << std::stoull(str, &idx, 10) << ", idx=" << idx << endl;
  cout << "float=" << std::stof(str, &idx) << ", idx=" << idx << endl;
  cout << "double=" << std::stod(str, &idx) << ", idx=" << idx << endl;
  cout << "long double=" << std::stold(str, &idx) << ", idx=" << idx << endl;

  /// others to string
  float f_val = 123.0;
  double d_val = 123.0;
  long double ld_val = 123.0;
  assert("123" == std::to_string(123));
  assert("123" == std::to_string(123U));
  assert("123" == std::to_string(123L));
  assert("123" == std::to_string(123UL));
  assert("123" == std::to_string(123LL));
  assert("123" == std::to_string(123ULL));
  assert("123.000000" == std::to_string(f_val));
  assert("123.000000" == std::to_string(d_val));
  assert("123.000000" == std::to_string(ld_val));
  assert("-123" == std::to_string(-123));
  assert("-123" == std::to_string(-123L));
  assert("-123" == std::to_string(-123LL));
  assert("-123.000000" == std::to_string(-f_val));
  assert("-123.000000" == std::to_string(-d_val));
  assert("-123.000000" == std::to_string(-ld_val));
}
} // namespace std_string_sample

int main() {
  std_string_sample::BuildString();
  std_string_sample::CompareString();
  std_string_sample::ConvertStringCase();
  std_string_sample::FindString();
  std_string_sample::Front();
  std_string_sample::ReverseString();
  std_string_sample::SubString();
  std_string_sample::TypeConversion();
  return 0;
}