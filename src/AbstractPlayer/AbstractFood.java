package src.AbstractPlayer;

public class AbstractFood {
    private String name;
    private boolean isFresh;
    private boolean isDangerous; //

    public AbstractFood(String name, boolean isFresh){
        this.name = name;
        this.isFresh = isFresh;
        this.isDangerous = isDangerous;
    }

    public String getName(){return name;}

    public boolean isFresh() {return isFresh;}

    public boolean isDangerous() {return isDangerous;}
}

