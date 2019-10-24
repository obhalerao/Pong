package trap1.bhaleraoomkar.pong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Ball extends RectF {

    private float ball_rate;
    private int color = Color.RED;
    public float dx, dy;
    public float x;
    public float y;
    public float r;

    private static final int BMP_COLUMNS = 1;
    private static final int BMP_ROWS = 1;

    private int iconWidth, iconHeight;

    public Bitmap bitmap;


    public Ball(float x, float y, float r, float rate) {
        super(x - r, y - r, x + r, y + r);
        this.x = x;
        this.y = y;
        this.r = r;
        ball_rate = rate;
        dx = (float)(Math.random()*ball_rate)*(Math.random() < 0.5 ? 1 : -1);
        dy = (float)Math.sqrt((ball_rate)*(ball_rate) - dx*dx)*(Math.random() < 0.5 ? 1 : -1);
    }

    public void move(){
        x+=dx;
        y+=dy;
        offsetTo(x-r, y-r);
    }

    public void update(){
        x = left+r;
        y = top+r;
    }

    public void draw(Canvas canvas){
        if(bitmap == null) {
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawRect(this, paint);
        }else{
//            iconWidth = bitmap.getWidth() / BMP_COLUMNS;//calculate width of 1 image
//            iconHeight = bitmap.getHeight() / BMP_ROWS; //calculate height of 1 image
//            int srcX = 0;       //set x of source rectangle inside of bitmap based on current frame
//            int srcY = 0; //set y to row of bitmap based on direction
//            Rect src = new Rect(srcX, srcY, srcX + iconWidth, srcY + iconHeight);  //defines the rectangle inside of heroBmp to displayed
            canvas.drawBitmap(bitmap,null, this,null); //draw an image
        }
    }

    public boolean outOfBounds(int w, int h){
        return (right < 0 || left > w || bottom < 0 || top > h);
    }
}
