package com.wangzuo.copyproject.common.base.view.dialog;

/**
 * Created by hejie on 2016/11/2.
 *
 * 提示框模板
 *
 */

public interface LoadDialogInterface {

    /**
     * 加载提示信息
     * @param hintStr
     */
    void loadHint(String hintStr);

    /**
     * 提示框消失
     */
    void loadDialogDismiss();

    /**
     * 提示框显示
     */
    void loadDialogShow();
}
