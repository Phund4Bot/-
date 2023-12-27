public class Dragon extends Monstr {
    private Integer countDragons = 0;

    private boolean isBreatheFlame;

    @Override
    public void getSound() {
        System.out.println("R-r-r-r-r-r-r-");
    }

    public Dragon() {
        super();
        this.countDragons++;
        this.isBreatheFlame=false;
    }

    public Dragon(int height, int width, int weight, boolean isBreatheFlame) {
        super(height, width, weight);
        this.countDragons++;
        this.isBreatheFlame = isBreatheFlame;
    }
    
    public int getCountMonsters() {
        return this.countDragons;
    }

    public void setBreatheFlame() {
        this.isBreatheFlame = !this.isBreatheFlame;
    }

    public boolean getBreatheFlame() {
        return this.isBreatheFlame;
    }
}
