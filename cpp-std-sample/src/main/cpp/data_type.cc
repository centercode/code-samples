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

void TypeConversion() {
  // cast int to string
  std::string s0 = std::to_string(123);
  double d = static_cast<int64_t>(123L);
}

int main() {
  CharType();
  return 0;
}