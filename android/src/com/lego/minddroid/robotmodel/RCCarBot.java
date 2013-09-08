package com.lego.minddroid.robotmodel;

import com.lego.minddroid.BTCommunicator;
/**
 * Class for driving the RCCarBot.
 *  
 * @author barbolani@gmail.com
 *
 */
public class RCCarBot extends RobotModel {
	
	// This limits the maximum range of movement of the steering wheel
	static private int MAX_STEER_POS = 40 ;
	
	private int lastSteerPos = 0 ;
	/**
	 * Stops drive motor and resets steering wheel on action button
	 * @param buttonMode ignored
	 */
	@Override
	public void performAction(int buttonMode) {
		lastSteerPos = 0 ;
        sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.MOTOR_B, 0, 0);
    	sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.MOTOR_A, 0, 0 );
		
	}

	private boolean stopAlreadySent = false ;
	
	@Override
	public void updateMotorControl(float pitch, float roll) {
		
        if (commsHandler != null) {
    		int power = (int)(pitch) ;
    		int steering = (int)(roll) ;
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
            // scale the roll (who is communicated as [-100,100] range 
            // so the final value lies in the [-MAX_STEER_POS, MAX_STEER_POS] range
            int adjustedRoll = Math.max(-MAX_STEER_POS,  Math.min(MAX_STEER_POS, ((int)roll) * MAX_STEER_POS / 100 )) ;
            if( adjustedRoll != lastSteerPos ) {
            	sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.MOTOR_A, adjustedRoll - lastSteerPos , 0 );
            	lastSteerPos = adjustedRoll ;
            }
        }

	}

}
