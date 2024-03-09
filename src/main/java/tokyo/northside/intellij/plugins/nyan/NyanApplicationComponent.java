package tokyo.northside.intellij.plugins.nyan;

import com.intellij.buildsystem.model.BuildManager;
import com.intellij.execution.configuration.RunConfigurationExtensionBase;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.ScalableIcon;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.refactoring.listeners.RefactoringEventData;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NyanApplicationComponent implements LafManagerListener, ApplicationActivationListener {
    public NyanApplicationComponent() {
/*
        ApplicationManager.getApplication().getMessageBus().connect().subscribe(RefactoringEventListener.REFACTORING_EVENT_TOPIC, new RefactoringEventListener() {
            @Override
            public void refactoringDone(@NotNull String s, @Nullable RefactoringEventData refactoringEventData) {
                MyPopupWindow.showPopup();
            }
        });
*/
    }

    @Override
    public void lookAndFeelChanged(@NotNull LafManager lafManager) {
        // see https://plugins.jetbrains.com/docs/intellij/plugin-listeners.html
        updateProgressBarUi();

//        MyPopupWindow.showPopup();

    }

    @Override
    public void applicationActivated(@NotNull IdeFrame ideFrame) {
        updateProgressBarUi();

//        MyPopupWindow.showPopup();

    }

    private void updateProgressBarUi() {
        UIManager.put("ProgressBarUI", NyanProgressBarUi.class.getName());
        UIManager.getDefaults().put(NyanProgressBarUi.class.getName(), NyanProgressBarUi.class);
    }
}
