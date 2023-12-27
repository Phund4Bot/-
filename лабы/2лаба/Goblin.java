public abstract class Goblin extends Monstr {

    private Integer countGoblins = 0;

    private boolean isArmed;

    @Override
    public void getSound() {
        System.out.println("Хлоп! Стоп! Вот тебе в лоб!" + "\n" + 
        "Глядь! Хвать! Как тебя звать?"  + "\n" + 
        "Живут гоблины тут, Эй, смелей, сюда!" + "\n" + 
        "Стук! Звон! Топот и стон!" + "\n" + 
        "Раз-два - молоток! Три-четыре - свисток! Вперед в подземный ход," + "\n" + 
        "Эй, шевелись побыстрей! Треск! Свист! Кнут, хлыст! Бей и стучи! Рычи и мычи!" + "\n" + 
        "Врешь, врешь! От нас не уйдешь! Гоблины пьют, хохочут, поют" + "\n" + 
        "А ну-ка вперед, в подземный ход, Живей, живей, живей" + "\n");
    }

    public Goblin() {
        super();
        this.countGoblins++;
        this.isArmed=false;
    }

    public Goblin(int height, int width, int weight, boolean isArmed) {
        super(height, width, weight);
        this.countGoblins++;
        this.isArmed = isArmed;
    }
    
    public int getCountMonsters() {
        return this.countGoblins;
    }

    public void setArmed() {
        this.isArmed = !this.isArmed;
    }

    public boolean getArmed() {
        return this.isArmed;
    }
}