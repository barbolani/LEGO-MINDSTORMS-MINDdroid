package com.lego.minddroid.robotmodel;

import com.lego.minddroid.BTCommunicator;
/**
 * Base class for driving all robot types that have one motor attached to each wheel,
 * such as the RoboGator, the ShooterBot, the Tribot or the LejOS bot.
 * The default motor connectors are:
 *   motorLeft  : B
 *   motorRight : C
 *   motorAction: A
 *   
 * Refactored from GameView/MindDroid classes
 * 
 * @author barbolani@gmail.com
 *
 */
public class MotorPerWheelRobot extends RobotModel {

	protected int motorLeft;
	protected int directionLeft; // +/- 1
	protected int motorRight;
	protected int directionRight; // +/- 1
	protected int motorAction;
	protected int directionAction; // +/- 1
	// These two are updated during each cycle on updateMotorControl
	private int leftMotor ;
	private int rightMotor ;
	/**
	 * Creates a robot with the default control assignments:
	 *   motorLeft : B
	 *   motorRight : C
	 *   motorAction: A
	 */
	public MotorPerWheelRobot() {
		
		motorLeft = BTCommunicator.MOTOR_B;
        directionLeft = 1;
        motorRight = BTCommunicator.MOTOR_C;
        directionRight = 1;
        motorAction = BTCommunicator.MOTOR_A;
        directionAction = 1;
        
	}
	/**
	 * Default robots: 180 degrees forth and back
	 * @param buttonMode on ACTION_BUTTON_SHORT turns 180 degrees right, otherwise left
	 */
	@Override public void performAction(int buttonMode ) {
		
		// other robots: 180 degrees forth and back
	    int direction = (buttonMode == ACTION_BUTTON_SHORT ? 1 : -1);                
	    sendBTCmessage( BTCommunicator.NO_DELAY, motorAction,75*direction*directionAction, 0);
	    sendBTCmessage( 500, motorAction, -75*direction*directionAction, 0);
	    sendBTCmessage( 1000, motorAction, 0, 0);
	}
	/**
	 * Swaps the values of the right and left motors
	 */
	protected void reverseRightAndLeftMotors() {
		int rev_left = rightMotor ;
		int rev_right = leftMotor ;
		leftMotor = rev_left ;
		rightMotor = rev_right ;
	}
	
	/**
	 * Calculates the actions in the robot motors to perform according to the device haptic feedback
     * @param point (x,y) is the amount of power to be applied to the (left,right) motor
	 * @param pitch amount of pitch reported by the device
	 * @param roll amount of roll reported by the device 
	 */
	public void calcMotorMovement(float pitch, float roll) {
		
		// only when phone is little bit tilted
		if ((Math.abs(pitch) > 10.0) || (Math.abs(roll) > 10.0)) {

			// limit pitch and roll
			if (pitch > 33.3) {
				pitch = (float) 33.3;
			} else if (pitch < -33.3) {
				pitch = (float) -33.3;
			}

			if (roll > 33.3) {
				roll = (float) 33.3;
			} else if (roll < -33.3) {
				roll = (float) -33.3;
			}

			// when pitch is very small then do a special turning function
			if (Math.abs(pitch) > 10.0) {
				leftMotor = (int) Math.round(3.3 * pitch * (1.0 + roll / 60.0));
				rightMotor = (int) Math.round(3.3 * pitch * (1.0 - roll / 60.0));
			} else {
				leftMotor = (int) Math.round(3.3 * roll - Math.signum(roll) * 3.3 * Math.abs(pitch));
				rightMotor = -leftMotor;
			}

			// limit the motor outputs
			if (leftMotor > 100)
				leftMotor = 100;
			else if (leftMotor < -100)
				leftMotor = -100;

			if (rightMotor > 100)
				rightMotor = 100;
			else if (rightMotor < -100)
				rightMotor = -100;
		}
	}

	private boolean stopAlreadySent = false ;
	
	@Override public void updateMotorControl(float pitch, float roll) {

        if (commsHandler != null) {
    		calcMotorMovement( pitch, roll );
            // don't send motor stop twice
            if ((leftMotor == 0) && (rightMotor == 0)) {
                if (stopAlreadySent)
                    return;
                else
                    stopAlreadySent = true;
            }
            else
                stopAlreadySent = false;         
                        
            // send messages via the handler
            sendBTCmessage(BTCommunicator.NO_DELAY, motorLeft, leftMotor * directionLeft, 0);
            sendBTCmessage(BTCommunicator.NO_DELAY, motorRight, rightMotor * directionRight, 0);
        }
    }

}
