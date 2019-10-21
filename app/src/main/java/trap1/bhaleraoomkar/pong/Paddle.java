package trap1.bhaleraoomkar.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


public class Paddle extends RectF {


    public int padType;
    private int color = Color.BLUE;


    private float pad_length = 0.2f;
    private float pad_width = 0.05f;
    public int dx = 0;
    public int dy = 0;


    public Paddle(float l, float t, float r, float b, int type, float four_pad_rate, int w, int h){
        super(l, t, r, b);
        padType = type;

        if(padType == 1) dx = (int)(four_pad_rate*w);
        if(padType == 2) dy = (int)(four_pad_rate*h);
        if(padType == 3) dx = (int)(four_pad_rate*w*-1);
        if(padType == 4) dy = (int)(four_pad_rate*h*-1);

    }

    public void change(boolean incr){
        if(incr){
            offset(dx, dy);
        }else{
            offset(-1*dx, -1*dy);
        }
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(this, paint);
    }



}
