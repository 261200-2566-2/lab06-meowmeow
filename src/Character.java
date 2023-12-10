interface Character {
    String name = "Sam";
    int level = 0;
    double baseHp = 100;
    double hp = 100;
    double maxHp = 100;
    double baseMana = 50;
    double mana = 50;
    double maxMana = 50;
    double baseAttack = 10;
    double attack = 10;
    double baseMagic = 10;
    double magic = 10;
    double baseDefense = 10;
    double defense = 10;
    double baseSpeed = 10;
    double speed = 10;
    double maxSpeed = 10;

    /**
     * @return the name of the character
     */
    default String getName() {
        return name;
    }

    /**
     * @return the level of the character
     */
    default int getLevel() {
        return level;
    }

    /**
     * @return the current hp of the character
     */
    default double getHp() {
        return hp;
    }

    /**
     * @return the maximum hp of the character
     */
    default double getMaxHp() {
        return maxHp;
    }

    /**
     * @return the mana of the character
     */
    default double getMana() {
        return mana;
    }

    /**
     * @return the maximum mana of the character
     */
    default double getMaxMana() {
        return maxMana;
    }

    /**
     * @return the attack damage of the character
     */
    default double getAttack() {
        return attack;
    }

    /**
     * @return the magic damage of the character
     */
    default double getMagic() {
        return magic;
    }

    /**
     * @return the defense value of the character
     */
    default double getDefense() {
        return defense;
    }

    /**
     * @return the speed of the character
     */
    default double getSpeed() {
        return speed;
    }
    
    /**
     * Upgrades the character
     * And recalculate the stats
     */
    void upgrade();
    
    /**
     * Attacks the specified target character.
     *
     * @param target the character to be attacked
     */
    void attack(Character target);

    /**
     * Attacks the specified target using magic.
     *
     * @param target the character to be attacked
     */
    void attackMagic(Character target);

    /**
     * Heals the character by the specified amount.
     *
     * @param amount the amount to heal the character
     */
    void heal(double amount);

    /**
     * Restores the mana of the character by the specified amount.
     *
     * @param amount the amount of mana to restore
     */
    void restoreMana(double amount);
}