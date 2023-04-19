package cn.edu.ncepu.tankgame.Model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Element {

	public double x;
	public double y;
	public int width;
	public int height;
	public double hp;
	public boolean moving = false;
	public double direction;
	public boolean destroyed = false;

	public Element() {

	}

	public Element(double x, double y, double direction, double hp) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.hp = hp;
	}

	public void death() {
		this.destroyed = true;
	}

	public Rectangle getRect() {
		return new Rectangle
		((int) (x - (width / 2)), (int) y - (height / 2), (int) width, (int) height);
	}

	public abstract void move(double dir, int len);

	public abstract void draw(Graphics2D g);



}
