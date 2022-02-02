#include "/home/pi/rpi-rgb-led-matrix/include/led-matrix.h"
#include "/home/pi/rpi-rgb-led-matrix/include/threaded-canvas-manipulator.h"
#include "/home/pi/rpi-rgb-led-matrix/include/pixel-mapper.h"
#include "/home/pi/rpi-rgb-led-matrix/include/graphics.h"

#include <iostream>
#include <assert.h>
#include <getopt.h>
#include <limits.h>
#include <math.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <algorithm>

using std::min;
using std::max;
using std::cout;

#define TERM_ERR  "\033[1;31m"
#define TERM_NORM "\033[0m"

using namespace rgb_matrix;

volatile bool interrupt_received = false;
static void InterruptHandler(int signo) {
  interrupt_received = true;
}
class ChessBoard : public ThreadedCanvasManipulator {
public:
  ChessBoard(RGBMatrix *m) : ThreadedCanvasManipulator(m), matrix_(m) {
    off_screen_canvas_ = m->CreateFrameCanvas();
  }
  void Board2(){
    for (int c=0; c<4; c++){
      Row1(0, c*8);
      Row2(0, c*8+4);
    }
  };
      
  void Row2(int xa, int ya){
    for(int b=0; b<4; b++){
      Square(xa + b*8, ya,0,0,255);
      Square((xa+4)+ b*8, ya,255,0,0);
    }
  };  
  void Row1(int xa, int ya){
    for(int b=0; b<4; b++){
      Square(xa + b*8, ya,255,0,0);
      Square((xa+4)+ b*8, ya,0,0,255);
    }
  };  
  void Square(int x, int y, int r, int b, int g){
    for(int a=0; a<4; a++){
      DrawLine(off_screen_canvas_, x+a, y, x+a, y+3, Color(r,b,g));
    }
  };    
  //Creates a board Where the color of the white spaces comes first, then pieces, then the color of the Black spaces, black pieces , Which Color the player be using, and the size of the board
  void Board(int wr=0, int wg=0, int wb=255, int wpr=255, int wpb= 255, int wpg=0, int br=255, int bg=0, int bb=0, int bpr=0, int bpb=0, int bpg=255, char p='w', int bs=32){
    int size=bs/8;
    char cs=p;
    for(int y=0;y<8;y++){
      for(int x=0;x<8;x++){
        if(cs=='w'){
          for(int xa=0; xa<size; xa++){
            DrawLine(off_screen_canvas_, x*size+xa , y*size, x*size+xa, y*size+size-1, Color(wr,wg,wb));
            }
          cs='b';
          cout<<cs;
          continue;
        }
        if(cs=='b'){
          for(int xa=0; xa<size; xa++){
            DrawLine(off_screen_canvas_, x*size+xa , y*size, x*size+xa, y*size+size-1, Color(br,bg,bb));
            }
          cs='w';
          cout<<cs;
        }
       }   
       cs=(cs=='w')?'b':'w';
      }    
     
    
  };
  
  
  
  
  
  
  
  void Run() {
    while (running() &&!interrupt_received) {
      
      Board();
      off_screen_canvas_=matrix_->SwapOnVSync(off_screen_canvas_);
      }
    };
   private:
  RGBMatrix *const matrix_;
  FrameCanvas *off_screen_canvas_; 
  }; 
  
  
  
  int main(int argc, char *argv[]) {
  int runtime_seconds = -1;

  RGBMatrix::Options matrix_options;
  rgb_matrix::RuntimeOptions runtime_opt;

  // These are the defaults when no command-line flags are given.
  matrix_options.rows = 32;
  matrix_options.chain_length = 1;
  matrix_options.parallel = 1;

  runtime_opt.gpio_slowdown=4;

  // First things first: extract the command line flags that contain
  // relevant matrix options.
  


  RGBMatrix *matrix = CreateMatrixFromOptions(matrix_options, runtime_opt);
  if (matrix == NULL)
    return 1;

  printf("Size: %dx%d. Hardware gpio mapping: %s\n",
         matrix->width(), matrix->height(), matrix_options.hardware_mapping);

  Canvas *canvas = matrix;

  // The ThreadedCanvasManipulator objects are filling
  // the matrix continuously.
 
  ThreadedCanvasManipulator *image_gen = NULL;
  image_gen= new ChessBoard(matrix);



  // Set up an interrupt handler to be able to stop animations while they go
  // on. Note, each demo tests for while (running() && !interrupt_received) {},
  // so they exit as soon as they get a signal.
  signal (SIGTERM, InterruptHandler);
  signal (SIGINT, InterruptHandler);
  
  // Image generating demo is crated. Now start the thread.
  image_gen->Start();

  // Now, the image generation runs in the background. We can do arbitrary
  // things here in parallel. In this demo, we're essentially just
  // waiting for one of the conditions to exit.
  if (runtime_seconds > 0) {
    sleep(runtime_seconds);
  } else {
    // The
    printf("Press <CTRL-C> to exit and reset LEDs\n");
    while (!interrupt_received) {
      sleep(1); // Time doesn't really matter. The syscall will be interrupted.
    }
  }

  // Stop image generating thread. The delete triggers
  delete image_gen;
  delete canvas;

  printf("\%s. Exiting.\n",
         interrupt_received ? "Received CTRL-C" : "Timeout reached");
  return 0;
}
