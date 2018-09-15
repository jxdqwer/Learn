#include "NumTest.h"

float NumTest::getNumTest(int index){
    float a = 0.01f;
    float b = 0;
    for(int i = 0;i<index;i++){
        b += a;
    }
    return b;
}
