package com.kaminari.realball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final String TITLE = "Ball";

	private Thread thread;
	private JFrame frame;
	private Ball b;
	private Platform p, p1;
	private Input input;

	private boolean running = false;

	public Display() {
		frame = new JFrame(TITLE);
		b = new Ball();
		p = new Platform(100, 300, 150, 20);
		p1 = new Platform(500, 200, 150, 20);
		input = new Input();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.CYAN);
		
		addKeyListener(input);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		requestFocus();
		
		while (running) {
			b.update(this);
			p.update(this, b);
			p1.update(this, b);
			input.update();
			
			if(input.left)
				b.moveLeft();
			if(input.right)
				b.moveRight();
			if(input.jump)
				b.jump();
			
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		b.paintBall(g);
		p.paintPlatform(g);
		p1.paintPlatform(g);
	}

	public static void main(String[] args) {
		Display game = new Display();
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setResizable(false);
		game.frame.setVisible(true);

		game.start();
	}

}
