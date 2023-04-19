package cn.edu.ncepu.tankgame.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.edu.ncepu.tankgame.Control.GameControl;

public class WarData {

	static double player_hp = 300;
	static double enemy_hp = 100;
	static int player_speed = 5;
	static int enemy_speed = 2;
	static int enemyNum_Max = 5;
	static int enemyNum = 3;
	static volatile int dead_enemy = 0;

	public static CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
	public static ExecutorService es = Executors.newFixedThreadPool(enemyNum);
	public static CopyOnWriteArrayList<Tank> tanks = new CopyOnWriteArrayList();
	public static CopyOnWriteArrayList<Wall> walllist = new CopyOnWriteArrayList<>();

	public static List<Tank> tempanklist = new ArrayList<>();

	public Tank player = new Tank(344, 640, Team.PLAYER, 0, player_speed, player_hp);

//	public TankThread playthread=new TankThread(player);

	public WarData() {
		tanks.add(player);
		addEnemy(getEnemyNum_Max());
		addWall();
	}

	public boolean update() {
		if (player.destroyed)
			return false;
		// TODO Auto-generated method stub
		for (Tank tank : tanks) {
			if (tank.team == Team.PLAYER) {
				for (Bullet bullet : bullets) {
					if (tank.getRect().intersects(bullet.getRect()) && bullet.tank != tank) {
						tank.damage(bullet.damage);
						bullets.remove(bullet);
					}
				}
				tank.update(player_speed);
				/***
				 * 坦克与墙的碰撞检测
				 ***/
				for (Wall wall : walllist) {
					if (tank.getRect().intersects(wall.getRect()))
						tank.update(-player_speed);
				}
				/***
				 * 坦克与坦克的碰撞检测
				 ***/
				for (Tank t : tanks) {
					if (t != tank && tank.getRect().intersects(t.getRect()))
						tank.update(-player_speed);
				}
			}

			if (tank.team != Team.PLAYER) {
				// 防止跑出地图
				for (Bullet bullet : bullets) {
					if (tank.getRect().intersects(bullet.getRect()) && bullet.tank.team == Team.PLAYER) {
						tank.damage(bullet.damage);
						GameControl.playMusic("src/music/bang.wav");
						bullets.remove(bullet);
					}
				}
				if (tank.x <= 0) {
					tank.direction = 90;
				}
				if (tank.y <= 0) {
					tank.direction = 180;
				}
				if (tank.x >= 900) {
					tank.direction = 270;
				}
				if (tank.y >= 700) {
					tank.direction = 0;
				}
				tank.move(tank.direction, tank.speed);
				// 运动几步随机开炮
				if (tank.steps > 100) {
					// 方向随机
					double random = (new Random().nextInt(4)) * 90;
					tank.direction = random;
					tank.moving = true;
					tank.steps = 0;
//                    Bullet bullet = new Bullet(tank, 8);
//                    bullets.add(bullet);
				}
				tank.update(enemy_speed);
				/***
				 * 坦克与墙的碰撞检测
				 ***/
				for (Wall wall : walllist) {
					if (tank.getRect().intersects(wall.getRect()))
						tank.update(-player_speed);
				}
				/***
				 * 坦克与坦克的碰撞检测
				 ***/
				for (Tank t : tanks) {
					if (t != tank && tank.getRect().intersects(t.getRect()))
						tank.update(-player_speed);
				}
			}
		}

		for (Bullet bullet : bullets) {
			bullet.update();
		}

		for (Wall wall : walllist) {
			for (Bullet bullet : bullets) {
				if (bullet.getRect().intersects(wall.getRect())) {
					bullet.death();
					wall.damage(bullet.damage);
					bullets.remove(bullet);
					if (wall.style != 3 && wall.destroyed)
						walllist.remove(wall);
					if (wall.style == 3 && wall.destroyed) {
						wall.update();
						return false;
					}
					break;
				}
			}

		}

		if (dead_enemy == getEnemyNum_Max()) {
			GameControl.Vectory();
			return false;
		}
		return true;
	}

	public static void addEnemy(int n) {
		for (int i = 0; i < n; i++) {
			Random rand = new Random();
			Tank enemy = new Tank(rand.nextInt(10) * 80 + 80.0, 30, rand.nextInt(2) == 0 ? Team.ENEMY1 : Team.ENEMY2,
					rand.nextInt(4) * 90, enemy_speed, enemy_hp);

			Iterator<Tank> it = tempanklist.iterator();
			while (it.hasNext()) {
				Tank tmp = it.next();
				if (enemy.getRect().intersects(tmp.getRect())) {
					it.remove();
					i--;
				}
			}
			tempanklist.add(enemy);

			// tanks.add(enemy);
		}
		for(Tank tank:tempanklist) {
			TankThread thread=new TankThread(tank);
			es.submit(thread);
		}
	}

	public static void addBullet(Bullet bullet) {

		bullets.add(bullet);
	}

	private void addWall() {
		// TODO Auto-generated method stub
		walllist.add(new Wall(442, 640, 3)); // 基地

		walllist.add(new Wall(394, 640, 1)); // 基地围墙
		walllist.add(new Wall(394, 592, 1));
		walllist.add(new Wall(442, 592, 1));
		walllist.add(new Wall(490, 592, 1));
		walllist.add(new Wall(490, 640, 1));

		walllist.add(new Wall(346, 302, 1)); // H型墙
		walllist.add(new Wall(346, 350, 1));
		walllist.add(new Wall(346, 398, 1));
		walllist.add(new Wall(346, 446, 1));
		walllist.add(new Wall(394, 350, 1));
		walllist.add(new Wall(442, 350, 1));
		walllist.add(new Wall(490, 350, 1));
		walllist.add(new Wall(538, 302, 1));
		walllist.add(new Wall(538, 350, 1));
		walllist.add(new Wall(538, 398, 1));
		walllist.add(new Wall(538, 446, 1));

		walllist.add(new Wall(188, 130, 1)); // 左上方砖
		walllist.add(new Wall(236, 130, 1));
		walllist.add(new Wall(188, 178, 1));
		walllist.add(new Wall(236, 178, 1));

		walllist.add(new Wall(418, 130, 1)); // 中间矩形砖
		walllist.add(new Wall(466, 130, 1));

		walllist.add(new Wall(648, 130, 1)); // 右上方砖
		walllist.add(new Wall(696, 130, 1));
		walllist.add(new Wall(648, 178, 1));
		walllist.add(new Wall(696, 178, 1));

		walllist.add(new Wall(24, 350, 2)); // 铁墙
		walllist.add(new Wall(72, 350, 2));
		walllist.add(new Wall(860, 350, 2));
		walllist.add(new Wall(812, 350, 2));

	}

	public static int getEnemyNum_Max() {
		return enemyNum_Max;
	}

	public int getDead_enemy() {
		return dead_enemy;
	}
}
