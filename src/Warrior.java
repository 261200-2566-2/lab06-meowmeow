public class Warrior implements Character {
    private String name;
    private int level;
    private double baseHp;
    private double hp;
    private double maxHp;
    private double baseMana;
    private double mana;
    private double maxMana;
    private double baseAttack;
    private double attack;
    private double baseMagic;
    private double magic;
    private double baseDefense;
    private double defense;
    private double baseSpeed;
    private double speed;
    private double maxSpeed;
    private Shield shield;

    public Warrior() {
        this("Sam");
    }

    public Warrior(String name) {
        this(name, 0);
    }

    public Warrior(String name, int level) {
        this(name, 100, 50, 10, 10, 10, 10, level);
    }

    public Warrior(String name, double baseHp, double baseMana, double baseAttack, double baseMagic, double baseDefense, double baseSpeed, int level) {
        this.name = name;
        this.level = level;
        this.baseHp = baseHp;
        this.hp = baseHp + (10 * level);
        this.maxHp = baseHp + (10 * level);
        this.baseMana = baseMana;
        this.mana = baseMana + (2 * level);
        this.maxMana = baseMana + (2 * level);
        this.baseAttack = baseAttack;
        this.attack = baseAttack * (1 + 0.1 * level);
        this.baseMagic = baseMagic;
        this.magic = baseMagic * (1 + 0.1 * level);
        this.baseDefense = baseDefense;
        this.defense = baseDefense * (1 + 2 * level);
        this.baseSpeed = baseSpeed;
        this.speed = baseSpeed + (0.1 + 0.03 * level);
        this.maxSpeed = baseSpeed + (0.1 + 0.03 * level);
    }
    
    /**
     * Calculates the stats of the warrior based on their level and base stats.
     * Updates the maximum HP, maximum mana, maximum speed, attack, magic, defense, and speed of the warrior.
     * If the warrior has a shield equipped, the speed is adjusted accordingly.
     */
    private void calculateStats() {
        this.maxHp = baseHp + (10 * level);
        this.maxMana = baseMana + (2 * level);
        this.maxSpeed = baseSpeed + (0.1 + 0.03 * level);
        this.attack = baseAttack * (1 + 0.1 * level);
        this.magic = baseMagic * (1 + 0.1 * level);
        this.defense = baseDefense * (1 + 2 * level);
        this.maxSpeed = baseSpeed + (1 + 0.03 * level);
        if (shield != null) {
            speed = maxSpeed * (0.7 + 0.03 * shield.getLevel());
        } else {
            speed = maxSpeed;
        }
    }

    /**
     * @return the name of the Warrior
     */
    public String getName() {
        return name;
    }

    /**
     * @return the level of the Warrior
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the current hp of the Warrior
     */
    public double getHp() {
        return hp;
    }

    /**
     * @return the maximum hp of the Warrior
     */
    public double getMaxHp() {
        return maxHp;
    }

    /**
     * @return the current mana of the Warrior
     */
    public double getMana() {
        return mana;
    }

    /**
     * @return the maximum mana of the Warrior
     */
    public double getMaxMana() {
        return maxMana;
    }

    /**
     * Returns the attack damage of the Warrior.
     * If the Warrior has a shield equipped, the attack damage is increased by the shield's attack damage.
     * @return the attack damage of the Warrior
     */
    public double getAttack() {
        return shield == null ? attack : attack + shield.getAttack();
    }

    /**
     * @return the magic damage of the Warrior
     */
    public double getMagic() {
        return magic;
    }

    /**
     * Returns the defense value of the Warrior.
     * If the Warrior has a shield equipped, the defense value is increased by the shield's defense value.
     * If the Warrior does not have a shield equipped, the defense value remains unchanged.
     *
     * @return the defense value of the Warrior
     */
    public double getDefense() {
        return shield == null ? defense : defense + shield.getDefense();
    }

    /**
     * @return the speed of the Warrior
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @return the shield object equipped by the Warrior, or null if the Warrior does not have a shield equipped
     */
    Shield getShield() {
        return shield;
    }

    /**
     * Sets the shield of the Warrior.
     * 
     * @param shield the shield to be set
     */
    public void setShield(Shield shield) {
        this.shield = shield;
    }
    
    /**
     * Increases the level of the warrior and recalculates the stats.
     */
    public void upgrade() {
        level++;
        calculateStats();
    }

    /**
     * Reduces the warrior's hp based on the incoming damage.
     * The damage is reduced by half of the warrior's defense value.
     * If the damage is greater than 0, it is subtracted from the hp.
     *
     * @param damage the amount of damage to be taken
     */
    protected void getAttacked(double damage) {
        damage -= this.getDefense() * 0.5;
        if (damage > 0) {
            hp -= damage;
        }
    }
    
    /**
     * Attacks the specified target character.
     * Get class of target and call getAttacked method of target.
     * 
     * @param target the character to be attacked
     */
    public void attack(Character target) {
        if (target instanceof Warrior) {
            Warrior warrior = (Warrior) target;
            warrior.getAttacked(this.getAttack());
        } else if (target instanceof Mage) {
            Mage mage = (Mage) target;
            mage.getAttacked(this.getAttack());
        } else if (target instanceof Assassin) {
            Assassin assassin = (Assassin) target;
            assassin.getAttacked(this.getAttack());
        }
    }

    /**
     * Decreases the warrior's hp by the specified amount of magic damage.
     *
     * @param damage the amount of magic damage to be taken
     */
    protected void getAttackedMagic(double damage) {
        hp -= damage;
    }

    /**
     * Attacks the target character with magic.
     * Get class of target and call getAttackedMagic method of target.
     * 
     * @param target the character to be attacked with magic
     */
    public void attackMagic(Character target) {
        if (target instanceof Warrior) {
            Warrior warrior = (Warrior) target;
            warrior.getAttackedMagic(this.getMagic());
        } else if (target instanceof Mage) {
            Mage mage = (Mage) target;
            mage.getAttackedMagic(this.getMagic());
        } else if (target instanceof Assassin) {
            Assassin assassin = (Assassin) target;
            assassin.getAttackedMagic(this.getMagic());
        }
    }

    /**
     * Increases the warrior's hp by the specified amount.
     * If the hp exceeds the maximum hp, it is set to the maximum hp.
     *
     * @param amount the amount of hp to be healed
     */
    public void heal(double amount) {
        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    /**
     * Increases the warrior's mana by the specified amount.
     * If the mana exceeds the maximum mana, it is set to the maximum mana.
     *
     * @param amount the amount of mana to be restored
     */
    public void restoreMana(double amount) {
        mana += amount;
        if (mana > maxMana) {
            mana = maxMana;
        }
    }
}
