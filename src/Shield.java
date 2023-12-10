public class Shield implements Weapon {
    String name;
    int level;
    double baseAttack;
    double attack;
    double baseDefense;
    double defense;

    public Shield() {
        this("Stupid");
    }

    public Shield(String name) {
        this(name, 0);
    }

    public Shield(String name, int level) {
        this(name, 10, 10, level);
    }

    public Shield(String name, double baseAttack, double baseDefense, int level) {
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.level = level;
    }

    /**
     * @return the name of the shield
     */
    public String getName() {
        return name;
    }

    /**
     * @return the level of the shield
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the attack damage of the shield
     */
    public double getAttack() {
        return attack;
    }

    /**
     * @return the defense value of the shield
     */
    public double getDefense() {
        return defense;
    }

    /**
     * Upgrades the shield by increasing its level and adjusting the attack and defense values accordingly.
     */
    public void upgrade() {
        level++;
        attack = baseAttack * (1 + 0.1 * level);
        defense = baseDefense * (1 + 0.2 * level);
    }
}
