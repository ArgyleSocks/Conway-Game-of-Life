
public class Cell {
	
	boolean lifeState;
	int cellCheck = 0;
	
	//Value indicating life or death, respectively true or false
	public Cell(boolean lifeState) {
		this.lifeState = lifeState;
	}
	
	//Rules for if cell is dead or alive
	public void determineLifeState() {
		if (lifeState && (!(cellCheck == 2) && !(cellCheck == 3))) {
			lifeState = false;
		} else if (!lifeState && (cellCheck == 3)) {
			lifeState = true;
		}
	}
}
