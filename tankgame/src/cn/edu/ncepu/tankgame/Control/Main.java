package cn.edu.ncepu.tankgame.Control;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.*;

import cn.edu.ncepu.tankgame.Model.Tank;
import cn.edu.ncepu.tankgame.Model.TankThread;
import cn.edu.ncepu.tankgame.Model.Team;
import cn.edu.ncepu.tankgame.Model.WarData;
import cn.edu.ncepu.tankgame.View.GameFrame;

public class Main {

	public static void main(String[] args) {
		
		WarData wardata = new WarData();

		GameFrame frame = new GameFrame(wardata);

		GameControl control = new GameControl(frame, wardata);
		
		control.playMusic("src/music/start.wav");

	}
}
