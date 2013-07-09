package com.lego.minddroid.robotmodel;

import com.lego.minddroid.BTCommunicator;
/**
 * Class for driving the RoboGator robot
 *   Left Motor   : C
 *   Right Motor  : B
 *   Action Motor : A * Refactored from GameView/MindDroid classes
 * 
 * @author barbolani@gmail.com
 *
 */
public class RoboGator extends MotorPerWheelRobot {
	/**
	 * Builds and initializes the RoboGator motor assignments:
	 *   Left Motor   : C
	 *   Right Motor  : B
	 *   Action Motor : A
	 */
	public RoboGator() {
		motorLeft = BTCommunicator.MOTOR_C;
        directionLeft = -1;
        motorRight = BTCommunicator.MOTOR_B;
        directionRight = -1;
        motorAction = BTCommunicator.MOTOR_A;
        directionAction = 1;
	}
	/**
	 * Robogators bite three times when the action button is pressed
	 * @param buttonMode This is ignored in the Robogator
	 */
	@Override public void performAction(int buttonMode ) {
        // Robogator: bite the user in any case ;-)
        for (int bite=0; bite<3; bite++) {
            sendBTCmessage(bite*400, motorAction, 75*directionAction, 0);
            sendBTCmessage(bite*400+200, motorAction, -75*directionAction, 0);
        }    
        sendBTCmessage(3*400, motorAction, 0, 0);		
	}
	
}
