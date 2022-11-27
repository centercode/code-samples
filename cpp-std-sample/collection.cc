#include <iostream>
#include <map>

void Tuple() {
  auto t = std::make_tuple(1, "Foo", 3.14);
  // index-based access
  std::cout << "(" << std::get<0>(t) << ", " << std::get<1>(t) << ", "
            << std::get<2>(t) << ")\n";
  // type-based access (C++14 or later)
  std::cout << "(" << std::get<int>(t) << ", " << std::get<const char *>(t)
            << ", " << std::get<double>(t) << ")\n";

  int f1;
  std::string f2;
  std::tie(f1, f2, std::ignore) = t;
}

/// https://stackoverflow.com/questions/26281979/c-loop-through-map
void IterateMap(std::map<int, int> map) {
  // 1. iterator:
  std::map<int, int>::iterator it;
  for (it = map.begin(); it != map.end(); it++) {
    auto key = it->first;
    auto value = it->second;
    std::cout << key << "=" << value << std::endl;
  }

  // 2. C++11
  for (auto const &entry : map) {
    auto key = entry.first;
    auto value = entry.second;
    std::cout << key << "=" << value << std::endl;
  }

  // 3. C++ 17
  for (auto const &[key, value] : map) {
    std::cout << key << "=" << value << std::endl;
  }
}

int main() { return 0; }