#include <stdio.h>

#define PI 3.14159

int add(int x,int y){
    return x+y;
}
 
int main(void)
{
    int a = 10;
    int b = 4;
    printf("somma: %d", add(a,b));
    printf("pigreco: %f", PI);
    return 0;
}