import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class painting extends PApplet {

public void settings() {
  size(screenWidth, screenHeight);
}

public void setup() {
  background(255);
  noStroke();
  colorMode(HSB, 360, 100, 100, 1);

  for (int i = 0; i < dots.length; i ++ ) {
    // initialise each dot with a random [x,y] position
    dots[i] = new Dot(PApplet.parseInt(random(screenWidth)), PApplet.parseInt(random(screenHeight)));
  }
}

public void draw() {
  for (int i = 0; i < dots.length; i ++ ) {
    dots[i].draw();
  }
}

public void mousePressed() {
  save("painting.png");
}
class Dot {
  PVector pos;
  PVector vel;
  int c;
  float rotate;
  float size;
  float speed;
  boolean righting;

  Dot(int x, int y) {

    // make position vector based on x,y coordinates
    pos = new PVector(x, y);

    // pick a random color
    // take a random pretty hue
    int hue = (hues[PApplet.parseInt(random(0, hues.length-1))])%360;
    float sat = random(30, 50);
    float bri = random(90, 100);
    c = color(hue, sat, bri);

    // pick a random size
    size = random(50, 100);

    // pick random rotation strength
    rotate = random(0.05f);

    // pick a random speed or movement
    speed = random(2, 5);

    // create random velocity vector and set its speed
    vel = PVector.random2D().setMag(speed);

    // randomly chose left/right leaning
    righting = (Math.random() < 0.5f);

  }

  public void update() {

    // randomly change direction of rotation
    if (Math.random() < 0.01f) {
      righting = !righting;
    }

    // rotate left or right based on righting
    if (righting) {
      vel.rotate(rotate);
    } else {
      vel.rotate(-rotate);
    }

    // add the velocity to the vector
    pos.add(vel);

  }

  public void render() {
    fill(c);
    rect(pos.x, pos.y, size, size);
  }

  public void draw() {
    update();
    render();
  }

}
int screenWidth = 1000;
int screenHeight = 1000;
int dotCount = 50;
int[] hues = {2, 10, 17, 37, 40, 63, 67, 72, 74, 148, 152, 156, 160, 170, 175, 189, 194, 260, 270, 280, 288, 302, 320, 330, 340, 350};
Dot[] dots = new Dot[dotCount];
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "painting" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
