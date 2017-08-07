package com.example.xiyou3g.playxiyou.MyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.xiyou3g.playxiyou.R;

/**
 * Created by Lance on 2017/3/18.
 */

public class RoundImageView extends ImageView {

    private int type;                                           //ImageView的类型；
    private static final int TYPE_CIRCLE = 0;                   //圆形图片;
    private static final int TYPE_ROUND = 1;                    //圆角图片;
    private static final int BORDER_RADIUS_DEFAULT = 10;        //默认圆角宽度;
    private int mBorderRadius;                                  //设置圆角宽度;
    private Paint mPaint;                                       //画笔；
    private int mRadius;                                        //半径;
    private Matrix mMatrix;                                     //缩放矩阵;
    private BitmapShader mBitmapshader;                         //渲染器，使用图片填充形状;
    private  int mWidth;                                        //宽度;
    private RectF mRectF;                                       //圆角范围;


    public RoundImageView(Context context) {
        this(context,null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置画笔属性;
        mMatrix = new Matrix();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //获取自定义属性值;
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundImageView,defStyleAttr,0);
        int count = array.getIndexCount();
        for(int i = 0;i<count;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.RoundImageView_borderRadius:
                    //获取圆角大小;
                    mBorderRadius = array.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,BORDER_RADIUS_DEFAULT,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.RoundImageView_imageType:
                    //获取ImageView的类型;
                    type = array.getInt(R.styleable.RoundImageView_imageType,TYPE_CIRCLE);
                    break;
            }
        }
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //如果是圆形，则强制宽高一致，一最小值为准;
        if(type == TYPE_CIRCLE){
            mWidth = Math.min(getMeasuredWidth(),getMeasuredHeight());
            mRadius = mWidth / 2;
            setMeasuredDimension(mWidth,mWidth);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(getDrawable() == null){
            return ;
        }
        //设置渲染器;
        setShader();
        if(type == TYPE_ROUND){
            canvas.drawRoundRect(mRectF,mBorderRadius,mBorderRadius,mPaint);
        }else{
            canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
        }
    }

    private void setShader() {
        Drawable drawable = getDrawable();
        if(drawable == null){
            return ;
        }
        Bitmap bitmap = drawableToBitmap(drawable);
        mBitmapshader  = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if(type == TYPE_ROUND){
            scale = Math.max(getWidth()*1.0f / bitmap.getWidth(),getHeight()*1.0f / bitmap.getHeight());
        }else  if(type == TYPE_CIRCLE){
            //取最小值，如果取最大值的话，则不能覆盖view;
            int bitmapWidth = Math.min(bitmap.getWidth(),getHeight());
            scale = mWidth * 1.0f / bitmapWidth;
        }
        mMatrix.setScale(scale,scale);
        mBitmapshader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapshader);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if(drawable instanceof BitmapDrawable){
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        //创建画布;
        Bitmap bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,w,h);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF  = new RectF(0,0,getWidth(),getHeight());
    }

    //对外公布设置borderRadius 的方法;
    public void setBorderRadius(int borderRadius){
        int pxValue = dpTodp(borderRadius);
        if(this.mBorderRadius!= pxValue){
            this.mBorderRadius = pxValue;
            //这时候不需要父布局的onLayout，只需调用onDraw即可;
            invalidate();
        }
    }

    //对外公布设置形状的方法;
    public void setType(int type){
        if(this.type!=type){
            this.type = type;
            if(this.type != TYPE_CIRCLE && this.type != TYPE_ROUND){
                this.type = TYPE_CIRCLE;
            }
            //这个时候改变形状了，就需要调用父布局的onLayout ,那么此 view 的onMeasure方法也会被调用;
            requestLayout();
        }
    }

    public int dpTodp(int val){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,val,getResources().getDisplayMetrics());
    }
}
