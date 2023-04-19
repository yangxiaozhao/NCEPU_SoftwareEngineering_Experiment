package cn.edu.ncepu.tankgame.Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Bullet extends Element{
	
	public int speed;
	public Tank tank;
	public double damage=100.0;

	public Bullet(Tank tank,int speed) {
		this.tank = tank;
        this.x = tank.x;
        this.y = tank.y;
        this.height=10;
        this.width=10;
		this.speed=speed;
		this.direction=tank.direction;
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		BufferedImage img=ImageCache.get("bullet");
	    Graphics2D g = (Graphics2D) g2.create();
	    g.translate(x, y);
	    g.setColor(new Color(0.5f,0.7f,0.8f));
	    g.rotate(Math.toRadians(direction));
	    g.scale(1.7,1.7); // 缩小尺寸
	    g.drawImage(img, -9, -9, null);
	}

    public void update() {
        //计算新坐标
        this.move(direction,speed);
        //超出范围的无效子弹
        if (x < 0 || x > 1000 || y < 0 || y > 1000) {
            this.death();
        }
    }

	@Override
	public void move(double dir,int len) {
		// TODO Auto-generated method stub
		if(dir==0.0) {
			y-=speed;
		}
		if(dir==90.0) {
			x+=speed;
		}
		if(dir==180.0) {
			y+=speed;
		}
		if(dir==270.0) {
			x-=speed;
		}
	}
}
