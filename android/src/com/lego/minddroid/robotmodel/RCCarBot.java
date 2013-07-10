package com.lego.minddroid.robotmodel;

import com.lego.minddroid.BTCommunicator;

public class RCCarBot extends RobotModel {
	/**
	 * Class for driving the RCCarBot.
	 *  
	 * @author barbolani@gmail.com
	 *
	 */
	@Override
	public void performAction(int buttonMode) {
		// TODO Auto-generated method stub

	}

	private boolean stopAlreadySent = false ;
	
	@Override
	public void updateMotorControl(float pitch, float roll) {
		
        if (commsHandler != null) {
    		int power = (int)(100*pitch) ;
    		int steering = (int)(100*roll) ;
            // don't send motor stop twice
            if ((power == 0) && (steering == 0)) {
                if (stopAlreadySent)
                    return;
                else
                    stopAlreadySent = true;
            }
            else
                stopAlreadySent = false;         
            
            power = Math.max(-100, Math.min(100, power) );
            // send messages via the handler
            sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.MOTOR_B, power, 0);
            //sendBTCmessage(BTCommunicator.NO_DELAY, motorRight, rightMotor * directionRight, 0);
        }

	}

}
