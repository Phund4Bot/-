public class GreenGoblin extends Goblin {
    private Integer countGreenGoblins = 0;

    public GreenGoblin() {
        super();
        this.countGreenGoblins++;
    }

    public GreenGoblin(int height, int width, int weight, boolean isArmed) {
        super(height, width, weight, isArmed);
        this.countGreenGoblins++;
    }
    
    public int getCountMonsters() {
        return this.countGreenGoblins;
    }
}
