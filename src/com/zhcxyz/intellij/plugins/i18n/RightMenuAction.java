package com.zhcxyz.intellij.plugins.i18n;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.zhcxyz.intellij.plugins.i18n.utils.ErrorMessage;
import com.zhcxyz.intellij.plugins.i18n.utils.Unicode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by bond on 2017/10/6.
 */
public class RightMenuAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {

        String data = event.getData(PlatformDataKeys.FILE_TEXT);
        if (data == null || data.length() == 0) {
            return;
        }

        String flag = "###EDITING####";
        // transform to unicode
        int status = data.indexOf(flag);

        String rs = null;
        if (status == -1) {
            data = flag + "\n" + data;
            rs = Unicode.unEscaper(data);
        } else {
            data = data.substring(flag.length() + 1, data.length());
            rs = Unicode.escaper(data);
        }

        if (rs == null) {
            return;
        }

        write(event, rs);
    }

    /**
     * 写入结果
     *
     * @param event
     * @param rs
     */
    private void write(AnActionEvent event, String rs) {
        OutputStream out = null;
        try {
            VirtualFile file = event.getData(PlatformDataKeys.VIRTUAL_FILE);
            if (!file.getExtension().equals("properties")) {
                return;
            }
            String path = file.getPath();
            out = new FileOutputStream(path);
            out.write(rs.getBytes());
            file.refresh(false, false);
        } catch (IOException e) {
            ErrorMessage.show(1000001);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    ErrorMessage.show(1000002);
                }
            }
        }
    }
}
