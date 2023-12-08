public class Codex implements Weapon {
    String name;
    int level;
    double baseMagic;
    double magic;

    public Codex() {
        this("Stupid");
    }

    public Codex(String name) {
        this(name, 0);
    }

    public Codex(String name, int level) {
        this(name, 10, level);
    }

    public Codex(String name, double baseMagic, int level) {
        this.name = name;
        this.baseMagic = baseMagic;
        this.level = level;
    }

    /**
     * @return the name of the Codex
     */
    public String getName() {
        return name;
    }

    /**
     * @return the level of the Codex
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the magic damage of the Codex
     */
    public double getMagic() {
        return magic;
    }

    /**
     * Upgrades the Codex by increasing its level and updating the magic damage.
     */
    public void upgrade() {
        level++;
        magic = baseMagic * (1 + 0.2 * level);
    }
}
