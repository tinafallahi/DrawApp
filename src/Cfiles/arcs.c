#include "graphics.h"

int main(void)
{
	startTurtle(100,200,0);
	turtleForward(50);
	turtleRight(10);
	turtleForward(50);
	turtleLeft(10);
	turtleForward(50);
	penUp();
	penDown(0,0,90);
	turtleForward(100);
  return 0;
}