public class Assassin implements Character {
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
    private Knife knife;

    public Assassin() {
        this("Samuel");
    }

    public Assassin(String name) {
        this(name, 0);
    }

    public Assassin(String name, int level) {
        this(name, 100, 50, 10, 10, 10, 10, level);
    }

    public Assassin(String name, double baseHp, double baseMana, double baseAttack, double baseMagic, double baseDefense, double baseSpeed, int level) {
        this.name = name;
        this.level = level;
        this.baseHp = baseHp;
        this.hp = baseHp + (10 * level);
        this.maxHp = baseHp + (10 * level);
        this.baseMana = baseMana;
        this.mana = baseMana + (2 * level);
        this.maxMana = baseMana + (2 * level);
        this.baseAttack = baseAttack;
        this.attack = baseAttack * (1 + 2 * level);
        this.baseMagic = baseMagic;
        this.magic = baseMagic * (1 + 0.1 * level);
        this.baseDefense = baseDefense;
        this.defense = baseDefense * (1 + 0.1 * level);
        this.baseSpeed = baseSpeed;
        this.speed = baseSpeed + (0.1 + 0.03 * level);
        this.maxSpeed = baseSpeed + (0.1 + 0.03 * level);
    }
    
    /**
     * Calculates the stats of the Assassin character based on their level and base stats.
     * Updates the maximum HP, maximum mana, maximum speed, attack, magic, defense, and speed of the Assassin.
     * If the Assassin has a knife equipped, the speed is adjusted accordingly.
     */
    private void calculateStats() {
        this.maxHp = baseHp + (10 * level);
        this.maxMana = baseMana + (2 * level);
        this.maxSpeed = baseSpeed + (0.1 + 0.03 * level);
        this.attack = baseAttack * (1 + 2 * level);
        this.magic = baseMagic * (1 + 0.1 * level);
        this.defense = baseDefense * (1 + 0.1 * level);
        this.maxSpeed = baseSpeed + (1 + 0.05 * level);
        if (knife != null) {
            speed = maxSpeed * (0.8 + 0.07 * knife.getLevel());
        } else {
            speed = maxSpeed;
        }
    }

    /**
     * @return the name of the Assassin
     */
    public String getName() {
        return name;
    }

    /**
     * @return the level of the Assassin
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the current hp of the Assassin
     */
    public double getHp() {
        return hp;
    }

    /**
     * @return the max hp of the Assassin
     */
    public double getMaxHp() {
        return maxHp;
    }

    /**
     * @return the current mana of the Assassin
     */
    public double getMana() {
        return mana;
    }

    /**
     * @return the max mana of the Assassin
     */
    public double getMaxMana() {
        return maxMana;
    }

    /**
     * Returns the attack damage of the Assassin. If the Assassin has a knife equipped,
     * the attack damage is increased by the attack damage of the knife.
     *
     * @return the attack damage of the Assassin
     */
    public double getAttack() {
        return knife == null ? attack : attack + knife.getAttack();
    }

    /**
     * @return the magic damage of the Assassin
     */
    public double getMagic() {
        return magic;
    }

    /**
     * @return the defense value of the Assassin
     */
    public double getDefense() {
        return defense;
    }

    /**
     * @return the speed of the Assassin
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @return the knife object of the Assassin, or null if the Assassin doesn't have a knife
     */
    Knife getKnife() {
        return knife;
    }

    /**
     * Sets the knife of the Assassin.
     * 
     * @param knife the knife to be set
     */
    public void setKnife(Knife knife) {
        this.knife = knife;
    }
    
    /**
     * Increases the level of the Assassin and recalculates its stats.
     */
    public void upgrade() {
        level++;
        calculateStats();
    }

    /**
     * Reduces the current hp of the Assassin by the specified amount of damage.
     *
     * @param damage the amount of damage to be taken
     */
    protected void getAttacked(double damage) {
        hp -= damage;
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
        } else if (target instanceof Assassin) {
            Assassin Assassin = (Assassin) target;
            Assassin.getAttacked(this.getAttack());
        } else if (target instanceof Mage) {
            Mage mage = (Mage) target;
            mage.getAttacked(this.getAttack());
        }
    }

    /**
     * Decreases the Assassin's hp by the specified amount of magic damage.
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
        } else if (target instanceof Assassin) {
            Assassin Assassin = (Assassin) target;
            Assassin.getAttackedMagic(this.getMagic());
        } else if (target instanceof Mage) {
            Mage mage = (Mage) target;
            mage.getAttackedMagic(this.getMagic());
        }
        mana--;
    }

    /**
     * Increases the current hp of the Assassin by the specified amount.
     * If the resulting hp exceeds the maximum hp, hp is set to maxHp.
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
     * Increases the current mana of the Assassin by the specified amount.
     * If the resulting mana exceeds the maximum mana, mana is set to maxMana.
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
