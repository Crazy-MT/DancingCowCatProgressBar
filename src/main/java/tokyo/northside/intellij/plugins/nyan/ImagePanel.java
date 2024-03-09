package tokyo.northside.intellij.plugins.nyan;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private ImageIcon imageIcon;

    public ImagePanel(String imagePath) {
        try {
            // 从文件加载图像
            imageIcon = new ImageIcon(getClass().getResource(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MTMTMT ImagePanel:" + e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制 GIF 图像
        if (imageIcon != null) {
            imageIcon.paintIcon(this, g, 0, 0);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // 返回图像的尺寸作为首选大小
        if (imageIcon != null) {
            return new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        } else {
            return super.getPreferredSize();
        }
    }
}