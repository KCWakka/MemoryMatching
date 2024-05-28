public class Player {
    private int p1score;
    private int p2score;
    private String player1Name;
    private String player2Name;
    private boolean isPlayer1;

    public Player(String p1Name, String p2Name) {
        player1Name = p1Name;
        player2Name = p2Name;
        p1score = 0;
        p2score = 0;
        isPlayer1 = true;
    }
    public String getPlayer1Name() {
        return player1Name;
    }
    public String getPlayer2Name() {
        return player2Name;
    }
    public int getP1score() {
        return p1score;
    }
    public int getP2score() {
        return p2score;
    }
    public boolean isPlayer1() {
        return isPlayer1;
    }
    public void addScore() {
        if (isPlayer1) {
            p1score++;
        } else {
            p2score++;
        }
    }
     public void setIsPlayer1() {
        isPlayer1 = !isPlayer1;
     }
}
