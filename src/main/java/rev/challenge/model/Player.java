package rev.challenge.model;

import java.util.Objects;

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
		return
				name;

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Player player = (Player) o;
		return Float.compare(player.health, health) == 0 &&
				Objects.equals(name, player.name) &&
				Objects.equals(weapon, player.weapon);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, health, weapon);
	}
}
