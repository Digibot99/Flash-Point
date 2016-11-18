package flashpoint;

public class Invis_wall {

    int currentHealth;
    int currentRow;
    int currentColumn;

    Invis_wall() {
        currentColumn = (int) (Math.random() * Board.numColumns);
        currentRow = (int) (Math.random() * Board.numRows);
    }

    public int getCurrentRow() {
        return (currentRow);
    }

    public int getCurrentColumn() {
        return (currentColumn);
    }

    public int getCurrentHealth() {
        return (currentHealth);
    }
}
