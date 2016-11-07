package flashpoint;

public class Wall {

    int currentHealth;
    int currentRow;
    int currentColumn;

    Wall() {
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
