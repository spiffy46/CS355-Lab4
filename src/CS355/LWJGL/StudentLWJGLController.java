package CS355.LWJGL;


//You might notice a lot of imports here.
//You are probably wondering why I didn't just import org.lwjgl.opengl.GL11.*
//Well, I did it as a hint to you.
//OpenGL has a lot of commands, and it can be kind of intimidating.
//This is a list of all the commands I used when I implemented my project.
//Therefore, if a command appears in this list, you probably need it.
//If it doesn't appear in this list, you probably don't.
//Of course, your milage may vary. Don't feel restricted by this list of imports.
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.util.Iterator;

/**
 *
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController 
{
  
  //This is a model of a house.
  //It has a single method that returns an iterator full of Line3Ds.
  //A "Line3D" is a wrapper class around two Point2Ds.
  //It should all be fairly intuitive if you look at those classes.
  //If not, I apologize.
  private WireFrame model = new HouseModel();
  int rotation = 0;
  
  //This method is called to "resize" the viewport to match the screen.
  //When you first start, have it be in perspective mode.
  @Override
  public void resizeGL() 
  {
	  glViewport(0, 0, Display.getWidth(), Display.getHeight());
	  glMatrixMode(GL_PROJECTION);
	  glLoadIdentity();
	  gluPerspective((float)60,(float)(Display.getWidth()/Display.getHeight()),(float)1.5,(float)50);
	  glMatrixMode(GL_MODELVIEW);
	  glTranslatef(0,-5,-20);
  }

    @Override
    public void update() 
    {
        
    }

    //This is called every frame, and should be responsible for keyboard updates.
    //An example keyboard event is captured below.
    //The "Keyboard" static class should contain everything you need to finish
    // this up.
    @Override
    public void updateKeyboard() 
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) 
        {
        	glMatrixMode(GL_MODELVIEW);
        	glTranslatef(-(float)(.5 * Math.sin(rotation*Math.PI/180)),0,(float)(.5 * Math.cos(rotation*Math.PI/180)));
        } else if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
        	glMatrixMode(GL_MODELVIEW);
        	glTranslatef((float)(.5 * Math.sin(rotation*Math.PI/180)),0,-(float)(.5 * Math.cos(rotation*Math.PI/180)));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_A))
        {
        	glMatrixMode(GL_MODELVIEW);
        	glTranslatef((float)(.5 * Math.cos(rotation*Math.PI/180)),0,(float)(.5 * Math.sin(rotation*Math.PI/180)));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {
        	glMatrixMode(GL_MODELVIEW);
        	glTranslatef(-(float)(.5 * Math.cos(rotation*Math.PI/180)),0,-(float)(.5 * Math.sin(rotation*Math.PI/180)));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_E))
        {
        	glMatrixMode(GL_PROJECTION);
        	glRotatef(1,0,1,0);
        	rotation++;
        	if(rotation == 360){
        		rotation = 0;
        	}
        }else if(Keyboard.isKeyDown(Keyboard.KEY_Q))
        {
        	glMatrixMode(GL_PROJECTION);
        	glRotatef(-1,0,1,0);
        	rotation--;
        	if(rotation < 0){
        		rotation = 359;
        	}
        }else if(Keyboard.isKeyDown(Keyboard.KEY_R))
        {
        	glMatrixMode(GL_MODELVIEW);
        	glTranslatef(0,-(float).5,0);
        }else if(Keyboard.isKeyDown(Keyboard.KEY_F))
        {
        	glMatrixMode(GL_MODELVIEW);
        	glTranslatef(0,(float).5,0);
        }else if(Keyboard.isKeyDown(Keyboard.KEY_H))
        {
        	glMatrixMode(GL_PROJECTION);
        	glLoadIdentity();
        	gluPerspective((float)60,(float)(Display.getWidth()/Display.getHeight()),(float)1.5,(float)50);
        	glMatrixMode(GL_MODELVIEW);
        	glLoadIdentity();
        	glTranslatef(0,-5,-20);
        	rotation = 0;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_P))
        {
        	//TODO Needs to be fixed
        	glMatrixMode(GL_PROJECTION);
        	//glLoadIdentity();
        	gluPerspective((float)60,(float)(Display.getWidth()/Display.getHeight()),(float)1.5,(float)50);
        }else if(Keyboard.isKeyDown(Keyboard.KEY_O))
        {
        	//TODO needs to be implemented
        	glMatrixMode(GL_PROJECTION);
        	//glLoadIdentity();
        	glOrtho(-10,10,-10,10,1.5,50);
        }
    }

    //This method is the one that actually draws to the screen.
    @Override
    public void render() 
    {
        //This clears the screen.
        glClear(GL_COLOR_BUFFER_BIT);
        
        //Do your drawing here.
        Iterator<Line3D> i = model.getLines();
        while(i.hasNext()){
        	Line3D temp = i.next();
        	glBegin(GL_LINES);
        	glVertex3d(temp.start.x,temp.start.y,temp.start.z);
        	glVertex3d(temp.end.x,temp.end.y,temp.end.z);
        	glEnd();
        }
    }
    
}
