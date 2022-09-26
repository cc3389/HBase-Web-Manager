package edu.wit.hbasemanager.common.code;

import java.util.Random;
import java.util.StringJoiner;

public class VerfiyCodeUtil {
    public static String generateCode() {
        Random ran = new Random();
        char[] code = new char[6];
        for (int i=0;i<code.length;i++) {
            code[i] = (char)('0' + ran.nextInt(9));
        }
        return new String(code);
    }

    public static void main(String[] args) {
        System.out.println(generateCode());
    }
}
