package cn.edu.ncepu.tankgame.Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Wall extends Element {
	public int style;  // 1为砖墙，2为铁墙,3为home
	
	
	public Wall(int x,int y,int style) {
		super();
		this.x=x;
		this.y=y;
		this.height=48;
		this.width=48;
		this.style = style;
		switch(style) {
		case 1:
			this.hp=100.0;
			break;
		case 2:
			this.hp=Double.MAX_VALUE;
			break;
		case 3:
			this.hp=150.0;
			break;
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		BufferedImage img = null;
		switch(style) {
		case 1:
			img=ImageCache.get("walls");
			break;
		case 2:
			img=ImageCache.get("steels");
			break;
		case 3:
			if(this.hp==150)
				img=ImageCache.get("home");
			else
				img=ImageCache.get("home_over");
			break;
		}
	    Graphics2D g = (Graphics2D) g2.create();
	    g.translate(x, y);
	    g.setColor(new Color(0.5f,0.7f,0.8f));
	    g.rotate(Math.toRadians(direction));
	    g.scale(0.8, 0.8); // 缩小尺寸
	    g.drawImage(img, -30, -30, null);
	}

	@Override
	public void move(double dir,int len) {
		// TODO Auto-generated method stub
		return;
	}

    public void update() {
        // 若已死亡，则不再动作
        if (destroyed) {
            return;
        }
        
    }
    
    public void damage(double val) {
    	if(this.hp!=50)
    		this.hp -= val;
        if (this.hp <= 0 || this.style==3 && this.hp==50.0) {
            this.death();
        }
    }

}
