package cn.edu.ncepu.tankgame.Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tank extends Element {
	public Team team;
	public int steps = 0;
	public int speed;

	public Tank(double x, double y, Team team, double direction, int speed, double hp) {

		super(x, y, direction, hp);
		this.width = 42;
		this.height = 42;
		this.team = team;
		this.speed = speed;
//		this.width = 36;
//		this.height = 36;
//		System.out.println("Tank created at (" + x + ", " + y + ")");
	}

	public void damage(double val) {
		this.hp -= val;
		if (this.hp <= 0) {
			this.death();
		}
	}

	public void update(int steplen) {
		// 若已死亡，则不再动作
		if (destroyed) {
			this.x = 1000;
			this.y = 1000;
		}
		// 更新坦克位置
		if (moving) {
			this.move(direction, steplen);
		}
	}

	@Override
	public void move(double dir, int steplen) {
		if (moving) {
			if (dir == 0.0 && y > 0) {
				y -= steplen;
			}
			if (dir == 90.0 && x <= 900) {
				x += steplen;
			}
			if (dir == 180.0 && y <= 700) {
				y += steplen;
			}
			if (dir == 270.0 && x > 0) {
				x -= steplen;
			}
		}
		steps++;
	}

	@Override
	public void draw(Graphics2D g2) {
		BufferedImage img = null;
		switch (this.team) {
		case PLAYER:
			img = ImageCache.get("player");
			break;
		case ENEMY1:
			img = ImageCache.get("enemy1");
			break;
		case ENEMY2:
			img = ImageCache.get("enemy2");
			break;
		}
		Graphics2D g = (Graphics2D) g2.create();
		g.translate(x, y);
		// g.setColor(new Color(0.5f,0.7f,0.8f));
		g.rotate(Math.toRadians(direction));
		g.scale(0.7, 0.7); // 缩小
		g.drawImage(img, -30, -30, null);
	}

}
