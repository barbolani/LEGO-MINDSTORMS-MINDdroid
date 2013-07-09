package com.lego.minddroid.robottype;

import com.lego.minddroid.BTCommunicator;
/**
 * Class for driving the LejOS robot. Refactored from GameView/MindDroid classes
 * 
 * @author barbolani@gmail.com
 *
 */
public class LejOS extends MotorPerWheelRobot {
	/**
	 * LejOS robots accept file uploads
	 */
	@Override public boolean accepstFiles() { return true; } // The LejOS robot accepts files
	/**
	 *  LejOS plays a different tune depending on how long the action button is pressed
	 *  @param buttonMode if it is ACTION_BUTTONSHORT it plays "Zauberfloete", else plays G-F-E-D-C
	 * 
	 */
	@Override public void performAction( int buttonMode ) {
		if (buttonMode == ACTION_BUTTON_SHORT) {
            // Wolfgang Amadeus Mozart 
            // "Zauberfloete - Der Vogelfaenger bin ich ja"
            sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.DO_BEEP, 392, 100);
            sendBTCmessage(200, BTCommunicator.DO_BEEP, 440, 100);
            sendBTCmessage(400, BTCommunicator.DO_BEEP, 494, 100);
            sendBTCmessage(600, BTCommunicator.DO_BEEP, 523, 100);
            sendBTCmessage(800, BTCommunicator.DO_BEEP, 587, 300);
            sendBTCmessage(1200, BTCommunicator.DO_BEEP, 523, 300);
            sendBTCmessage(1600, BTCommunicator.DO_BEEP, 494, 300);
        }
        else {
            // G-F-E-D-C
            sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.DO_BEEP, 392, 100);
            sendBTCmessage(200, BTCommunicator.DO_BEEP, 349, 100);
            sendBTCmessage(400, BTCommunicator.DO_BEEP, 330, 100);
            sendBTCmessage(600, BTCommunicator.DO_BEEP, 294, 100);
            sendBTCmessage(800, BTCommunicator.DO_BEEP, 262, 300);
        }
        // lejosMINDdroid: just send the message for button press
        sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.DO_ACTION, buttonMode, 0);

	}
	/**
	 * Reverse the motors when driving back the LejOS model
	 * @param pitch Android haptic device reported pitch
	 * @param roll Android haptic device reported roll
	 */
	@Override public void calcMotorMovement(float pitch, float roll) {
		
		super.calcMotorMovement(pitch, roll);
		if (pitch < -10 ) {
			reverseRightAndLeftMotors();
		}
		
	}

}
