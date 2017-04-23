package com.cmichals.PCHelper.WinApp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

public class KeyHandler implements ClipboardOwner {
	
	private boolean altHeld = false;
	Robot inputsSender;
	
	public KeyHandler() {
		try {
			inputsSender = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException("Could not create Robot class");
		}
	}
	
	public void altTabRight() {
		if (altHeld == false) {
			inputsSender.keyPress(KeyEvent.VK_ALT);
			altHeld = true;
		}
		tabRight();
	}
	
	public void altTabLeft() {
		if (altHeld == false) {
			inputsSender.keyPress(KeyEvent.VK_ALT);
			altHeld = true;
		}
		tabLeft();
	}
	
	public void tabRight() {
		inputsSender.keyPress(KeyEvent.VK_TAB);
		inputsSender.keyRelease(KeyEvent.VK_TAB);
	}
	
	public void tabLeft() {
		inputsSender.keyPress(KeyEvent.VK_SHIFT);
		inputsSender.keyPress(KeyEvent.VK_TAB);
		inputsSender.keyRelease(KeyEvent.VK_SHIFT);
		inputsSender.keyRelease(KeyEvent.VK_TAB);
	}
	
	public void selectWindow() {
		if(altHeld) inputsSender.keyRelease(KeyEvent.VK_ALT);
		altHeld = false;
	}
	
	public void type(String text) {
		//Exit alt-tab mode
		selectWindow(); 

		//Put string contents into clipboard
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection stringSelection = new StringSelection(text);
		clipboard.setContents(stringSelection, this);
		
		//Do a Ctrl-V to paste the clipboard contents
		inputsSender.keyPress(KeyEvent.VK_CONTROL);
		inputsSender.keyPress(KeyEvent.VK_V);
		inputsSender.keyRelease(KeyEvent.VK_V);
		inputsSender.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public void sendEnter() {
		inputsSender.keyPress(KeyEvent.VK_ENTER);
		inputsSender.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void sendEsc() {
		inputsSender.keyPress(KeyEvent.VK_ESCAPE);
		inputsSender.keyRelease(KeyEvent.VK_ESCAPE);
	}
	
	@Override
	public void lostOwnership(Clipboard aClipboard, Transferable aContents) {
		//Don't care
	}

}
