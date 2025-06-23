package cui;
import javax.swing.*;

import java.awt.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("模拟多页面显示");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        // 主卡片面板（仍使用CardLayout作为外层）
        JPanel mainCardPanel = new JPanel(new CardLayout());

        // 创建包含四个页面的"复合页面"
        JPanel compositePage = new JPanel(new GridLayout(2, 2));

        // 创建四个子页面
        JPanel page1 = createPage("页面1", Color.LIGHT_GRAY);
        JPanel page2 = createPage("页面2", new Color(220, 220, 255));
        JPanel page3 = createPage("页面3", new Color(255, 220, 220));
        JPanel page4 = createPage("页面4", new Color(220, 255, 220));

        compositePage.add(page1);
        compositePage.add(page2);
        compositePage.add(page3);
        compositePage.add(page4);

        // 添加复合页面到卡片面板
        mainCardPanel.add(compositePage, "multiView");

        // 可以添加其他单页面视图（保留CardLayout的切换功能）
        JPanel singlePage = createPage("单独页面", Color.WHITE);
        mainCardPanel.add(singlePage, "singleView");

        // 添加切换按钮
        JButton switchBtn = new JButton("切换视图模式");
        switchBtn.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainCardPanel.getLayout();
            cl.next(mainCardPanel);
        });

        frame.add(mainCardPanel, BorderLayout.CENTER);
        frame.add(switchBtn, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static JPanel createPage(String title, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JTextArea content = new JTextArea(5, 20);
        content.setText("这是" + title + "的内容区域\n可以添加各种组件");
        content.setLineWrap(true);
        panel.add(new JScrollPane(content), BorderLayout.CENTER);

        return panel;
    }
}