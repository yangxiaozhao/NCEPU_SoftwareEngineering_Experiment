package cn.edu.ncepu.tankgame.View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import cn.edu.ncepu.tankgame.Model.*;

import java.awt.Color;
import java.awt.Dimension;

public class GameFrame extends JFrame {
	private WarData wardata;

	/**
	 * Create the frame.
	 */
	public GameFrame(WarData wardata) {
		this.wardata = wardata;
		init();
	}

	public void init() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		setLocationRelativeTo(null);
		setTitle("坦克大战低配版");
		JPanel contentPane = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				try {
					for (Tank tank : wardata.tanks)
						tank.draw(g2);
					for (Bullet bullet : wardata.bullets)
						bullet.draw(g2);
					for (Wall wall : wardata.walllist) {
						wall.draw(g2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		contentPane.setBackground(new Color(0.1f, 0.1f, 0.1f));
		setContentPane(contentPane);
		setVisible(true);
	}
}
