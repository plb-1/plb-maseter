package com.example.plb.Utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

import com.example.plb.R;

/**
 * Created by rose on 2019/12/11.
 */

public class TimeCount extends CountDownTimer {
    Button timeBtn;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCount(long millisInFuture, long countDownInterval,Button button) {
        super(millisInFuture, countDownInterval);
        this.timeBtn = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timeBtn.setBackgroundColor(Color.parseColor("#f5f5f5"));
        timeBtn.setClickable(false);
        timeBtn.setText(millisUntilFinished / 1000 +"秒后重新发送");
    }

    @Override
    public void onFinish() {
        timeBtn.setText("重新获取验证码");
        timeBtn.setClickable(true);
        timeBtn.setBackgroundColor(Color.parseColor("#f7ede4"));
    }
}
