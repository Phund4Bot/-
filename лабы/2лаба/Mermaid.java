public class Mermaid extends Monstr {
    private Integer countMermaids = 0;

    private boolean isSong;

    @Override
    public void getSound() {
        System.out.println("Bul-bul-bul");
    }

    public Mermaid() {
        super();
        this.countMermaids++;
        this.isSong=false;
    }

    public Mermaid(int height, int width, int weight, boolean isSong) {
        super(height, width, weight);
        this.countMermaids++;
        this.isSong = isSong;
    }
    
    public int getCountMonsters() {
        return this.countMermaids;
    }

    public void setSong() {
        this.isSong = !this.isSong;
    }

    public boolean getSong() {
        return this.isSong;
    }
}
