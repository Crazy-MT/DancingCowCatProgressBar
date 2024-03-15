package com.crazymt.intellij.plugins;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.AnActionResult;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.awt.RelativePoint;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class CatPopupWindow implements AnActionListener {
    JBPopup popup;

    public CatPopupWindow() {
        createPopup();
    }

    private void createPopup() {
        // 创建一个 JPanel 作为 Popup Window 的内容
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new ImagePanel(), BorderLayout.CENTER);

        // 使用 JBPopupFactory 创建一个简单的 Popup
        popup = JBPopupFactory.getInstance()
                .createComponentPopupBuilder(panel, null)
                .setAlpha(0.8f)
                .setMovable(true)
                .createPopup();
        popup.setSize(new Dimension(320,320));
    }

    @Override
    public void beforeActionPerformed(@NotNull AnAction action, @NotNull AnActionEvent event) {
        AnActionListener.super.beforeActionPerformed(action, event);

        if (action.getClass().getName().contains("ExecutorAction")
                || action.getClass().getName().contains("CompileDirtyAction")
                || action.getClass().getName().contains("Make Project")
                || action.getClass().getName().contains("MakeGradleProjectAction")
                || action.getClass().getName().contains("RebuildGradleProjectAction")) {
//            System.out.println("MTMTMT before:" + action + " " + action.getClass().getName());
//            showPopup();
        }
    }

    @Override
    public void afterActionPerformed(@NotNull AnAction action, @NotNull AnActionEvent event, @NotNull AnActionResult result) {
        AnActionListener.super.afterActionPerformed(action, event, result);
        if (action.getClass().getName().contains("ExecutorAction")
                || action.getClass().getName().contains("CompileDirtyAction")
                || action.getClass().getName().contains("Make Project")
                || action.getClass().getName().contains("MakeGradleProjectAction")
                || action.getClass().getName().contains("RebuildGradleProjectAction")) {
//            System.out.println("MTMTMT after:" + action + " " + action.getClass().getName());
//            dismissPopup();
        }
    }

    public void showPopup() {
        if (popup != null) {

            if (popup.isDisposed()) {
                createPopup();
            }

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();

/*            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();
            DisplayMode mode = gs[0].getDisplayMode();
            int screenWidth = mode.getWidth();
            int screenHeight = mode.getHeight();*/

            int popupWidth = 320;
            int popupHeight = 320;
            int x = screenWidth - popupWidth;
            int y = screenHeight - popupHeight;

            popup.show(new RelativePoint(new Point(0, y)));
            /*ApplicationManager.getApplication().invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (!popup.isDisposed())
                        popup.showInFocusCenter();
                }
            });*/
        }

        // 获取相对于组件的相对位置
//        RelativePoint point = JBPopupFactory.getInstance()
//                .guessBestPopupLocation(component);

        // 显示 Popup Window
//        popup.show(new RelativePoint(new Point(500, 500)));
    }

    private void dismissPopup() {
        if (popup != null) {
            popup.cancel();
        }
    }
}
