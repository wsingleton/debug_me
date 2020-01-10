package rev.challenge.model;

public class Player {

	private String name;
	private float health;
	private Weapon weapon;
	

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
	public Player() {
		this.weapon=Weapon.makeWeapon("");
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", health=" + health +
				", weapon=" + weapon +
				'}';
	}
}
