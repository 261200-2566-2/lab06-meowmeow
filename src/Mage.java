public class Mage implements Character {
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
    private Codex codex;

    public Mage() {
        this("Momo");
    }

    public Mage(String name) {
        this(name, 0);
    }

    public Mage(String name, int level) {
        this(name, 100, 50, 10, 10, 10, 10, level);
    }

    public Mage(String name, double baseHp, double baseMana, double baseAttack, double baseMagic, double baseDefense, double baseSpeed, int level) {
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
        this.magic = baseMagic * (1 + 2 * level);
        this.baseDefense = baseDefense;
        this.defense = baseDefense * (1 + 0.1 * level);
        this.baseSpeed = baseSpeed;
        this.speed = baseSpeed + (0.1 + 0.03 * level);
        this.maxSpeed = baseSpeed + (0.1 + 0.03 * level);
    }
    
    /**
     * Calculates the stats of the Mage based on its level and base stats.
     * Updates the maxHp, maxMana, maxSpeed, attack, magic, defense, and speed attributes.
     * If the Mage has a codex, the speed is modified based on the codex level.
     */
    private void calculateStats() {
        this.maxHp = baseHp + (10 * level);
        this.maxMana = baseMana + (2 * level);
        this.maxSpeed = baseSpeed + (0.1 + 0.03 * level);
        this.attack = baseAttack * (1 + 0.1 * level);
        this.magic = baseMagic * (1 + 2 * level);
        this.defense = baseDefense * (1 + 0.1 * level);
        this.maxSpeed = baseSpeed + (1 + 0.03 * level);
        if (codex != null) {
            speed = maxSpeed * (0.8 + 0.07 * codex.getLevel());
        } else {
            speed = maxSpeed;
        }
    }

    /**
     * @return the name of the Mage
     */
    public String getName() {
        return name;
    }

    /**
     * @return the level of the Mage
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the current hp of the Mage
     */
    public double getHp() {
        return hp;
    }

    /**
     * @return the maximum hp of the Mage
     */
    public double getMaxHp() {
        return maxHp;
    }

    /**
     * @return the current mana value of the Mage
     */
    public double getMana() {
        return mana;
    }

    /**
     * @return the maximum mana value of the Mage
     */
    public double getMaxMana() {
        return maxMana;
    }

    /**
     * @return the attack value of the Mage
     */
    public double getAttack() {
        return attack;
    }

    /**
     * Returns the magic damage of the Mage. If the Mage has a codex equipped,
     * the magic damage is increased by the magic damage of the codex.
     *
     * @return the magic damage of the Mage
     */
    public double getMagic() {
        return codex == null ? magic : magic + codex.getMagic();
    }

    /**
     * @return the defense value of the Mage
     */
    public double getDefense() {
        return defense;
    }

    /**
     * @return the speed of the Mage
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @return the Codex object of the Mage, or null if the Mage doesn't have a codex
     */
    Codex getCodex() {
        return codex;
    }

    /**
     * Sets the codex of the Mage.
     *
     * @param codex the codex to be set
     */
    public void setCodex(Codex codex) {
        this.codex = codex;
    }
    
    /**
     * Increases the level of the Mage and recalculates its stats.
     */
    public void upgrade() {
        level++;
        calculateStats();
    }

    /**
     * Decreases the Mage's hp by the specified amount of damage.
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
        } else if (target instanceof Mage) {
            Mage mage = (Mage) target;
            mage.getAttacked(this.getAttack());
        } else if (target instanceof Assassin) {
            Assassin assassin = (Assassin) target;
            assassin.getAttacked(this.getAttack());
        }
    }

    /**
     * Reduces the mage's hp when attacked by magic.
     * The damage taken is reduced by 50% of the mage's magic power.
     * If the damage is greater than 0, it is subtracted from the mage's hp.
     *
     * @param damage the amount of damage to be taken
     */
    protected void getAttackedMagic(double damage) {
        damage -= this.getMagic() * 0.5;
        if (damage > 0) {
            hp -= damage;
        }
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
        mana--;
    }

    /**
     * Increases the hp of the mage by the specified amount.
     * If the resulting hp exceeds the maximum hp, it is set to the maximum hp.
     *
     * @param amount the amount of hp to restore
     */
    public void heal(double amount) {
        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    /**
     * Restores the mana of the mage by the specified amount.
     * If the restored mana exceeds the maximum mana, the mana is set to the maximum value.
     *
     * @param amount the amount of mana to restore
     */
    public void restoreMana(double amount) {
        mana += amount;
        if (mana > maxMana) {
            mana = maxMana;
        }
    }
}
