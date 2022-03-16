package src;

import java.lang.Math;

public class Character {
    //Default values START
    private String name = "Default";
    private int damage = 100;
    private double attackspeed = 1.0;
    private int life = 500;
    private boolean forcesensitive = false;
    private double tempattackdamage = 0;
    private int finalattackdamage = 0; //Muss 0 sein, falls man sich heilt, dann darf man ja kein Schaden abziehen. UPDATE: Klappt wohl trotzdem nicht, huh. Egal, setze einfach den finalattackdamage bei dem Typen selber auf 0
    private double hitchance = Math.random() * 100;
    private String tier = "TIER1";
    
    private String attack1 = "Melee";
    private String attack2 = "Kick";
    private String attack3 = "Bite";
    private String attack4 = "Spit";
    private String[] attacks = {attack1, attack2, attack3, attack4};
    private String type = "Damage";
    
    private int attack = 0;
    private int BurnCounter = 0;
    //Default values STOP
    
    
    //Getter START
    public String getName() {
        return name;
    }
    
    public int getLife() {
        return life;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public String[] getAttacks() {
        return attacks;
    }
    
    public double getTempAttackDamage() {
        return tempattackdamage;
    }
    
    public int getFinalAttackDamage() {
        return finalattackdamage;
    }
    
    public String getType() {
        return type;
    }
    
    public double getAttackSpeed() {
        return attackspeed;
    }
    
    public boolean getForceSensitive() {
        return forcesensitive;
    }
    
    public int getBurnCounter() {
        return BurnCounter;
    }
    
    public String getTier() {
        return tier;
    }
    //Getter STOP
    
    
    public void printStats() { //Printet src.Character Stats
        System.out.println("\nName: " + getName() + "\nsrc.Character Tier: " + getTier() + "\nLife: " + getLife() + "\nDamage: " + getDamage() + "\nAttack speed: " + getAttackSpeed() + "\nForce sensitive: " + getForceSensitive());
    }
    
    
    
    //Setters START
    public void setStats(String name, String tier, boolean forcesensitive) {
        this.name = name;
        this.forcesensitive = forcesensitive;
        this.tier = tier;
        if(tier.equals("TIER1")) {
            this.life = 500 + (int)(Math.random() * ((1100 - 500) + 1)); //Danke für die Random Idee, Jan! :D
            this.damage = 100 + (int)(Math.random() * ((250 - 100) + 1));
            this.attackspeed = 1.0 + (Math.random() * (1.35-1.0));
        }
        else if(tier.equals("TIER2")) {
            this.life = 900 + (int)(Math.random() * ((2100 - 900) + 1));
            this.damage = 250 + (int)(Math.random() * ((500 - 250) + 1));
            this.attackspeed = 1.15 + (Math.random() * (1.6-1.15));
        }
        else if(tier.equals("TIER3")) {
            this.life = 1900 + (int)(Math.random() * ((3000 - 1900) + 1));
            this.damage = 500 + (int)(Math.random() * ((750 - 500) + 1));
            this.attackspeed = 1.45 + (Math.random() * (2-1.45));
        }

        else {
            this.life = life;
            this.damage = damage;
            this.attackspeed = attackspeed;
        }
    }
    
    public void setLife(int life) { //Wird benötigt für die Attacken
        this.life = life;
    }
    
    //Attack Section
    
    public void setAttacks(String attack1, String attack2, String attack3, String attack4) {
        this.attacks[0] = attack1;
        this.attacks[1] = attack2;
        this.attacks[2] = attack3;
        this.attacks[3] = attack4;
    }

    public void setCurrentAttack(int attack) {
        this.attack = attack;
    }
    
    public void setFinalAttackDamage(int finalattackdamage) {
        this.finalattackdamage = finalattackdamage;
    }
    
    public void setBurnCounter(int BurnCounter) {
        this.BurnCounter = BurnCounter;
    }
    //Setters STOP
    


    public void Attacks() {
        hitchance = Math.random() * 100; //generiert random hitchance pro Angriff
        System.out.println(getName() + " benutzt " + getAttacks()[attack-1]);
        if(getAttacks()[attack-1].equals("Lichtschwert Angriff")) { //Again, muss -1 sein, da es ein Array ist. Wird pro Runde neu generiert, da Attacks() in der Schleife neu ausgeführt wird
            type = "Damage";
            tempattackdamage = damage * 1.0;
            finalattackdamage = (int)tempattackdamage;
        }
        else if(getAttacks()[attack-1].equals("Stimpacks") || getAttacks()[attack-1].equals("Machtheilung")) {
            type = "Heal";
            setLife(getLife()+250);
            setFinalAttackDamage(0); //Wenn dies nicht der Fall ist, dann fügt der Spieler Schaden hinzu. Zu OP
        }
        else if(getAttacks()[attack-1].contains("schild") || getAttacks()[attack-1].equals("Abwehr")) {
            type = "Shield";
            setFinalAttackDamage(0); //Wenn dies nicht der Fall ist, dann fügt der Spieler Schaden hinzu. Zu OP
        }
        else if(getAttacks()[attack-1].equals("Flammenwerfer")) {
            type = "Burn";
            BurnCounter = 4;
            tempattackdamage = damage * 1.0;
            finalattackdamage = (int)tempattackdamage;
        }
        else { //Ist immer nur mal 1
            type = "Damage";
            double rndm = 0.8 + (Math.random() * (1.2-0.8));
            tempattackdamage = damage * rndm;
            finalattackdamage = (int)tempattackdamage;
        }
        
        switch(tier) { //je nach Tier ist die chance nicht zu hitten größer
            case "TIER1":
                if(hitchance<20 && type == "Damage") {
                    finalattackdamage = 0;
                    System.out.println("\nDie Attacke von " + getName() + " ging daneben!");
                }
                break;
            case "TIER2":
                if(hitchance<15 && type == "Damage") {
                    finalattackdamage = 0;
                    System.out.println("\nDie Attacke von " + getName() + " ging daneben!");
                }
                break;
            case "TIER3":
                if(hitchance<10 && type == "Damage") {
                    finalattackdamage = 0;
                    System.out.println("\nDie Attacke von " + getName() + " ging daneben!");
                }
                break;
            }
    }
}
