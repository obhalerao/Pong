package trap1.bhaleraoomkar.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    private final int INF = 1000000007;
    private Paint paint=new Paint();
    private int ball_rate = (int)Math.random()*4+3;
    private int x = INF, dX=ball_rate;//set intial x position and vertical speed
    private int y = INF, dY=ball_rate;//set initial y position and vertical speed
    private int radius = INF;
    public boolean incr_four_pad = false;
    public boolean decr_four_pad = false;
    private float pad_length = 0.2f;
    private float pad_width = 0.05f;
    public float four_pad = 0.5f;

    public int score = 0;



    Paddle pad1;
    Paddle pad2;
    Paddle pad3;
    Paddle pad4;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int right, int top, int bottom){
        float l, t, r, b;
        if(type == 1){ l = (fp - pad_length / 2) * width; t = 0.0f; r = (fp + pad_length / 2) * width; b = pad_width * width;}
        else if(type == 2){ l = 0.0f; t = (fp-pad_length*((float)width/(float)height)/2)*height; r = pad_width*width; b = (fp+pad_length*((float)width/(float)height)/2)*height;}
        else if(type == 3){ l = (1-(fp+pad_length/2))*width; t = (float)height-pad_width*width; r = (1-(fp-pad_length/2))*width; b = (float)height;}
        else if(type == 4){ l = (float)width-pad_width*width; t = (1-(fp+pad_length*((float)width/(float)height)/2))*height; r = (float)width; b =(1-(fp-pad_length*((float)width/(float)height)/2))*height;}
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(x == INF || y == INF || radius == INF){
            x = (int)(getWidth()*.5f);
            y = (int)(getHeight()*.5f);
            radius = (int)(getWidth()*.067f);
        }
        y+=dY;//increment y position
        x+=dX;
        super.onDraw(canvas);


        paint.setColor(Color.GRAY);//set paint to gray
        canvas.drawRect(getLeft(),0,getRight(),getBottom(),paint);//paint background gray

        paint.setTextSize(288);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLACK);
        canvas.drawText(""+score, getWidth()*.5f, getHeight()*.5f, paint);

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
        canvas.drawRect((1-(four_pad+pad_length/2))*getWidth(),
                (float)getHeight()-pad_width*getWidth(),
                (1-(four_pad-pad_length/2))*getWidth(),
                (float)getHeight(), paint);
        canvas.drawRect((float)getWidth()-pad_width*getWidth(),
                (1-(four_pad+pad_length*((float)getWidth()/(float)getHeight())/2))*getHeight(),
                (float)getWidth(),
                (1-(four_pad-pad_length*((float)getWidth()/(float)getHeight())/2))*getHeight(), paint);


        if(checkIntersection(x, y, radius, (four_pad-pad_length/2)*getWidth(), 0.0f, (four_pad+pad_length/2)*getWidth(), pad_width*getWidth())){
            dY=ball_rate;
            score++;
        }


        if(checkIntersection(x, y, radius, (1-(four_pad+pad_length/2))*getWidth(), (float)getHeight()-pad_width*getWidth(),(1-(four_pad-pad_length/2))*getWidth(), (float)getHeight())){
            dY=-1*ball_rate;
            score++;
        }

        if(checkIntersection(x, y, radius, 0.0f, (four_pad-pad_length*((float)getWidth()/(float)getHeight())/2)*getHeight(), pad_width*getWidth(), (four_pad+pad_length*((float)getWidth()/(float)getHeight())/2)*getHeight())){
            dX=ball_rate;
            score++;
        }

        if(checkIntersection(x, y, radius, (float)getWidth()-pad_width*getWidth(), (1-(four_pad+pad_length*((float)getWidth()/(float)getHeight())/2))*getHeight(), (float)getWidth(), (1-(four_pad-pad_length*((float)getWidth()/(float)getHeight())/2))*getHeight())){
            dX=-1*ball_rate;
            score++;
        }



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

    private boolean checkIntersection(float p1, float p2, float r, float x1, float y1, float x2, float y2){

        float width = Math.abs(x2-x1);
        float height = Math.abs(y2-y1);

        float circleDistx = Math.abs(p1-x1);
        float circleDisty = Math.abs(p2-y1);

        if (circleDistx > (width/2 + r)) { return false; }
        if (circleDisty > (height/2 + r)) { return false; }

        if (circleDistx <= (width/2)) { return true; }
        if (circleDisty <= (height/2)) { return true; }

        float cornerDistance_sq = (float)(Math.pow((circleDistx - width/2), 2.0) +
                Math.pow((circleDisty - height/2), 2));

        return (cornerDistance_sq < r*r);
    }
}