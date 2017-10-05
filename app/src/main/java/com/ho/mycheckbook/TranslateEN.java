package com.ho.mycheckbook;

/**
 * Created by ho on 2017/10/5.
 */

public class TranslateEN {

    private static String[] arr = new String[]{" ", " ", " Thousand ", " Million ", " Billion ", " Trillion "};

    private static String[] pArr = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                                                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen","Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    private static String[] tArr = new String[]{"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};

    public static String trans(String pNumber, String xDecimal) {

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
            cents = getTens(Integer.parseInt(xDecimal));//把2位小数整数化 //小数点*100时候会出现很多0.1999999，保留2位处理
        }

        //处理整数部分
        int xIndex = 1;
        while(pNumber != "")//切整个数字字符串
        {
            if(pNumber.length() < 3)//整串小于3个数的时候进行补齐 每次都要补齐
            {
                pNumber = "000" + pNumber;
                pNumber = pNumber.substring(pNumber.length()-3);
            }

            String xHundred = "";
            String xValue = pNumber.substring(pNumber.length()-3);//获取字符串最右3个数
            if (Integer.parseInt(xValue) != 0)
            {
                int h = Integer.parseInt(xValue)/100;
                int t = Integer.parseInt(xValue) - h*100;
                if(h != 0)//百位不为0
                {
                    xHundred = getDigit(h) + " Hundred ";
                }
                if(t != 0)
                {
                    xHundred += getTens(t);
                }
            }
            if(xHundred != "")//整数部分整合文字
            {
                dollars = xHundred + arr[xIndex] + dollars;
            }

            if(pNumber.length() > 3)//是否要下次while循环
            {
                pNumber = pNumber.substring(0, pNumber.length()-3);
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
                dollars = "No dollars";
                break;
            case "One":
                dollars = "One Dollar";
                break;
            default:
                dollars += " dollars";
                break;
        }

        switch(cents)
        {
            case "":
                cents = " Only";
                break;
            case "One":
                cents = " and One Cent";
                break;
            default:
                cents = " and " + cents + " cents";
                break;
        }
        String spellNumberToEnglish = dollars + cents;

        return spellNumberToEnglish;
    }

    private static String getTens(int pTens)//十位数处理
    {
        String result = "";
        int t = pTens/10;
        if (t == 1)//如果是10开头
        {
            result = pArr[pTens];
        }
        else //如果不是
        {
            result = tArr[t];
            result += getDigit(pTens%10);
        }

        return result;
    }


    private static String getDigit(int pDigit)
    {
        String digit = "";
        digit = pArr[pDigit];
        return digit;
    }

}
