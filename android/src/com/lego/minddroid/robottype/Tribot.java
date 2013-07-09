package com.lego.minddroid.robottype;

import com.lego.minddroid.BTCommunicator;
/**
 * Class for driving the Tribot robot. Refactored from the GameView/Minddroid classes
 * Tribots have the following motor connections:
 *  motorLeft  : B
 *  motorRight : C
 *  motorAction: A
 * 
 * @author barbolani@gmail.com
 *
 */
public class Tribot extends MotorPerWheelRobot {
	
	public Tribot() {
		motorLeft = BTCommunicator.MOTOR_B;
        directionLeft = 1;
        motorRight = BTCommunicator.MOTOR_C;
        directionRight = 1;
        motorAction = BTCommunicator.MOTOR_A;
        directionAction = 1;
	}

}
