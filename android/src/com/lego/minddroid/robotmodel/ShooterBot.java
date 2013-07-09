package com.lego.minddroid.robotmodel;
/**
 * Class for driving the ShooterBot robot. Refactored from the GameView/Minddroid classes
 * 
 * @author barbolani@gmail.com
 *
 */
public class ShooterBot extends MotorPerWheelRobot {
	/**
	 * ShooterBots uses the normal calculation but swaps the left and right motors
	 */
	@Override public void calcMotorMovement(float pitch, float roll) {
		super.calcMotorMovement( pitch, roll );
		if( pitch < -10 ) {
			reverseRightAndLeftMotors();
		}
		
	}
	
}
