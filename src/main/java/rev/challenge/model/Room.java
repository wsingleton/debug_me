package rev.challenge.model;

public class Room extends Space{
	private Enemy enemy;

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	@Override
	public String toString() {
		// fix current position when attack 
		if(isCurrent()){
			return "P";
		}
		if(isVisited()) {
			return "S";
			
		}
		return "R";
	}

	public Room() {
		enemy=new Enemy();
	}
	
	

}
