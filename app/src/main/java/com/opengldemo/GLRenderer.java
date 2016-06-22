package com.opengldemo;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by AKINDE-PETERS on 3/2/2016.
 */

//NAME: AKINDE-PETERS JOSEPH DARE
//MATRIC NUMBER: 110408007
//DEPARTMENT:	COMPUTER ENGINEERING
//COURSE:	EEG517

public class  GLRenderer implements GLSurfaceView.Renderer {

private Cube cube = new Cube();
    private float mCubeRotation = 0.15f;

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background color to black ( rgba ).
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        // Set the color of the polygon(rgba)
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.5f);
        // Enable Smooth Shading, default not really needed.
//        gl.glShadeModel(GL10.GL_SMOOTH);
        // Depth buffer setup.
        gl.glClearDepthf(1.0f);
        // Enables depth testing.
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    public void onDrawFrame(GL10 gl) {
        // Clears the screen and depth buffer.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // Replace the current matrix with the identity matrix
        gl.glLoadIdentity();
        // Translates 4 units into the screen.
        gl.glTranslatef(0, 0, -4);
        gl.glRotatef(mCubeRotation, 1.0f, 1.0f, 1.0f);

        // Draw our cube.
        cube.draw(gl);

        gl.glLoadIdentity();
        mCubeRotation -= 0.15f;
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height);
        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reset the projection matrix
        gl.glLoadIdentity();
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 80.0f, (float) width / (float) height, 0.1f,
                100.0f);
        gl.glViewport(0, 0, width, height);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // Reset the modelview matrix
        gl.glLoadIdentity();
    }


    public void rotateFaster() {
        mCubeRotation -= 4.15f;
    }
}
