import java.util.Scanner;

import apcs.Window;

public class Main {
	
	Cell[][] cellMasterBoard;
	Cell[][] cellDisplay;
	Scanner stepBreak = new Scanner(System.in);
	
	final int fieldLength = 100;
	final int multiplier = 5;
	
	static Main refMain = new Main();
	Init refInit = new Init(refMain, multiplier, fieldLength, cellDisplay, cellMasterBoard, stepBreak);
	
	public static void main(String args[]) {
		Window.out.background(255, 255, 255);
		Window.size(refMain.fieldLength * refMain.multiplier, refMain.fieldLength * refMain.multiplier);
		
		refMain.refInit.initField();
		refMain.refInit.visualStep();
		refMain.refInit.initNextStep();
	}
}
