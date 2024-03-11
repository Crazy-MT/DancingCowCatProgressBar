package tokyo.northside.intellij.plugins.nyan;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private static final String PACKAGE_PATH = "/tokyo/northside/intellij/plugins/nyan/";

    private final ImageIcon GIF_ICON = (ImageIcon) IconLoader.getIcon(PACKAGE_PATH + "cat_1.gif",
            getClass().getClassLoader());

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (GIF_ICON != null) {
            GIF_ICON.paintIcon(this, g, 0, 0);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (GIF_ICON != null) {
            return new Dimension(GIF_ICON.getIconWidth(), GIF_ICON.getIconHeight());
        } else {
            return super.getPreferredSize();
        }
    }
}