package trap1.bhaleraoomkar.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button leftButton;
    Button rightButton;
    DrawView canvas;



    View.OnTouchListener left = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                canvas.incr_four_pad = true;
                return true;
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                canvas.incr_four_pad = false;
                return true;
            }
            return false;
        }
    };

    View.OnTouchListener right = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                canvas.decr_four_pad = true;
                return true;
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                canvas.decr_four_pad = false;
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);
        canvas = findViewById(R.id.drawView);
        leftButton.setOnTouchListener(left);
        rightButton.setOnTouchListener(right);
    }


}
