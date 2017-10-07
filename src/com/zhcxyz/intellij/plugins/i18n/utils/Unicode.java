package com.zhcxyz.intellij.plugins.i18n.utils;

import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;

/**
 * Created by bond on 2017/10/6.
 */
public final class Unicode {

    private Unicode() {
    }

    public static String escaper(String src) {
        JavaUnicodeEscaper unicodeEscaper = new JavaUnicodeEscaper(128, 2147483647, true);
        return unicodeEscaper.translate(src);
    }

    public static String unEscaper(String str) {
        UnicodeUnescaper unescaper = new UnicodeUnescaper();
        return unescaper.translate(str);
    }

}
