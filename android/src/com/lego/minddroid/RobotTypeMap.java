package com.lego.minddroid;

import android.util.SparseArray;

import com.lego.minddroid.robottype.LejOS;
import com.lego.minddroid.robottype.RoboGator;
import com.lego.minddroid.robottype.RobotModel;
import com.lego.minddroid.robottype.ShooterBot;
import com.lego.minddroid.robottype.Tribot;

public final class RobotTypeMap {
	
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
