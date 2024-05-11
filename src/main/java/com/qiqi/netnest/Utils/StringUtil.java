package com.qiqi.netnest.Utils;


public class StringUtil {

    /**
     * 过滤数字
     *
     * @param number
     * @return
     */
    public static String filterNumber(String number) {
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }

    /**
     * 过滤英文
     *
     * @param alph
     * @return
     */
    public static String filterAlphabet(String alph) {
        alph = alph.replaceAll("[^(A-Za-z)]", "");
        return alph;
    }

    /**
     * 过滤中文
     *
     * @param chin
     * @return
     */
    public static String filterChinese(String chin) {
        chin = chin.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
        return chin;
    }

    /**
     * 过滤英文数字中文
     * @param character
     * @return
     */
    public static String filter(String character) {
        character = character.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
        return character;
    }
}
