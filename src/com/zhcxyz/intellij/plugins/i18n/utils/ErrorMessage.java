package com.zhcxyz.intellij.plugins.i18n.utils;

import com.intellij.openapi.ui.Messages;

/**
 * Created by bond on 2017/10/6.
 */
public final class ErrorMessage {

    private ErrorMessage() {
    }

    public static void show(int code) {
        Messages.showMessageDialog("Sorry！there is an error[Code: E" + code + "]", "Error", Messages.getErrorIcon());
    }
}
