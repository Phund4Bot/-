public class Main {
    public static void main(String[] args) {
        GreenGoblin goblin = new GreenGoblin(50, 30, 60, false);
        System.out.println(goblin.getCountMonsters());
        goblin.setArmed();
        goblin.getSound();
    }
}
