package com.example.plb.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.plb.R;
import com.example.plb.bean.ShopBean;
import com.example.plb.database.ShopDatabase;
import com.example.plb.fragment.DetailsShopFragment;
import com.example.plb.fragment.ShopFragment;
import com.example.plb.tdy.Sqlite.Constant;
import com.example.plb.tdy.Sqlite.MysqliteHelper;
import com.example.plb.tdy.Sqlite.bean.shopinfos;
import com.example.plb.tdy.Sqlite.util.DBManger;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 陈 on 2019/1/3.
 */

public class AddShopPopupwindow extends PopupWindow{
    private View view,view2;
    private ImageView imageView;
    private ImageView shop_img;//商品图片
    private TextView shop_name;//商品名称
    private TextView shop_beginSum;//商品起售数量
    private TextView shop_Price;//商品价格
    private TextView shop_Num;//商品可售数量
    private TextView shop_buyNum;//购买数量
    private ImageButton delete_shopNum;//减少数目
    private ImageButton add_shopNum;//增加数目
    private TextView shop_Sum;//商品购买数量
    private TextView num_tv;//底部商品购买数量
    private TextView money_tv;//商品总价
    private Button add_btn;//确认数目后加入进货单

    private ImageView shopcaricon;
    private TextView shopconunt;
    private long ll;
    DetailsShopFragment ds = new DetailsShopFragment ();
    int i =ds.minNum,mycount = 0;



    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];
    private RelativeLayout mRootRl;
    private MysqliteHelper helper;
    private SQLiteDatabase db = null;
    List<shopinfos> list;

    public AddShopPopupwindow(Activity context, View.OnClickListener itemsOnClick){
        super(context);
        initView(context, itemsOnClick);
    }

    /*TDY*/
    /*TDY*/
    private void initView(final Activity context, View.OnClickListener itemsOnClick) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.layout_addshop, null);
        view2 = view;
        initview();
        initData();
        delete_shopNum.setOnClickListener (new MyOnClickListener());
        add_shopNum.setOnClickListener (new MyOnClickListener());
        /*start tdy*/
        initSqlite();
        selectShopCount();
        /*end tdy*/
        add_btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                addGoodToCar(shop_img);
                  addShop();
//                ShopDatabase shopDatabase = new ShopDatabase ( view.getContext (),null,1 );
//                //获取连接
//                SQLiteDatabase database = shopDatabase.getReadableDatabase ();
//                ContentValues values = new ContentValues ( );
//                //values.clear ();
//                values.put ( "shop_img",ds.image );//图片地址
//                values.put ( "shop_name",ds.info );//商品名称
//                values.put ( "shop_wholesalePrice",ds.wholesalePrice );//商品单价
//                values.put ( "shop_buyNum",i );//购买数量
//                values.put ( "shop_follow",ds.checkd );//是否关注
//                ll = database.insert ( "shop_details",null,values );
//                database.close ();
//                Toast.makeText ( view.getContext (), "亲，在购物车等你哟~", Toast.LENGTH_SHORT ).show ();
//                dismiss ();
            }
        } );

        imageView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                //销毁弹出框
                dismiss();
                backgroundAlpha(context, 1f);
            }
        } );
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth( WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight( WindowManager.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置PopupWindow可触摸
        this.setTouchable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        // this.setAnimationStyle(R.style.select_anim);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha(context, 0.5f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(context, 1f);
            }
        });
    }



    private void addGoodToCar(ImageView imageView){
        final ImageView view = new ImageView(view2.getContext());
        view.setImageDrawable(imageView.getDrawable());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);

        mRootRl.addView(view, layoutParams);

        //二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLoc = new int[2];
        mRootRl.getLocationInWindow(parentLoc);

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        imageView.getLocationInWindow(startLoc);

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        shopcaricon.getLocationInWindow(endLoc);

        float startX = startLoc[0] - (parentLoc[0]) + imageView.getWidth()/2;
        float startY = startLoc[1] - parentLoc[1] + imageView.getHeight()/2;

        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = (endLoc[0]-20) - parentLoc[0] + imageView.getWidth() / 5;
        float toY = endLoc[1] - parentLoc[1];

        //开始绘制贝塞尔曲线
        Path path = new Path();
        path.moveTo(startX, startY);

        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, startY, toX, toY);
        mPathMeasure = new PathMeasure(path, false);
        //属性动画

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float)animation.getAnimatedValue();
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                view.setTranslationX(mCurrentPosition[0]);
                view.setTranslationY(mCurrentPosition[1]);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 购物车的数量加1
                mycount+=1;
                shopconunt.setText(""+mycount+"");
                // 把移动的图片imageview从父布局里移除
                mRootRl.removeView(view);
                //shopImg 开始一个放大动画
                Animation scaleAnim = AnimationUtils.loadAnimation(view2.getContext(), R.anim.scale);
                shopcaricon.startAnimation(scaleAnim);
                shopconunt.startAnimation(scaleAnim);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }
    private List<shopinfos> selectShopCount(){
        db = helper.getWritableDatabase();
        Cursor query = null;
        query = db.query(Constant.TABLE_NAME, null, Constant.ISPAYMENT + "=?",
                new String[]{"1"}, null, null, Constant.SHOPBUYNUM + " desc");
        list = DBManger.CursorTolsit(query);
        mycount = list.size();
        shopconunt.setText(mycount+"");
        db.close();
        return list;
    }


    /*start TDY*/
    private void initSqlite(){
        helper = DBManger.getMmysqliteHelper(view.getContext ());
        db = helper.getWritableDatabase();
        //Toast.makeText(view.getContext (),"创建或打开成功",Toast.LENGTH_SHORT).show();
    }

    private void addShop(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.NAME,ds.info);//商品名称
        values.put(Constant.SHOPIMG,R.mipmap.example_1);//图片地址
        values.put(Constant.SHOPBUYNUM,i);//购买数量
        values.put(Constant.SHOPPRICE,ds.wholesalePrice);//商品单价
        values.put(Constant.DELIVERYSTATUS,1);//发货状态
        values.put(Constant.ISPAYMENT,1);//交易状态
        long result=db.insert(Constant.TABLE_NAME,null,values);
        if(result>0) {
            selectShop();
        }else
            Toast.makeText(view.getContext (),"添加失败",Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void selectShop(){
        db = helper.getWritableDatabase();

        Cursor query = null;

        query = db.query(Constant.TABLE_NAME, null, null,
                null, null, null, Constant.SHOPBUYNUM + " desc");
        String stext = Showtext(query);

        db.close();

        if(stext!="" && stext.length()>0 && !"".equals(stext) && stext!=null){}
            //Toast.makeText(view.getContext (),"查询成功",Toast.LENGTH_SHORT).show();
    }

    public String Showtext(Cursor cursor){
        List<shopinfos> list = DBManger.CursorTolsit(cursor);

        String show="";

        for (shopinfos p:list){
            show+="\n"+p.toString();
            Log.e("shopinfos",p.toString());
        }
        Log.e("shopinfos",list.size()+"");
        return show;
    }
    /*end TDY*/


    private void initData() {
        Glide.with ( view.getContext () ).load ( ds.image ).into ( shop_img );
        shop_name.setText ( ds.info+"" );
        shop_beginSum.setText ( ds.minNum +""+ds.unit+"起批");
        shop_Price.setText (ds.wholesalePrice+"" );
        shop_Num.setText( ds.stocks +""+ds.unit+"可售");
        shop_Sum.setText ( ds.minNum+"" );
        num_tv.setText ( "共"+ds.minNum+"箱" );
        money_tv.setText ( "￥"+ds.minNum*ds.wholesalePrice );
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param v
     */

    void backgroundAlpha(Activity context, float v) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = v;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    private void initview() {
        imageView = view.findViewById(R.id.diss);
        shop_img = view.findViewById ( R.id.shop_img );
        shop_name = view.findViewById ( R.id.shop_name );
        shop_beginSum = view.findViewById ( R.id.shop_beginSum );
        shop_Price = view.findViewById ( R.id.shop_Price );
        shop_buyNum = view.findViewById ( R.id.shop_buyNum );
        delete_shopNum = view.findViewById ( R.id.delete_shopNum );
        add_shopNum = view.findViewById ( R.id.add_shopNum );
        shop_Num =view.findViewById ( R.id.shop_Num );
        shop_Sum = view.findViewById ( R.id.shop_Sum );
        num_tv = view.findViewById ( R.id.num_tv );
        money_tv = view.findViewById ( R.id.money_tv );
        add_btn = view.findViewById ( R.id.add_btn );
        mRootRl = view.findViewById(R.id.relative);
        shopcaricon = view.findViewById(R.id.shopcaricon);
        shopconunt = view.findViewById(R.id.shopconunt);
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId ()){
                case R.id.add_shopNum:
                    if(i>ds.stocks-1){
                        Toast.makeText ( view.getContext (),"商品最大售量为"+ds.stocks+""+ds.unit+"~",Toast.LENGTH_SHORT ).show ();
                    }else {
                        i++;
                    }
                    BigDecimal BD = new BigDecimal(i*ds.wholesalePrice);
                    BigDecimal resultBD = BD.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
                    shop_Sum.setText ( i+"" );
                    money_tv.setText ( "￥"+resultBD );
                    num_tv.setText ("共"+i+"箱" );
                    break;
                case R.id.delete_shopNum:
                    if (i<ds.minNum+1){
                        Toast.makeText ( view.getContext (),"商品最少起售为"+ds.minNum+""+ds.unit+"~",Toast.LENGTH_SHORT ).show ();
                    }else {
                        i--;
                    }
                    shop_Sum.setText ( i+"" );
                    num_tv.setText ( "共"+i+"箱" );
                    BigDecimal BDL = new BigDecimal(i*ds.wholesalePrice);
                    BigDecimal resultBDL = BDL.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
                    money_tv.setText ( "￥"+resultBDL);
                    break;
            }
        }
    }

}
