package rev.challenge.model;

public class Player {

	private String name;
	private float health;
	private Weapon weapon;
	private int score;
	

	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(float d) {
		this.health = d;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Player() {
		this.weapon=Weapon.makeWeapon("");
	}
}
