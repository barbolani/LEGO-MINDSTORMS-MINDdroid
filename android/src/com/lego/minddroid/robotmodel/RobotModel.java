package com.lego.minddroid.robotmodel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
/**
 * Base class for driving all robot models.
 * 
 * Refactored from GameView/MindDroid classes
 * 
 * @author barbolani@gmail.com
 *
 */
public abstract class RobotModel {

	public static final int ACTION_BUTTON_SHORT = 0;
    public static final int ACTION_BUTTON_LONG = 1;
    
    protected Handler commsHandler ;
    protected Handler msgHandler ;
    /**
     * Initializes the handlers used to communicate with the NXT device
     * and the Android activity. This has to be called prior to sending any 
     * commands to the NXT unit, otherwise everything will fail.
     * 
     * @param commsHandler The bluetooth comms handler
     * @param msgHandler The activity handler
     */
    public void setHandlers( Handler commsHandler, Handler msgHandler ) {
    	this.commsHandler = commsHandler ;
    	this.msgHandler = msgHandler ;
    }
    /**
     * Reports if the robot accepts file uploads
     * @return true if files can be uploaded to the robot, false otherwise
     */
    // By default do not accept files
    public boolean accepstFiles() { return false ; }
    /**
     * Response to the activity action button pressed
     * @param buttonMode can be ACTION_BUTTON_SHORT or ACTION_BUTTON_LONG
     */
	abstract public void performAction(int buttonMode);
	/**
	 * Sends to the NXT device the motor control messages based on haptic feedback
	 * @param pitch the Android device pitch degree
	 * @param roll the Android device roll degree
	 */
	abstract public void updateMotorControl(float pitch, float roll);

    /**
     * Sends the message via the BTCommuncator to the robot.
     * @param delay time to wait before sending the message.
     * @param message the message type (as defined in BTCommucator)
     * @param value1 first parameter
     * @param value2 second parameter
     */   
    public void sendBTCmessage(int delay, int message, int value1, int value2) {
        Bundle myBundle = new Bundle();
        myBundle.putInt("message", message);
        myBundle.putInt("value1", value1);
        myBundle.putInt("value2", value2);
        Message myMessage = msgHandler.obtainMessage();
        myMessage.setData(myBundle);

        if (delay == 0)
            commsHandler.sendMessage(myMessage);

        else
            commsHandler.sendMessageDelayed(myMessage, delay);
    }
    /**
     * Sends the message via the BTCommuncator to the robot.
     * @param delay time to wait before sending the message.
     * @param message the message type (as defined in BTCommucator)
     * @param String a String parameter, whose meaning depends on the message
     */       
    public void sendBTCmessage(int delay, int message, String name) {
        Bundle myBundle = new Bundle();
        myBundle.putInt("message", message);
        myBundle.putString("name", name);
        Message myMessage = msgHandler.obtainMessage();
        myMessage.setData(myBundle);

        if (delay == 0)
            commsHandler.sendMessage(myMessage);
        else
        	commsHandler.sendMessageDelayed(myMessage, delay);
    }

}
