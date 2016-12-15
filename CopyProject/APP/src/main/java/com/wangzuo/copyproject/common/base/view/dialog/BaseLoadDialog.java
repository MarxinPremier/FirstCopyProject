package com.wangzuo.copyproject.common.base.view.dialog;

import android.content.Context;
/**
 * Created by hejie on 2016/11/2.
 * <p>
 * 基本的提示框,可以自行构造，继承LoadDialogInterface接口
 * 根据模板来写就行了,替换度对象名称即可,下面是可以替换的Dialog对象
 * {@link LoadDialog}
 */

public class BaseLoadDialog {

    private LoadDialogInterface diyDialogInter;

    public BaseLoadDialog(Context context) {
        diyDialogInter = new LoadDialog(context);
    }

    public void loadHint(String hint) {
        diyDialogInter.loadHint(hint);
    }

    public void loadDialogDismiss() {
        diyDialogInter.loadDialogDismiss();
    }

    public void loadDialogShow() {
        diyDialogInter.loadDialogShow();
    }
}
