package com.ho.mycheckbook;

/**
 * Created by ho on 2017/10/5.
 */

public class TranslateCN {

    private static String[] arr = new String[]{"", "万", "亿"};

    private static String[] digitArr = new String[]{"","壹","贰", "叁", "肆", "伍", "陆", "柒", "捌","玖"};

    public static String trans(String pNumber, String xDecimal)
    {

        String dollars = "";
        String cents = "";

        //小数处理
        if(xDecimal.length() == 0)
        {
            xDecimal = "00";
        }
        if(xDecimal.length() == 1)//如果一位，补齐一个0
        {
            xDecimal += "0";
        }
        if(Integer.parseInt(xDecimal) > 0)//如果有整数部分
        {
            cents = getXiaoshu(Integer.parseInt(xDecimal));//把2位小数整数化 //小数点*100时候会出现很多0.1999999，保留2位处理
        }

        //处理整数部分
        int xIndex = 0;
        while(pNumber != "")//切整个数字字符串
        {
            if(pNumber.length() < 4)//整串小于4个数的时候进行补齐
            {
                pNumber = "0000" + pNumber;
                pNumber = pNumber.substring(pNumber.length()-4);
            }

            String xQian = "";
            String xValue = pNumber.substring(pNumber.length()-4);//获取字符串最右4个数
            if (Integer.parseInt(xValue) != 0)
            {
                int q = Integer.parseInt(xValue)/1000;
                int h = (Integer.parseInt(xValue) - q*1000)/100;
                int t = Integer.parseInt(xValue) - q*1000 - h*100;
                if(q != 0)//千位不为0
                {
                    xQian = getDigit(q) + "仟";
                }
                if(h != 0)//百位不为0
                {
                    xQian += getDigit(h) + "佰";
                }
                if(t != 0)
                {
                    xQian += getTens(t);
                }
            }
            if(xQian != "")//整数部分整合文字
            {
                dollars = xQian + arr[xIndex] + dollars;
            }

            if(pNumber.length() > 4)//是否要下次while循环
            {
                pNumber = pNumber.substring(0, pNumber.length()-4);
            }
            else
            {
                pNumber = "";
            }
            xIndex += 1;
        }

        switch(dollars)
        {
            case "":
            case "0":
                dollars = "零圆";
                break;
            default:
                dollars += "圆";
                break;
        }

        switch(cents)
        {
            case "":
                cents = "整";
                break;
            default:
                //
                break;
        }
        String spellNumberToEnglish = dollars + cents;

        return spellNumberToEnglish;
    }

    private static String getTens(int pTens)//十位数处理
    {
        String s = getDigit(pTens/10);
        s = s == "" ? "" : s + "拾";
        return s + getDigit(pTens%10);
    }

    private static String getXiaoshu(int pTens)//小数位数处理
    {
        String s = getDigit(pTens/10);
        s = s == "" ? "" : s + "角";
        String g = getDigit(pTens%10);
        g = g == "" ? "" : g + "分";
        return s + g;
    }

    private static String getDigit(int pDigit)
    {
        return digitArr[pDigit];
    }


}
