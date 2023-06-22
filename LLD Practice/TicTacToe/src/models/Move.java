package models;

public class Move {
    private Cell cell;
    private Player player;

    public Move(Cell cell, Player player){
        this.cell = cell;
        this.player = player;
    }
    public models.Cell getCell() {
        return this.cell;
    }

    public void setCell(models.Cell cell) {
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
