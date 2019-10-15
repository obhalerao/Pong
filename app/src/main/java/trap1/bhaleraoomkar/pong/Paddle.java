package trap1.bhaleraoomkar.pong;

import android.graphics.RectF;

public class Paddle extends RectF {
    public float left;
    public float top;
    public float right;
    public float bottom;

    public int padType;

    private float pad_length = 0.2f;
    private float pad_width = 0.05f;
    private float four_pad_rate = 0.0075f;


    public Paddle(float l, float t, float r, float b){
        super(l, t, r, b);
    }

    public void change(boolean incr){
        if(incr){

        }
    }
}
