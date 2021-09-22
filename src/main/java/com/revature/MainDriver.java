package com.revature;

import org.apache.log4j.Logger;

import com.revature.presentation.Presentation;
public class MainDriver {

	private static final Logger loggy = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
		
		loggy.info("Application starting.");
		
		Presentation menu = new Presentation();
		
		menu.displayStartMenu();
		
		loggy.info("Application ending.");

	}

}
