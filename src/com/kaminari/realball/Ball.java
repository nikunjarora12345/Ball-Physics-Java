package com.kaminari.realball;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	private int x = 400;
	private double dx = 0;
	private int y = 25;
	private double dy = 0;
	private int radius = 20;

	private double gravity = 15;
	private double energyLoss = 1;
	private double friction = .95;
	private double dt = .2;

	public Ball() {
	}

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveRight() {
		if (dx < 10)
			dx += 1;
	}

	public void moveLeft() {
		if (dx > -10)
			dx -= 1;
	}

	public void jump() {
		if (dy > -50)
			dy -= 10;
		else {
			dy += gravity * dt;
			y += dy * dt + .5 * gravity * dt * dt;
		}
	}

	public void update(Display d) {
		if (x + dx > d.getWidth() - radius - 1) {
			x = d.getWidth() - radius - 1;
			dx = -dx;
		} else if (x + dx < radius) {
			x = radius;
			dx = -dx;
		} else {
			x += dx;
		}

		if (y == d.getHeight() - radius - 1) {
			dx *= friction;
		}

		if (y + dy > d.getHeight() - radius - 1) {
			y = d.getHeight() - radius - 1;
			dy = -dy;
			dy *= energyLoss;
		} else {
			dy += gravity * dt;
			y += dy * dt + .5 * gravity * dt * dt;
		}
	}

	public void paintBall(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	}

	// Getters and Setters

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
