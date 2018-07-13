package com.sakura.yuhengyinshua.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.KeyEvent;

/**
 * com.sakuraphonebtc.View
 *
 * @author 赵磊
 * @date 2018/3/27
 * 功能描述：
 */
public class BackDiaLog extends Dialog {


    public BackDiaLog(@NonNull final Context context) {
        super(context);
        OnKeyListener keylistener = new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    try {
                        Activity context1 = (Activity) context;
                        context1.finish();
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    return false;
                }
            }
        };

        setOnKeyListener(keylistener);
        setCancelable(false);
    }

    public BackDiaLog(@NonNull final Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        OnKeyListener keylistener = new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    try {
                        Activity context1 = (Activity) context;
                        dismiss();
                        context1.finish();
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    return false;
                }
            }
        };

        setOnKeyListener(keylistener);
        setCancelable(false);
    }

    protected BackDiaLog(@NonNull final Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        OnKeyListener keylistener = new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    try {
                        Activity context1 = (Activity) context;
                        context1.finish();
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    return false;
                }
            }
        };

        setOnKeyListener(keylistener);
        setCancelable(false);
    }


}
