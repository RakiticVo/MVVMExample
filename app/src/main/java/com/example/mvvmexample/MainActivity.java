package com.example.mvvmexample;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmexample.Model.Player;
import com.example.mvvmexample.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private static final String NO_WINNER = "No One";

    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    // Tạo ra 2 người chơi bằng cách truyền tên họ đến ViewModel
    public void initDataBinding() {
        // ActivityMainBinding: Lớp này được tự động tạo ra thông qua file xml layout activity_main
        //  Nó có nhiệm vụ là giữ tất cả các thuộc tính ở xml file
        //  Trường hợp này chúng ta có biến gameViewModel
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        // Muốn tên gì thì truyền vào
        gameViewModel.init("P1", "P2");
        // Đăng ký và kết nối đến ViewModel
        // Hàm này sẽ gán gameViewModel của chúng ta đến gameViewModel trên activity_main.xml
        activityMainBinding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();
    }
    // Hàm phản ứng các thông báo từ ViewModel
    public void setUpOnGameEndListener() {
        // Hàm này sẽ quan sát về người chơi chiến thắng thông qua LiveData ở ViewModel
        gameViewModel.getWinner().observe(this, new Observer<Player>() {
            @Override
            public void onChanged(Player player) {
                // Khi kết thúc game, giá trị của winner sẽ thay đổi
                // từ đó sẽ dẫn đến hàm onGameWinnerChange(player)
                onGameWinnerChange(player);
            }
        });
    }
    // VisibleForTesting: Biểu thị rằng lớp, phương thức hoặc trường được nới lỏng khả năng hiển thị
    // để nó được hiển thị rộng rãi hơn mức cần thiết để làm cho code có thể kiểm tra được.
    @VisibleForTesting
    private void onGameWinnerChange(Player winner) {
        String winnerName = (winner != null &&
                (winner.name != null && !winner.name.isEmpty())) ? winner.name : NO_WINNER;
        Toast.makeText(this, "The Winner is: " + winnerName, Toast.LENGTH_SHORT).show();
    }
}