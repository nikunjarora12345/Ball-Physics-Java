package com.kaminari.realball;

import java.awt.Color;
import java.awt.Graphics;

public class Platform {

	private int x;
	private int y;
	private int width;
	private int height;

	public Platform(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update(Display d, Ball b) {
		if (b.getY() + b.getRadius() >= y
				&& b.getY() + b.getRadius() < y + height) {
			if (b.getX() + b.getRadius() >= x
					&& b.getX() - b.getRadius() < x + width) {
				b.setY(y - b.getRadius());
				b.setDy(b.getDy() * -1);
			}
		}
	}

	public void paintPlatform(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
