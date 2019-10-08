package trap1.bhaleraoomkar.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    private final int INF = 1000000007;
    private Paint paint=new Paint();
    private int x = INF, dX=1;//set intial x position and vertical speed
    private int y = INF, dY=1;//set initial y position and vertical speed
    private int radius = INF;
    public boolean incr_four_pad = false;
    public boolean decr_four_pad = false;

    private float four_pad = 0.5f;

    private float pad_length = 0.2f;
    private float pad_width = 0.05f;
    private float four_pad_rate = 0.0075f;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(x == INF || y == INF || radius == INF){
            x = (int)(getWidth()*.5f);
            y = (int)(getHeight()*.5f);
            radius = (int)(getWidth()*.067f);
        }
        super.onDraw(canvas);
        paint.setColor(Color.GRAY);//set paint to gray
        canvas.drawRect(getLeft(),0,getRight(),getBottom(),paint);//paint background gray
        paint.setColor(Color.RED);//set paint to red
        //draw red circle
        canvas.drawCircle(x,y,radius,paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect((four_pad-pad_length/2)*getWidth(),
                0.0f,
                (four_pad+pad_length/2)*getWidth(),
                pad_width*getWidth(), paint);
        canvas.drawRect(0.0f,
                (four_pad-pad_length*((float)getWidth()/(float)getHeight())/2)*getHeight(),
                pad_width*getWidth(),
                (four_pad+pad_length*((float)getWidth()/(float)getHeight())/2)*getHeight(), paint);
        canvas.drawRect((1-(four_pad-pad_length/2))*getWidth(),
                (float)getHeight()-pad_width*getWidth(),
                (1-(four_pad+pad_length/2))*getWidth(),
                (float)getHeight(), paint);
        canvas.drawRect((float)getWidth()-pad_width*getWidth(),
                (1-(four_pad-pad_length*((float)getWidth()/(float)getHeight())/2))*getHeight(),
                (float)getWidth(),
                (1-(four_pad+pad_length*((float)getWidth()/(float)getHeight())/2))*getHeight(), paint);
        y+=dY;//increment y position
        x+=dX;
        if(incr_four_pad){
            four_pad+=four_pad_rate;
            if(four_pad > (1.0-pad_length/2)+0.01f) four_pad = (1-pad_length/2)+0.01f;
        }
        else if(decr_four_pad){
            four_pad-=four_pad_rate;
            if(four_pad < (pad_length/2)-0.01f) four_pad = (pad_length/2)-0.01f;
        }

        invalidate();  //redraws screen, invokes onDraw()
    }
}