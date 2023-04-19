package cn.edu.ncepu.tankgame.Control;

import java.awt.Image;
import java.awt.event.*;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;

import cn.edu.ncepu.tankgame.Model.Bullet;
import cn.edu.ncepu.tankgame.Model.ImageCache;
import cn.edu.ncepu.tankgame.Model.Tank;
import cn.edu.ncepu.tankgame.Model.WarData;
import cn.edu.ncepu.tankgame.View.GameFrame;

public class GameControl {
	static GameFrame frame;
	static WarData wardata;
	ShutControl shutcontrol = new ShutControl();
	Timer timer;

	public GameControl(GameFrame frame, WarData wardata) {
		this.frame = frame;
		this.wardata = wardata;
		Tank tank = wardata.player;
		shutcontrol.start();

		timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (wardata.update())
					frame.repaint();
				else {
					frame.repaint();
					timer.stop();
					wardata.es.shutdownNow();
					if (wardata.getDead_enemy() < WarData.getEnemyNum_Max())
						gameOver();
				}
			}
		});
		timer.start();

		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (tank.destroyed) {
					tank.moving = false;
					return;
				}
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					tank.direction = 0;
					tank.moving = true;
					break;
				case KeyEvent.VK_DOWN:
					tank.direction = 180;
					tank.moving = true;
					break;
				case KeyEvent.VK_LEFT:
					tank.direction = 270;
					tank.moving = true;
					break;
				case KeyEvent.VK_RIGHT:
					tank.direction = 90;
					tank.moving = true;
					break;
				case KeyEvent.VK_SPACE:
					if (shutcontrol.shuting) {
						playMusic("src/music/fire.wav");
						Bullet bullet = new Bullet(tank, 8);
						wardata.bullets.add(bullet);
						shutcontrol.shuting = false;
					}

					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_SPACE:
					tank.moving = false;
					break;
				}
			}
		});

	}

	public static void playMusic(String filePath) {
		Clip start = null;
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
			start = AudioSystem.getClip();
			start.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start.start();
	}

	public static void gameOver() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame, "Game Over ! You Lose !", "Defeat", 1);
		System.out.println("game over");
	}

	public static void Vectory() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame, "Congratulations ! You Win !", "Vectory", 1);
		System.out.println("vectory");
	}
}
