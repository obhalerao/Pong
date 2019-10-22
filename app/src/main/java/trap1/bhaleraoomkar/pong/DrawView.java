package trap1.bhaleraoomkar.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import static android.graphics.RectF.intersects;

public class DrawView extends View {
    private final int INF = 1000000007;
    private Paint paint=new Paint();
    private float ball_rate = 12.5f;
    private int x = INF;
    private int y = INF;
    private int radius = INF;
    public boolean incr_four_pad = false;
    public boolean decr_four_pad = false;
    private float pad_length = 0.2f;
    private float pad_width = 0.05f;
    public float fp = 0.5f;
    private float four_pad_rate = 0.0125f;
    public boolean gameOver = false;

    public int score = 0;



    Paddle pad1;
    Paddle pad2;
    Paddle pad3;
    Paddle pad4;
    Ball ball;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int right, int top, int bottom){
        setAllParams();
    }

    public void setAllParams(){
        score = 0;
        incr_four_pad = false;
        decr_four_pad = false;
        fp = .5f;
        x = (int)(getWidth()*.5f);
        y = (int)(getHeight()*.5f);
        radius = (int)(getWidth()*.033f);
        pad1 = new Paddle((fp - pad_length / 2) * getWidth(), 0.0f, (fp + pad_length / 2) * getWidth(),pad_width * getWidth(), 1, four_pad_rate, getWidth(), getHeight());
        pad2 = new Paddle( 0.0f, (fp-pad_length*((float)getWidth()/(float)getHeight())/2)*getHeight(), pad_width*getWidth(),(fp+pad_length*((float)getWidth()/(float)getHeight())/2)*getHeight(), 2, four_pad_rate, getWidth(), getHeight());
        pad3 = new Paddle((1-(fp+pad_length/2))*getWidth(),(float)getHeight()-pad_width*getWidth(), (1-(fp-pad_length/2))*getWidth(), (float)getHeight(), 3, four_pad_rate, getWidth(), getHeight());
        pad4 = new Paddle((float)getWidth()-pad_width*getWidth(), (1-(fp+pad_length*((float)getWidth()/(float)getHeight())/2))*getHeight(), (float)getWidth(), (1-(fp-pad_length*((float)getWidth()/(float)getHeight())/2))*getHeight(), 4, four_pad_rate, getWidth(), getHeight());
        ball  = new Ball(x, y, radius, ball_rate);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(gameOver){
            paint.setColor(Color.GRAY);//set paint to gray
            canvas.drawRect(getLeft(),0,getRight(),getBottom(),paint);//paint background gray
            paint.setTextSize(144);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(Color.BLACK);
            canvas.drawText("GAME OVER!", getWidth()*.5f, getHeight()*.25f, paint);
            canvas.drawText("Score: "+score, getWidth()*.5f, getHeight()*.5f, paint);
            paint.setTextSize(108);
            canvas.drawText("Click anywhere to reset.", getWidth()*.5f, getHeight()*.75f, paint);

        }else {


            paint.setColor(Color.GRAY);//set paint to gray
            canvas.drawRect(getLeft(), 0, getRight(), getBottom(), paint);//paint background gray

            paint.setTextSize(288);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(Color.BLACK);
            canvas.drawText("" + score, getWidth() * .5f, getHeight() * .5f, paint);

            pad1.draw(canvas);
            pad2.draw(canvas);
            pad3.draw(canvas);
            pad4.draw(canvas);
            ball.draw(canvas);

            if (intersects(pad1, ball)) {
                score += 1;
                ball.dy *= -1;
                ball.offsetTo(ball.left, getWidth() * pad_width);
                ball.update();
            }

            if (intersects(pad2, ball)) {
                score += 1;
                ball.dx *= -1;
                ball.offsetTo(getWidth() * pad_width, ball.top);
                ball.update();
            }

            if (intersects(pad3, ball)) {
                score += 1;
                ball.dy *= -1;
                ball.offsetTo(ball.left, getHeight() - (getWidth() * pad_width) - (2 * radius));
                ball.update();
            }

            if (intersects(pad4, ball)) {
                score += 1;
                ball.dx *= -1;
                ball.offsetTo(getWidth() * (1 - pad_width) - (2 * radius), ball.top);
                ball.update();
            }


            if (incr_four_pad) {
                if (fp <= (1.0 - pad_length / 2) + 0.03f) {
                    fp += four_pad_rate;
                    pad1.change(true);
                    pad2.change(true);
                    pad3.change(true);
                    pad4.change(true);
                }
            } else if (decr_four_pad) {
                if (fp >= (pad_length / 2) - 0.03f) {
                    fp -= four_pad_rate;
                    pad1.change(false);
                    pad2.change(false);
                    pad3.change(false);
                    pad4.change(false);
                }
            }
            ball.move();

            if (ball.outOfBounds(getWidth(), getHeight())) gameOver = true;
        }
        invalidate();  //redraws screen, invokes onDraw()
    }
}