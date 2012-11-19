#include <stdio.h>
#include "graphics.h"

void drawLine(int x1, int x2, int x3, int x4)
{
  printf("DL %i %i %i %i\n", x1, x2, x3, x4);
}

void drawRect(int x1, int x2, int x3, int x4)
{
  printf("DR %i %i %i %i\n", x1, x2, x3, x4);
}

void drawOval(int x, int y, int width, int height)
{
  printf("DO %i %i %i %i\n",x,y,width,height);
}

void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
{
  printf("DA %i %i %i %i %i %i\n",x,y,width,height, startAngle, arcAngle);
}

void fillRect(int x1, int x2, int x3, int x4)
{
  printf("FR %i %i %i %i\n", x1, x2, x3, x4);
}

void drawString(char* s, int x, int y)
{
  printf("DS %i %i @%s\n",x,y,s);
}

void drawImage(char* s, int x, int y,int width,int height)
{
	printf("DI %i %i %i %i @%s\n",x,y,width,height,s);
}

void setColour(colour c)
{
  char* colourName;
  switch(c)
  {
    case black : colourName = "black"; break;
    case blue : colourName = "blue"; break;
    case cyan : colourName = "cyan"; break;
    case darkgray : colourName = "darkgray"; break;
    case gray : colourName = "gray"; break;
    case green : colourName = "green"; break;
    case lightgray : colourName = "lightgray"; break;
    case magenta : colourName = "magenta"; break;
    case orange : colourName = "orange"; break;
    case pink : colourName = "pink"; break;
    case red : colourName = "red"; break;
    case white : colourName = "white"; break;
    case yellow : colourName = "yellow"; break;
  }
  printf("SC %s\n", colourName);
}

void setGradient(colour cStart,colour cEnd)
{
	char* colourStart;
	char* colourEnd;
	switch(cStart)
	{
		case black : colourStart = "black"; break;
		case blue : colourStart= "blue"; break;
		case cyan : colourStart = "cyan"; break;
		case darkgray : colourStart = "darkgray"; break;
		case gray : colourStart = "gray"; break;
		case green : colourStart = "green"; break;
		case lightgray : colourStart = "lightgray"; break;
		case magenta : colourStart = "magenta"; break;
		case orange : colourStart = "orange"; break;
		case pink : colourStart = "pink"; break;
		case red : colourStart = "red"; break;
		case white : colourStart = "white"; break;
		case yellow : colourStart = "yellow"; break;	
	}
	switch(cEnd)
	{
		case black : colourEnd = "black"; break;
		case blue : colourEnd= "blue"; break;
		case cyan : colourEnd = "cyan"; break;
		case darkgray : colourEnd = "darkgray"; break;
		case gray : colourEnd = "gray"; break;
		case green : colourEnd = "green"; break;
		case lightgray : colourEnd = "lightgray"; break;
		case magenta : colourEnd = "magenta"; break;
		case orange : colourEnd = "orange"; break;
		case pink : colourEnd = "pink"; break;
		case red : colourEnd = "red"; break;
		case white : colourEnd = "white"; break;
		case yellow : colourEnd = "yellow"; break;	
	}
	printf("SG @%s !%s\n", colourStart,colourEnd);
}

void setBlur()
{
	printf("SB\n");
}

void setReflection()
{
	printf("SR\n");
}

void setDropShadow()
{
	printf("SS\n");
}

void setDimension(int width, int height){
	printf("SD %i %i\n", width,height);
}
void startTurtle(int x,int y, int r){
	printf("ST %i %i %i\n", x,y,r);
}
void turtleForward(int d){
	printf("FT %i\n", d);
}
void turtleLeft(int r){
	printf("TL %i\n",r);
}
void turtleRight(int r){
	printf("TR %i\n", r);
}
void penUp(){
	printf("PU\n");
}
void penDown(int x,int y, int r){
	printf("PD %i %i %i\n", x,y,r);
}