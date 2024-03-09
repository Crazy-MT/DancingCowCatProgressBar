package tokyo.northside.intellij.plugins.nyan;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionListener;
import com.intellij.execution.ExecutionManager;
import com.intellij.execution.configuration.RunConfigurationExtensionBase;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunnerSettings;
import com.intellij.execution.lineMarker.ExecutorAction;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.AnActionResult;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.ScalableIcon;
import com.intellij.openapi.wm.impl.headertoolbar.BuildQuickAction;
import com.intellij.task.ProjectTaskContext;
import com.intellij.task.ProjectTaskListener;
import com.intellij.task.ProjectTaskManager;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.util.messages.MessageBusConnection;
import com.jetbrains.rd.generator.gradle.GradleGenerationSpec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MyPopupWindow implements AnActionListener, ExecutionListener, ProjectTaskListener {

    @Override
    public void started(@NotNull ProjectTaskContext context) {
        ProjectTaskListener.super.started(context);
        System.out.println("MTMTMT started:" + context);

    }

    @Override
    public void finished(@NotNull ProjectTaskManager.Result result) {
        ProjectTaskListener.super.finished(result);
        System.out.println("MTMTMT finished:" + result);
    }

    @Override
    public void processStarted(@NotNull String executorId, @NotNull ExecutionEnvironment env, @NotNull ProcessHandler handler) {
        ExecutionListener.super.processStarted(executorId, env, handler);
        System.out.println("MTMTMT processStarted:" + executorId);
    }

    @Override
    public void processTerminated(@NotNull String executorId, @NotNull ExecutionEnvironment env, @NotNull ProcessHandler handler, int exitCode) {
        ExecutionListener.super.processTerminated(executorId, env, handler, exitCode);
        System.out.println("MTMTMT processTerminated:" + executorId);
    }

    @Override
    public void processStartScheduled(@NotNull String executorId, @NotNull ExecutionEnvironment env) {
        ExecutionListener.super.processStartScheduled(executorId, env);
        System.out.println("MTMTMT processStartScheduled:" + executorId);
    }

    JBPopup popup;

    private static final String PACKAGE_PATH = "/tokyo/northside/intellij/plugins/nyan/";
    private final ScalableIcon CAT_ICON = (ScalableIcon) IconLoader.getIcon(PACKAGE_PATH + "cat_1.png",
            getClass().getClassLoader());
    private final ScalableIcon RCAT_ICON = (ScalableIcon) IconLoader.getIcon(PACKAGE_PATH + "cat_1.png",
            getClass().getClassLoader());
    public MyPopupWindow() {

        createPopup();
        MessageBusConnection connection = ApplicationManager.getApplication().getMessageBus().connect();
        connection.subscribe(ExecutionManager.EXECUTION_TOPIC, new ExecutionListener() {
            @Override
            public void processStarted(@NotNull String executorId, @NotNull ExecutionEnvironment env, @NotNull ProcessHandler handler) {
                ExecutionListener.super.processStarted(executorId, env, handler);
                System.out.println("MTMTMT ExecutionListener:" + executorId);

            }
        });
    }

    private void createPopup() {
        // 创建一个 JPanel 作为 Popup Window 的内容
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new ImagePanel(PACKAGE_PATH + "cat_1.gif"), BorderLayout.CENTER);


        // 使用 JBPopupFactory 创建一个简单的 Popup
        popup = JBPopupFactory.getInstance()
                .createComponentPopupBuilder(panel, null)
//                .setAlpha(0.5f)
                .setMovable(true)
                .createPopup();
        popup.setSize(new Dimension(320,320));
    }

    @Override
    public void beforeActionPerformed(@NotNull AnAction action, @NotNull AnActionEvent event) {
        AnActionListener.super.beforeActionPerformed(action, event);
        System.out.println("MTMTMT before:" + action + ";name:" + action.getClass().getName());

        if (action.getClass().getName().contains("ExecutorAction")
                || action.getClass().getName().contains("CompileDirtyAction")
                || action.getClass().getName().contains("Make Project")
                || action.getClass().getName().contains("MakeGradleProjectAction")
                || action.getClass().getName().contains("RebuildGradleProjectAction")) {
//            System.out.println("MTMTMT before:" + action + " " + action.getClass().getName());
            showPopup();
        }
    }

    @Override
    public void afterActionPerformed(@NotNull AnAction action, @NotNull AnActionEvent event, @NotNull AnActionResult result) {
        AnActionListener.super.afterActionPerformed(action, event, result);
        System.out.println("MTMTMT after:" + action + ";name" + action.getClass().getName());
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
        System.out.println("MTMTMT showPopup:" + popup.isDisposed());

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

            // 计算右下角的位置
            int popupWidth = 320;
            int popupHeight = 320;
            int x = screenWidth - popupWidth;
            int y = screenHeight - popupHeight;

            System.out.println("MTMTMT x:" + x);
            System.out.println("MTMTMT y:" + y);


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
        System.out.println("MTMTMT dismissPopup:" + popup.isDisposed());

        if (popup != null) {
            popup.cancel();
        }
    }
}
