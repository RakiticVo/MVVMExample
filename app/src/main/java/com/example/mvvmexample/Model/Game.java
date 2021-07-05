package com.example.mvvmexample.Model;

import androidx.lifecycle.MutableLiveData;

public class Game {

    // getSimpleName là phương thức lấy chuỗi là tên lớp
    // ở đây nếu lấy chuỗi bằng toString() sẽ ra package.Game
    // còn getSimpleName() sẽ ra Game
    private static final String TAG = Game.class.getSimpleName();

    private static final int BOARD_SIZE = 3;

    public Player player1;
    public Player player2;

    public Player currentPlayer;

    public Cell[][] cells;

    // MultableLiveData dùng để lưu lại và thông báo cho view biết ai sẽ người chiến thắng
    public MutableLiveData<Player> winners = new MutableLiveData<>();

    public Game(String playerOneName, String playerTwoName) {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(playerOneName, "x");
        player2 = new Player(playerTwoName, "o");
        currentPlayer = player1;
    }

    public void switchPlayer() {
        // currentPlayer = if(currentPlayer == player1){currentPlayer = player2}
        //                  else currentPlayer = player1
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }
    // Hàm trả về kết quả khi nào game kết thúc
    // Có 3 cách kết thúc 1 game:
    // khi có 3 cell giống hệt nhau theo chiều dọc, chiều ngang hoặc theo đường chéo,
    // hoặc là board của game đã full không còn di chuyển được nữa (Lúc này ko ai là người chiến thắng cả)
    public boolean isGameEnded() {
        if (hasThreeSameOnHorizontalCells() || hasThreeSameOnVerticalCells()
                || hasThreeSameOnDiagonalCells()) {
            winners.setValue(currentPlayer);
            return true;
        }

        if (isBoardFull()) {
            winners.setValue(null);
            return true;
        }
        return false;
    }
    // Hàm kiểm tra xem có các giá trị giống nhau theo chiều dọc hay không
    private boolean hasThreeSameOnHorizontalCells() {
        Player.PlayerValue value = currentPlayer.value;

        return areEquals(value, cells[0][0], cells[1][0], cells[2][0])
                || areEquals(value, cells[0][1], cells[1][1], cells[2][1])
                || areEquals(value, cells[0][2], cells[1][2], cells[2][2]);
    }
    // Hàm kiểm tra xem có các giá trị giống nhau theo chiều ngang hay không
    public boolean hasThreeSameOnVerticalCells() {
        Player.PlayerValue value = currentPlayer.value;

        return areEquals(value, cells[0][0], cells[0][1], cells[0][2])
                || areEquals(value, cells[1][0], cells[1][1], cells[1][2])
                || areEquals(value, cells[2][0], cells[2][1], cells[2][2]);
    }
    // Hàm kiểm tra xem có các giá trị giống nhau theo đường chéo hay không
    public boolean hasThreeSameOnDiagonalCells() {
        Player.PlayerValue value = currentPlayer.value;

        return areEquals(value, cells[0][0], cells[1][1], cells[2][2])
                || areEquals(value, cells[0][2], cells[1][1], cells[2][0]);
    }
    // Hàm kiểm tra xem các ô đã được đánh dấu hết chưa
    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (cells[i][j] == null || cells[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    // Hàm kiểm tra xem có giá trị đã được đánh dấu tại ô đó chưa
    public boolean areEquals(Player.PlayerValue value, Cell... cells) {
        for (Cell cell : cells) {
            if (cell == null || cell.isEmpty() || cell.player != currentPlayer || cell.player.value != value) {
                return false;
            }
        }
        return true;
    }
    // hàm reset Game
    public void reset() {
        currentPlayer = player1;
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
    }
}
