package com.example.mvvmexample;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmexample.Model.Cell;
import com.example.mvvmexample.Model.Game;
import com.example.mvvmexample.Model.Player;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, Integer> cells;
    private Game game;

    public void init(String p1, String p2) {
        game = new Game(p1, p2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedAtCell(int r, int c) {
        Log.e("TAG", "onClickedAtCell: " + game.cells[r][c] );
        if (game.cells[r][c] == null) {
            game.cells[r][c] = new Cell(game.currentPlayer);
            int res = 0;
            if (game.currentPlayer.value == Player.PlayerValue.VALUE_X) {
                res = R.drawable.ic_baseline_clear_24;
            } else {
                res = R.drawable.ic_baseline_lens_24;
            }
            cells.put(String.valueOf(r) + String.valueOf(c), res);

            if (game.isGameEnded()) {
                resetGame();
            } else {
                game.switchPlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return game.winners;
    }

    public void resetGame() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                game.reset();
                cells.clear();
            }
        }, 1000);
    }
}
