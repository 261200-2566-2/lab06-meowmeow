public class Lab05 {
    public static void showStats(Character name) {
        System.out.println("Name: " + name.getName());
        System.out.println("Class: " + name.getClass());
        System.out.println("Level: " + name.getLevel());
        System.out.println("Hp: " + name.getHp() + "/" + name.getMaxHp());
        System.out.println("Mana: " + name.getMana() + "/" + name.getMaxMana());
        System.out.println("Attack: " + name.getAttack());
        System.out.println("Defense: " + name.getDefense());
        System.out.println("Magic: " + name.getMagic());
        System.out.println("Speed: " + name.getSpeed());
    }

    public static void restoreHpAndMana(Character name) {
        name.heal(name.getMaxHp());
        name.restoreMana(name.getMaxMana());
    }

    public static void main(String[] args) {
        Warrior Sam = new Warrior();
        Assassin Samuel = new Assassin();
        Mage Momo = new Mage();
        showStats(Sam);
        System.out.println("------");
        showStats(Samuel);
        System.out.println("------");
        showStats(Momo);

        System.out.println("--- Test 1 ---"); // Test attack, attack magic, heal, restore mana and level up
        System.out.println("Sam attack Samuel");
        Sam.attack(Samuel);
        showStats(Samuel);
        System.out.println("------");
        System.out.println("Sam level up");
        Sam.upgrade();
        showStats(Sam);
        System.out.println("------");
        System.out.println("Samuel attack Sam");
        Samuel.attack(Sam);
        showStats(Sam);
        System.out.println("------");
        System.out.println("Sam heal +100");
        Sam.heal(100);
        showStats(Sam);
        System.out.println("------");
        System.out.println("Momo attack magic Sam");
        Momo.attackMagic(Sam);
        showStats(Sam);
        System.out.println("------");
        System.out.println("Sam attack magic Momo");
        Sam.attackMagic(Momo);
        showStats(Momo);
        System.out.println("------");
        System.out.println("Sam restore hp and mana");
        restoreHpAndMana(Sam);
        showStats(Sam);

        System.out.println("--- Test 2 ---"); // Test character level up and weapon level up
        showStats(Momo);
        System.out.println("Momo level up +10");
        for (int i = 0; i < 10; i++) {
            Momo.upgrade();
        }
        showStats(Momo);
        System.out.println("------");
        System.out.println("Momo's Codex: " + Momo.getCodex().getName());
        System.out.println("Level: " + Momo.getCodex().getLevel());
        System.out.println("Magic: " + Momo.getCodex().getMagic());
        System.out.println("Codex level up +5");
        for (int i = 0; i < 5; i++) {
            Momo.getCodex().upgrade();
        }
        System.out.println("Momo's Codex: " + Momo.getCodex().getName());
        System.out.println("Level: " + Momo.getCodex().getLevel());
        System.out.println("Magic: " + Momo.getCodex().getMagic());

        System.out.println("--- Test 3 ---"); // Test attack until die
        restoreHpAndMana(Sam);
        restoreHpAndMana(Samuel);
        showStats(Sam);
        System.out.println("------");
        showStats(Samuel);
        if (Sam.getSpeed() > Samuel.getSpeed()) {
            while (Samuel.getHp() > 0) {
                System.out.println("Sam attack Samuel");
                Sam.attack(Samuel);
                System.out.println("Samuel's Hp: " + Samuel.getHp() + "/" + Samuel.getMaxHp());
                System.out.println("------");
            }
            System.out.println("Samuel dead");
            restoreHpAndMana(Samuel);
        } else {
            while (Sam.getHp() > 0) {
                System.out.println("Samuel attack Sam");
                Samuel.attack(Sam);
                System.out.println("Sam's Hp: " + Sam.getHp() + "/" + Sam.getMaxHp());
                System.out.println("------");
            }
            System.out.println("Sam dead");
            restoreHpAndMana(Sam);
        }

        System.out.println("--- Test 4 ---"); // Test attack magic until die
        restoreHpAndMana(Momo);
        showStats(Sam);
        System.out.println("------");
        showStats(Momo);

        while (Sam.getHp() > 0 || Momo.getHp() > 0) {
            System.out.println("Sam attack magic Momo");
            Sam.attackMagic(Momo);
            System.out.println(Sam.getMana() + "/" + Sam.getMaxMana());
            System.out.println("Momo's Hp: " + Momo.getHp() + "/" + Momo.getMaxHp());
            System.out.println("------");
            System.out.println("Momo attack magic Sam");
            Momo.attackMagic(Sam);
            System.out.println(Momo.getMana() + "/" + Momo.getMaxMana());
            System.out.println("Sam's Hp: " + Sam.getHp() + "/" + Sam.getMaxHp());
            System.out.println("------");
        }
        if (Sam.getHp() < 0) {
            System.out.println("Sam dead");
        } else {
            System.out.println("Momo dead");
        }
    }
}
