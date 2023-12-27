public abstract class Monstr {
    private Integer height = null;
    private Integer width = null;
    private Integer weight = null;
    private Integer countMonsters = 0;

    public Monstr() {
        this.countMonsters++;
    }

    public Monstr(int height, int width, int weight) {
        this.countMonsters++;
        this.height = height;
        this.width = width;
        this.weight = weight;
    }

    public int getCountMonsters() {
        return this.countMonsters;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;    
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;    
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;    
    }

    public abstract void getSound();
}
