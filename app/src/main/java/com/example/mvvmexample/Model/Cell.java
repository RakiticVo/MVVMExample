package com.example.mvvmexample.Model;

public class Cell {
    public Player player;

    public Cell(Player player) {
        this.player = player;
    }
    // Hàm xây dựng theo dựa vào kiểu String của PlayerValue
    public Cell(String name, String value) {
        this.player = new Player(name, value);
    }
    // Hàm kiểm tra giá trị của player
    public boolean isEmpty() {
        return player == null || player.value == Player.PlayerValue.VALUE_EMPTY;
    }
}
