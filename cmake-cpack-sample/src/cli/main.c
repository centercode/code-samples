#include "greet.h"
int main(int args, char ** argv) {
    if(args> 1) {
        greet(argv[1]);
    }else{
        greet("World");
    }
    return 0;
}