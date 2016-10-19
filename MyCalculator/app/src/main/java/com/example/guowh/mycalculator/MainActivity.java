package com.example.guowh.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.nio.CharBuffer;

public class MainActivity extends AppCompatActivity {

    private double number_in = 0;
    private double number_out = 0;
    private double result = 0;
    private String string_in = null;
    private String string_out = null;
    private String string_what = "+";//默认为+号
    private String string_zhuanhuan = "";//转换后的字符串


    //输入数判断
    private void initialize(TextView tv_in) {
        if (tv_in.getText().equals("0") || tv_in.getText().equals("输入区                    开发者"))
            tv_in.setText("");
        if (tv_in.getText().equals("-0"))
            tv_in.setText("-");
        if (tv_in.getText().equals(number_out + string_what + number_in)) {
            tv_in.setText("");
            number_in = 0;
            number_out = 0;
            string_what = "+";
        }
    }

    //计算数判断
    private void myOperation(TextView tv_in, TextView tv_out, Button btn) {
        if (tv_out.getText().equals("被除数不能为零！") || tv_in.getText().equals("输入区                    开发者"))
            tv_in.setText("");
        try {
            if (tv_in.getText().equals("")) {
                //输入区未改变，什么都不做
            } else if (tv_in.getText().equals(number_out + string_what + number_in)) {
                string_what = btn.getText() + "";
                tv_out.setText(result + string_what);
                number_out = result;
                tv_in.setText("");
            } else {
                string_what = btn.getText() + "";
                string_out = tv_in.getText() + "";
                tv_out.setText(string_out + string_what);
                number_out = Double.valueOf(tv_in.getText() + "");
                tv_in.setText("");
            }

        } catch (Exception e) {

        }

    }

    //进制转换算法(整数部分)
    void DtoM_int(int n, int r) {
        int m;
        char ch;
        if (n > 0) {
            m = n % r;
            n = n / r;
            DtoM_int(n, r);
            if (m < 10)
                ch = (char) ('0' + m);
            else
                ch = (char) ('A' + m - 10);
            string_zhuanhuan = string_zhuanhuan + ch;
        }
    }

    //进制转换算法(小数部分)
    void DtoM_double(double n, int r) {
        int m;
        char ch;
        for (int i = 0; i < 10; i++) {
            if(n<0.00000001)
                break;
            n = n * r;
            m = (int) n;
            n = n - m;
            if (m < 10)
                ch = (char) ('0' + m);
            else
                ch = (char) ('A' + m - 10);
            string_zhuanhuan = string_zhuanhuan + ch;
        }
    }

    //进制转换细节
    void Change(int jinzhi, TextView tv_in, TextView tv_out) {
        if (tv_in.getText().equals(number_out + string_what + number_in)) {
            int zhuanhuan = (int) result;
            if(zhuanhuan==0)
                string_zhuanhuan = string_zhuanhuan + '0';
            else
                DtoM_int(zhuanhuan, jinzhi);
            string_zhuanhuan = string_zhuanhuan + ".";
            DtoM_double(result - zhuanhuan, jinzhi);
            tv_out.setText("=  " + string_zhuanhuan + "  (" + jinzhi + "进制)");
        } else if (tv_out.getText().equals("") || tv_out.getText().equals("被除数不能为零！") || tv_in.getText().equals("输入区                    开发者")) {
            //什么都不做
        } else {
            double input = Double.valueOf(tv_in.getText().toString());
            int zhuanhuan = (int) input;
            if(zhuanhuan==0)
                string_zhuanhuan = string_zhuanhuan + '0';
            else
                DtoM_int(zhuanhuan, jinzhi);
            string_zhuanhuan = string_zhuanhuan + ".";
            DtoM_double(input - zhuanhuan, jinzhi);
            tv_out.setText("=  " + string_zhuanhuan + "  (" + jinzhi + "进制)");
        }
        string_zhuanhuan = "";//重置为空
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv_in = (TextView) findViewById(R.id.textView_in);//获取输入文本域资源
        final TextView tv_out = (TextView) findViewById(R.id.textView_out);//获取输出文本域资源
        final Button b_0 = (Button) findViewById(R.id.button_0);//获取0按钮资源
        final Button b_1 = (Button) findViewById(R.id.button_1);//获取1按钮资源
        final Button b_2 = (Button) findViewById(R.id.button_2);//获取2按钮资源
        final Button b_3 = (Button) findViewById(R.id.button_3);//获取3按钮资源
        final Button b_4 = (Button) findViewById(R.id.button_4);//获取4按钮资源
        final Button b_5 = (Button) findViewById(R.id.button_5);//获取5按钮资源
        final Button b_6 = (Button) findViewById(R.id.button_6);//获取6按钮资源
        final Button b_7 = (Button) findViewById(R.id.button_7);//获取7按钮资源
        final Button b_8 = (Button) findViewById(R.id.button_8);//获取8按钮资源
        final Button b_9 = (Button) findViewById(R.id.button_9);//获取9按钮资源
        final Button b_add = (Button) findViewById(R.id.button_add);//获取+按钮资源
        final Button b_sub = (Button) findViewById(R.id.button_subtract);//获取-按钮资源
        final Button b_mul = (Button) findViewById(R.id.button_multiplication);//获取x按钮资源
        final Button b_div = (Button) findViewById(R.id.button_division);//获取÷按钮资源
        final Button b_equ = (Button) findViewById(R.id.button_equal);//获取=按钮资源
        final Button b_poi = (Button) findViewById(R.id.button_point);//获取小数点按钮资源
        final Button b_neg = (Button) findViewById(R.id.button_negative);//获取正负号按钮资源
        final Button b_C = (Button) findViewById(R.id.button_C);//获取归零按钮资源
        final Button b_CE = (Button) findViewById(R.id.button_CE);//获取清空按钮资源
        final Button b_to2 = (Button) findViewById(R.id.button_to2);//获取转换为2进制按钮资源
        final Button b_to8 = (Button) findViewById(R.id.button_to8);//获取转换为8进制按钮资源
        final Button b_to10 = (Button) findViewById(R.id.button_to10);//获取转换为10进制按钮资源
        final Button b_to16 = (Button) findViewById(R.id.button_to16);//获取转换为16进制按钮资源

        b_0.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_0.getText();
                tv_in.setText(string_in);
            }
        });
        b_1.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_1.getText();
                tv_in.setText(string_in);
            }
        });
        b_2.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_2.getText();
                tv_in.setText(string_in);
            }
        });
        b_3.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_3.getText();
                tv_in.setText(string_in);
            }
        });
        b_4.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_4.getText();
                tv_in.setText(string_in);
            }
        });
        b_5.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_5.getText();
                tv_in.setText(string_in);
            }
        });
        b_6.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_6.getText();
                tv_in.setText(string_in);
            }
        });
        b_7.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_7.getText();
                tv_in.setText(string_in);
            }
        });
        b_8.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_8.getText();
                tv_in.setText(string_in);
            }
        });
        b_9.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                initialize(tv_in);
                string_in = tv_in.getText() + "" + b_9.getText();
                tv_in.setText(string_in);
            }
        });
        b_poi.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                if (tv_in.getText().equals("输入区                    开发者"))
                    tv_in.setText("0");
                string_in = tv_in.getText() + "" + b_poi.getText();
                tv_in.setText(string_in);
                b_poi.setEnabled(false);
            }
        });
        b_neg.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                if (tv_in.getText().equals("") || tv_in.getText().equals("输入区                    开发者"))
                    tv_in.setText("0");
                string_in = tv_in.getText() + "";
                if (string_in.substring(0, 1).equals("-"))
                    string_in = string_in.substring(1);
                else
                    string_in = "-" + string_in;
                tv_in.setText(string_in);
            }
        });

        b_add.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                myOperation(tv_in, tv_out, b_add);
                b_poi.setEnabled(true);
                b_neg.setEnabled(true);
            }
        });
        b_sub.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                myOperation(tv_in, tv_out, b_sub);
                b_poi.setEnabled(true);
                b_neg.setEnabled(true);
            }
        });
        b_mul.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                myOperation(tv_in, tv_out, b_mul);
                b_poi.setEnabled(true);
                b_neg.setEnabled(true);
            }
        });
        b_div.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                myOperation(tv_in, tv_out, b_div);
                b_poi.setEnabled(true);
                b_neg.setEnabled(true);
            }
        });

        b_equ.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                if (tv_in.getText().equals(""))
                    tv_in.setText("0");
                try {
                    number_in = Double.valueOf(tv_in.getText() + "");
                    tv_in.setText(number_out + string_what + number_in);
                    if (string_what.equals("+"))
                        result = number_out + number_in;
                    if (string_what.equals("-"))
                        result = number_out - number_in;
                    if (string_what.equals("x"))
                        result = number_out * number_in;
                    tv_out.setText("=  " + String.valueOf(result));
                    if (string_what.equals("÷"))
                        if (number_in != 0) {
                            result = number_out / number_in;
                            tv_out.setText("=  " + String.valueOf(result));
                        } else
                            tv_out.setText("被除数不能为零！");
                } catch (Exception e) {
                }
                b_poi.setEnabled(false);
                b_neg.setEnabled(false);
            }
        });
        b_C.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                tv_in.setText("输入区                    开发者");
                tv_out.setText("输出区                    郭文浩");
                number_in = 0;
                number_out = 0;
                result = 0;
                b_poi.setEnabled(true);
                b_neg.setEnabled(true);
                string_what = "+";
            }
        });

        b_CE.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                tv_in.setText("0");
                b_poi.setEnabled(true);
                b_neg.setEnabled(true);
            }
        });
        b_to2.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                Change(2, tv_in, tv_out);
            }
        });
        b_to8.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                Change(8, tv_in, tv_out);
            }
        });
        b_to10.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                Change(10, tv_in, tv_out);
            }
        });
        b_to16.setOnClickListener(new View.OnClickListener() {//创建监听
            @Override
            public void onClick(View view) {
                Change(16, tv_in, tv_out);
            }
        });
    }
}
