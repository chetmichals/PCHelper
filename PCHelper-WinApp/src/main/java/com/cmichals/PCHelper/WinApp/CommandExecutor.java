package com.cmichals.PCHelper.WinApp;

import com.google.gson.JsonObject;

public class CommandExecutor {
	
	private static KeyHandler myKeyHandler = new KeyHandler();
	
	private static final String COMMAND_ELEMENT = "command";
	private static final String TEXT_ELEMENT = "text";

	private static final String TEXT_SEND_COMMAND = "textSend";
	private static final String PRESS_ENTER_COMMAND = "pressEnter";
	private static final String SHIFT_TAB_COMMAND = "shiftTab";
	private static final String TAB_COMMAND = "tab";
	private static final String SHIFT_ALT_TAB_COMMAND = "windowBack";
	private static final String ALT_TAB_COMMAND = "windowForward";
	private static final String ALT_TAB_SELECT_COMMAND = "selectWindow";
	
	public static void parseInput(JsonObject inputObject) {
		String command = inputObject.get(COMMAND_ELEMENT).getAsString();
		switch(command){ 
			case TEXT_SEND_COMMAND: 
				String text = inputObject.get(TEXT_ELEMENT).getAsString();
				myKeyHandler.type(text);
				break;
			case PRESS_ENTER_COMMAND:
				myKeyHandler.sendEnter();
				break;
			case SHIFT_TAB_COMMAND: 
				myKeyHandler.tabLeft();
				break;
			case TAB_COMMAND: 
				myKeyHandler.tabRight();
				break;
			case SHIFT_ALT_TAB_COMMAND:
				myKeyHandler.altTabLeft();
				break;
			case ALT_TAB_COMMAND:
				myKeyHandler.altTabRight();
				break;
			case ALT_TAB_SELECT_COMMAND:
				myKeyHandler.selectWindow();
				break;
			default:
				break;
		}
	}
}
