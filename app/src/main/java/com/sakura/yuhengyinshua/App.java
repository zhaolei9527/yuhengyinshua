package com.sakura.yuhengyinshua;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sakura.yuhengyinshua.Utils.PausableThreadPoolExecutor;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * com.sakura.yuhengyinshua
 *
 * @author 赵磊
 * @date 2018/7/9
 * 功能描述：
 */
public class App extends Application {
    /**
     * 先创建一个请求队列，因为这个队列是全局的，所以在Application中声明这个队列
     */
    public static RequestQueue queues;
    public static PausableThreadPoolExecutor pausableThreadPoolExecutor;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        queues = Volley.newRequestQueue(getApplicationContext());
        pausableThreadPoolExecutor = new PausableThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
    }

    public static RequestQueue getQueues() {
        return queues;
    }

}
