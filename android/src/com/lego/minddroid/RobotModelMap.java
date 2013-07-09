package com.lego.minddroid;

import android.util.SparseArray;

import com.lego.minddroid.robotmodel.LejOS;
import com.lego.minddroid.robotmodel.RoboGator;
import com.lego.minddroid.robotmodel.RobotModel;
import com.lego.minddroid.robotmodel.ShooterBot;
import com.lego.minddroid.robotmodel.Tribot;

public final class RobotModelMap {
	
	private static SparseArray<RobotModel> typeMap ;
	static {
		typeMap = new SparseArray<RobotModel>() ;
		typeMap.put( R.id.robot_type_lejos, new LejOS() );
		typeMap.put( R.id.robot_type_robogator, new RoboGator() );
		typeMap.put( R.id.robot_type_shooterbot, new ShooterBot() );
		typeMap.put( R.id.robot_type_tribot, new Tribot() );
	}
	
	static RobotModel get(int robotId ) {
		return typeMap.get(robotId);
	}
	
}
