package com.badlogic.gdx.tests.lwjgl;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.tests.NonContinuousTest;

public class LwjglCanvasTest extends JFrame {
	LwjglAWTCanvas canvas;

	public LwjglCanvasTest () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Canvas Test");
		
		Container container = getContentPane();
		canvas = new LwjglAWTCanvas(new NonContinuousTest(), false);
		
		container.add(canvas.getCanvas(), BorderLayout.CENTER);

		pack();
		setVisible(true);
		setSize(800, 480);
	}

	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run () {
				new LwjglCanvasTest();
			}
		});
	}
}
