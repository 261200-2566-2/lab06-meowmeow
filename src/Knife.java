public class Knife implements Weapon {
    String name;
    int level;
    double baseAttack;
    double attack;

    public Knife() {
        this("Stupid");
    }

    public Knife(String name) {
        this(name, 0);
    }

    public Knife(String name, int level) {
        this(name, 20, level);
    }

    public Knife(String name, double baseAttack, int level) {
        this.name = name;
        this.baseAttack = baseAttack;
        this.level = level;
    }

    /**
     * @return the name of the Knife
     */
    public String getName() {
        return name;
    }

    /**
     * @return the level of the Knife
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the attack damage of the knife
     */
    public double getAttack() {
        return attack;
    }

    /**
     * Upgrades the knife by increasing its level and attack damage.
     */
    public void upgrade() {
        level++;
        attack = baseAttack * (1 + 0.1 * level);
    }
}
