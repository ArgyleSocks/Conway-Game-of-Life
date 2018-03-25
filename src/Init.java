import apcs.Window;
import java.util.*;

public class Init {
	
	Main refMain;
	Scanner stepBreak;
	
	int multiplier;
	int fieldLength;
	Cell[][] cellDisplay;
	Cell[][] cellMasterBoard;
	int genValue;
	
	//synchronizes all of the objects used in Init with their counterparts in Main
	public Init(Main refMain, int multiplier, int fieldLength, Cell[][] cellDisplay, Cell[][] cellMasterBoard, Scanner stepBreak) {
		this.refMain = refMain;
		this.multiplier = multiplier;
		this.fieldLength = fieldLength;
		this.cellMasterBoard = cellMasterBoard;
		this.cellDisplay = cellDisplay;
		
		this.stepBreak = stepBreak;
	}
	
	//Randomly generates a pattern of cells
	public void initField() {
		cellMasterBoard = new Cell[fieldLength + 2][fieldLength + 2];
		cellDisplay = new Cell[fieldLength][fieldLength];
		
		for(int i = 0; i < cellMasterBoard.length; i++) {
			for(int j = 0; j < cellMasterBoard[0].length; j++) {
				if((i | j) == (0 | cellMasterBoard.length | cellMasterBoard[0].length)) {	
					cellMasterBoard[i][j] = new Cell(false);
				} else {
					cellMasterBoard[i][j] = new Cell((Math.random() > 0.5) ? true : false);
				}
			}
		}
		
		for(int i = 0; i < cellDisplay.length; i++) {
			for(int j = 0; j < cellDisplay[0].length; j++) {
				cellDisplay[i][j] = cellMasterBoard[i + 1][j + 1];
			}
		}
	}
	
	//Runs through the rules of the game to determine which cells live, die, or are revived
	/* Rules:
	 * Any living cell with anything but 2 or 3 neighbors dies
	 * Any dead cell with exactly 3 neighbors is revived
	 */
	public void initNextStep() {
		stepBreak.nextLine();
		
		Window.out.color(255, 255, 255);
		Window.out.square((fieldLength * multiplier)/2, (fieldLength * multiplier)/2, fieldLength * multiplier);
		
		for(int i = 0; i < cellDisplay.length; i++) {
			for(int j = 0; j < cellDisplay[0].length; j++) {
				cellDisplay[i][j] = cellMasterBoard[i + 1][j + 1];
			}
		}
		
		for(int i = 0; i < cellMasterBoard.length; i++) {
			for(int j = 0; j < cellMasterBoard[0].length; j++) {
				cellMasterBoard[i][j].cellCheck = 0;
			}
		}
		
		for(int i = 0; i < cellDisplay.length; i++) {
			for(int j = 0; j < cellDisplay[0].length; j++) {
				if(cellDisplay[i][j].lifeState) {
					for(int x = i; x < i + 3; x++) {
						for(int y = j; y < j + 3; y++) {
							if(!(x == (i + 1)) | !(y == (j + 1))) {
								cellMasterBoard[x][y].cellCheck++;
							}
						}
					}
				}
			}
		}
		
		/* Testing feature
		for(int i = 0; i < cellMasterBoard.length; i++) {
			for(int j = 0; j < cellMasterBoard[0].length; j++) {
				System.out.println(cellMasterBoard[i][j].cellCheck + " " + i + " " + j + " " + cellMasterBoard[i][j].lifeState);
			}
		}
		*/
		
		for(int i = 0; i < cellDisplay.length; i++) {
			for(int j = 0; j < cellDisplay[0].length; j++) {
				cellDisplay[i][j].determineLifeState();
			}
		}
		
		System.out.println("init generation" + genValue++);
		
		visualStep();
		initNextStep();
	}
	
	//Checks field for data values indicating living or dead cells and puts out a black square if it's alive
	public void visualStep() {
		
		Window.out.color(0, 0, 0);
		
		for(int i = 0; i < cellDisplay.length; i++) {
			for(int j = 0; j < cellDisplay[0].length; j++) {
				if(cellDisplay[i][j].lifeState == true) {
					Window.out.square(((i - 1) * multiplier + 8), ((j - 1) * multiplier + 8), multiplier);
				} else {}
			}
		}
	}
	
}
