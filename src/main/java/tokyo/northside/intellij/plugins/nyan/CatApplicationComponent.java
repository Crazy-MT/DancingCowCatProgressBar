package tokyo.northside.intellij.plugins.nyan;

import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.wm.IdeFrame;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CatApplicationComponent implements LafManagerListener, ApplicationActivationListener {

    @Override
    public void lookAndFeelChanged(@NotNull LafManager lafManager) {
        // see https://plugins.jetbrains.com/docs/intellij/plugin-listeners.html
        updateProgressBarUi();
    }

    @Override
    public void applicationActivated(@NotNull IdeFrame ideFrame) {
        updateProgressBarUi();
    }

    private void updateProgressBarUi() {
        UIManager.put("ProgressBarUI", CatProgressBarUi.class.getName());
        UIManager.getDefaults().put(CatProgressBarUi.class.getName(), CatProgressBarUi.class);
    }
}
