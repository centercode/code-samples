#include <iostream>

void MaxIntValue() {
  int a = ~(1 << 31);
  std::cout << "max int value = " << a << std::endl;
}

int main() {
  MaxIntValue();
  return 0;
}