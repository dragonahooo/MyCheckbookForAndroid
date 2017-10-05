package com.ho.mycheckbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnDOT;
    private Button btnBACKSPACE;
    private Button btnCancel;
    private TextView input;

    private TextView showEN;
    private TextView showCN;

    private String _numStr = "0";
    private String _decStr = "";
    private String _dotStr = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        showEN = (TextView)findViewById(R.id.showEN);
        showCN = (TextView)findViewById(R.id.showCN);

        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btnDOT = (Button)findViewById(R.id.btnDOT);
        btnBACKSPACE = (Button)findViewById(R.id.btnBACKSPACE);

        input = (TextView)findViewById(R.id.input);
        btnCancel = (Button)findViewById(R.id.btnCancelInput);
        btnCancel.setVisibility(View.INVISIBLE);

        clickListener();

        initUI();
        initScreen();
    }

    public void clickListener()
    {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btnDOT.setOnClickListener(this);
        btnBACKSPACE.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btn0:
                addNum(0);
                break;
            case R.id.btn1:
                addNum(1);
                break;
            case R.id.btn2:
                addNum(2);
                break;
            case R.id.btn3:
                addNum(3);
                break;
            case R.id.btn4:
                addNum(4);
                break;
            case R.id.btn5:
                addNum(5);
                break;
            case R.id.btn6:
                addNum(6);
                break;
            case R.id.btn7:
                addNum(7);
                break;
            case R.id.btn8:
                addNum(8);
                break;
            case R.id.btn9:
                addNum(9);
                break;
            case R.id.btnDOT:
                addDot();
                break;
            case R.id.btnBACKSPACE:
                removeLastStr();
                break;
            case R.id.btnCancelInput:
                initUI();
                break;
            default:
                //do nothing
                break;
        }
    }

    void initScreen()
    {
        //iphone se 320 568
        //iphone 7 375 667
        //let modelName = device.modelName
//        Double screenHeight = main.bounds.height;
//        if(screenHeight <= 568)
//        {
//            layCNHeight.constant = 100;
//            showEN.font = UIFont.systemFont(ofSize: 14)//14;
//            showCN.font = UIFont.systemFont(ofSize: 20)//14;20;
//            input.font = UIFont.systemFont(ofSize: 45)//45;
//            setBtnFontSize( 24);
//        }
//        else
//        {
//            layCNHeight.constant = 140;
//            showEN.font = UIFont.systemFont(ofSize: 20)//20;
//            showCN.font = UIFont.systemFont(ofSize: 24)//24;
//            input.font = UIFont.systemFont(ofSize: 60)//60;
//            setBtnFontSize( 30);
//        }

    }

    void setBtnFontSize (float value){
        btn0.setTextSize(value);
        btn1.setTextSize(value);
        btn2.setTextSize(value);
        btn3.setTextSize(value);
        btn4.setTextSize(value);
        btn5.setTextSize(value);
        btn6.setTextSize(value);
        btn7.setTextSize(value);
        btn8.setTextSize(value);
        btn9.setTextSize(value);
        btn0.setTextSize(value);
        btnDOT.setTextSize(value);
        btnBACKSPACE.setTextSize(value);
    }

    void addNum(int value)
    {
        if(_dotStr == "")
        {
            if(_numStr.length() >= 12)//整数太长会报错 千亿
            {
                return;
            }
            if(_numStr == "0")
            {
                if(value == 0)
                {
                    return;
                }
                _numStr = "" + value;
            }
            else
            {
                _numStr += value;
            }
        }
        else
        {
            if(_decStr.length() >= 2)//只限2位小数
            {
                return;
            }
            _decStr += value;
        }
        showWords();
    }

    void addDot()
    {
        if(_dotStr == "")//在小数模式中，不能无止尽的加 .
        {
            _dotStr = ".";
            showWords();
        }
    }

    void removeLastStr()
    {
        if(_dotStr == ".")
        {
            if(_decStr.length() == 0)//当没有小数的时候
            {
                _dotStr = "";//移除小数点
                if(_numStr == "0")
                {
                    initUI();
                }
                else
                {
                    showWords();
                }
            }
            else
            {
                _decStr = _decStr.substring(0, _decStr.length()-1);//移除最后一个小数
                showWords();
            }
        }
        else
        {
            if(_numStr.length() == 0 || _numStr.length() == 1)
            {
                _numStr = "0";
                initUI();
            }
            else
            {
                _numStr = _numStr.substring(0, _numStr.length()-1);
                showWords();
            }
        }
    }

    void showWords()
    {
        System.out.println(_numStr + "  " + _dotStr + "  " + _decStr);
        this.btnCancel.setVisibility(View.VISIBLE);
        this.input.setText(_numStr + _dotStr + _decStr);
        this.showEN.setText(TranslateEN.trans(_numStr, _decStr));
        this.showCN.setText(TranslateCN.trans(_numStr, _decStr));
    }

    void initUI()
    {
        _numStr = "0";
        _dotStr = "";
        _decStr = "";
        this.input.setText(_numStr + _dotStr + _decStr);
        this.btnCancel.setVisibility(View.INVISIBLE);
        this.showEN.setText("");
        this.showCN.setText("");
    }

}
