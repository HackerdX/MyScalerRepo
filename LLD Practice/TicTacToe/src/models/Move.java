package models;

public class Move {
    private Cell Cell;
    private Player player;

    public models.Cell getCell() {
        return Cell;
    }

    public void setCell(models.Cell cell) {
        Cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
