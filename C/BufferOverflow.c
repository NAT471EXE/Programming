// Buffer overflow with adjacent memory on the stack overwritten
//     Output:
//         Data: abcdef
//         Enter your name: NatalieQwerty
//         Data: ieQwerty
//         Hello, NatalieQwerty!
//         *** stack smashing detected ***: terminated

#include <stdio.h>
#include <stdlib.h>

int main()
{
    char data[] = "abcdef";
    printf("Data: %s\n", data);
    char name[5];
    printf("Enter your name: ");
    // scanf("%4s", name);    // prevent buffer overflow by setting size limit
    scanf("%s", name);
    printf("Data: %s\n", data);
    printf("Hello, %s!\n", name);
    return 0;
}
