package cn.cnu.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ContentFragment contentFragment;
    private Button1Fragment button1Fragment;
    private Button2Fragment button2Fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {

        // 设置默认的Fragment
        setDefaultFragment();
        // 初始化控件和声明事件
        Button button2 = (Button) findViewById(R.id.button2);
        Button button1 = (Button) findViewById(R.id.button1);
        button2.setOnClickListener(this);
        button1.setOnClickListener(this);

    }


    private void setDefaultFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        contentFragment = new ContentFragment();
        transaction.replace(R.id.content, contentFragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.button2:
                if (button2Fragment == null) {
                    button2Fragment = new Button2Fragment();
                }
                // 使用当前Fragment的布局替代content的控件
                transaction.replace(R.id.content, button2Fragment);
                break;
            case R.id.button1:
                if (button1Fragment == null) {
                    button1Fragment = new Button1Fragment();
                }
                transaction.replace(R.id.content, button1Fragment);
                break;
        }
        //添加返回栈,按back键可以返回上一个碎片
        transaction.addToBackStack(null);
        // 事务提交
        transaction.commit();
    }
}
