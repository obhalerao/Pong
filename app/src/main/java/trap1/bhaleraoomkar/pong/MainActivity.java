package trap1.bhaleraoomkar.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    DrawView canvas;


    View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            canvas.setAllParams();
            canvas.setOnClickListener(null);
            canvas.setOnTouchListener(respond);

        }
    };

    View.OnTouchListener respond = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(canvas.gameOver){
                canvas.setOnTouchListener(null);
                canvas.setOnClickListener(reset);
                return false;
            }
            if(event.getX() < canvas.getWidth()/2){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    canvas.incr_four_pad = true;
                    canvas.decr_four_pad = false;
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    canvas.incr_four_pad = false;
                    canvas.decr_four_pad = false;
                    return true;
                }
            }else {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    canvas.decr_four_pad = true;
                    canvas.incr_four_pad = false;
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    canvas.decr_four_pad = false;
                    canvas.incr_four_pad = false;
                    return true;
                }
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvas = findViewById(R.id.drawView);
        canvas.setOnTouchListener(respond);
    }


}
