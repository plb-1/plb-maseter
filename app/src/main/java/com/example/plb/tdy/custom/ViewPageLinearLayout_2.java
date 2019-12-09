package com.example.plb.tdy.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 *
 * Created by hp on 2018/11/12.
 */

public class ViewPageLinearLayout_2 extends LinearLayout {
    private Paint mPaint;

    private Path mPath;//用来画三角形的三条线

    private int mTriangleWidth,mTriangleheight;//三角形宽高

    private static  final float RADIO_TRIANGLE_WIDTH=1/6F;//三角形与tab宽度的比例
    private   final int TRIANGLE_WIDTH_MAX = (int) (getScreenwidth() / 3*RADIO_TRIANGLE_WIDTH);//设置三角形的最大底边宽度

    private int mInitranslationX;//偏移位置

    private int mtranslationX;//移动时的偏移量

    private int mtabvisiblecount;

    private static  final int COUNT_DEFAULT_TAB=4;

    List<String> mtitles;

    public ViewPageLinearLayout_2(Context context) {
        this(context,null);
    }

    public ViewPageLinearLayout_2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mtabvisiblecount = 2;
        if(mtabvisiblecount<0){
            mtabvisiblecount = COUNT_DEFAULT_TAB;
        }
        Log.e("mtabvisiblecount", "ViewPageLinearLayout_3: "+mtabvisiblecount);
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#0f9d58"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));//圆角
    }

    /**
     * 设置可见tab的数量
     * @param count
     */
    public void setVisibleTabcount(int count){
        mtabvisiblecount = count;
    }

    /**
     * 设置三角形的宽度和初始偏移量
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w/mtabvisiblecount*RADIO_TRIANGLE_WIDTH);
        mTriangleWidth = Math.min(mTriangleWidth,TRIANGLE_WIDTH_MAX);//在最大值和当前值中调一个最小的
        mInitranslationX = w/mtabvisiblecount/2-mTriangleWidth/2;
        initTringle();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mInitranslationX+mtranslationX,getHeight()+2);
        canvas.drawPath(mPath,mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int cCount = getChildCount();
        if (cCount==0)
            return;
        else{
            for(int i=0;i<cCount;i++){
                View view = getChildAt(i);
                LayoutParams lp= (LayoutParams) view.getLayoutParams();
                lp.weight=0;
                lp.width = getScreenwidth()/mtabvisiblecount;
            }
        }

        setItemEvent();
    }

    /**
     * 初始化三角形
     */
    private void initTringle() {
        mTriangleheight=mTriangleWidth/2;
        mPath = new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(mTriangleWidth,0);
        mPath.lineTo(mTriangleWidth/2,-mTriangleheight);
        mPath.close();
    }

    /**
     * 指示器跟随手指滚动
     * @param position
     * @param Offset 偏移量
     */
    public void scroll(int position, float Offset) {
        int tabWidth = getWidth()/mtabvisiblecount; //选项标题的宽度
        mtranslationX = (int) (tabWidth * (Offset + position));

        if(mtabvisiblecount!=1){
            // 容器移动 当tab处于显示选项的最后一个时
            // getChildCount() > mtabvisiblecount 当选项大于显示的选项数时
            if(position >= (mtabvisiblecount-2) && Offset>0 && getChildCount() > mtabvisiblecount){
                this.scrollTo((int) ((position-(mtabvisiblecount-2)) * tabWidth + tabWidth * Offset),0);
            }
        }else{
            this.scroll((int) (position * tabWidth + tabWidth*Offset),0);
        }
        invalidate();//重绘
    }


    /**
     * 设置显示的Tab
     * @param titles
     */
    public void setTabItemTitles(List<String> titles){
        if(titles.size()>0&&titles!=null){
            this.removeAllViews();
            mtitles = titles;
            for(String string:mtitles){
                addView(createTextview(string));
            }
        }

        setItemEvent();
    }


    private static int COLOR_TEXT = 0xCCCCCC;
    private static int COLOR_TEXT_HIGHT = 0xFFFFFFFF;
    public View createTextview(String title){
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        lp.width = getScreenwidth()/mtabvisiblecount;
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        lp.setMargins(0,15,0,15);
        textView.setTextColor(Color.WHITE);
        textView.setLayoutParams(lp);
        return textView;
    }


    /**
     * 外部的监听接口
     * 与 setOnPageChangeListener 一样
     */
    public interface PageChangeListener{

        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    public PageChangeListener mListener;

    private ViewPager mViewPager;

    public void setPageChangeListener(PageChangeListener listener){
        this.mListener = listener;
    }
    /**
     *
     * @param viewPager
     * @param pos 当前显示的tab
     */
    public void setViewPager(ViewPager viewPager,int pos){
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // tabwidth*positionOffset + position*tabwidth 指示标志的偏移量
                scroll(position,positionOffset);

                if(mListener!=null){
                    mListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }
            @Override
            public void onPageSelected(int position) {
                if(mListener!=null){
                    mListener.onPageSelected(position);
                }
                height_liang(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                if(mListener!=null){
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });
        mViewPager.setCurrentItem(pos);//当前显示的tab
        height_liang(pos);
    }


    /**
     * 让其他选项的文本颜色低亮
     * **/
    public void restesTextcolor(){
        for(int i=0;i<getChildCount();i++){
            View view = getChildAt(i);
            if(view instanceof TextView)
                ((TextView) view).setTextColor(Color.BLACK);
        }
    }
    /**
     *
     * @param pos 需要高亮的文本
     */
    private void height_liang(int pos){
        restesTextcolor();
        View view = getChildAt(pos);
        if(view instanceof TextView)
            ((TextView) view).setTextColor(Color.rgb(15,157,88));
    }

    /**
     * 设置tab的点击事件
     */
    private void setItemEvent(){
        int cCount = getChildCount();//获取所有子控件的总数
        for(int i=0;i<cCount;i++){
            final int j = i;
            View view = getChildAt(i);//获取第几个子控件 当选中tab标题时跳转到指定tab
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(j);//设置显示的tab
                }
            });
        }
    }


    /**
     * 返回屏幕宽度
     * @return
     */
    public int getScreenwidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrice = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrice);
        return outMetrice.widthPixels;
    }
}
