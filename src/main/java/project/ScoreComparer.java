package project;

public class ScoreComparer implements Comparable<ScoreComparer> {

    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public ScoreComparer(String name, int score) {
        this.score = score;
        this.name = name;
    }

    @Override
    public int compareTo(ScoreComparer scoreComparer) {
        if (this.score > scoreComparer.score) {
            return -1;
        }
        else {
            return 1;
        }
    }
    
}
