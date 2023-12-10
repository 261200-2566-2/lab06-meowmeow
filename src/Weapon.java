public interface Weapon {
    String name = "Stupid";
    int level = 0;
    
    /**
     * @return the name of the weapon
     */
    default String getName() {
        return name;
    }

    /**
     * @return the level of the weapon
     */
    default int getLevel() {
        return level;
    }
    
    /**
     * Upgrades the weapon.
     */
    void upgrade();
}
