package mytetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmt
 */
public class GamePanel extends javax.swing.JPanel implements Runnable {

    Controller controller = new Controller();

    public GamePanel() {
        initComponents();
        controller.getShape();
        controller.inintFix();
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        while (!MainFrame.isOver) {
            try {
                if (!MainFrame.isPause) {
                    controller.down();
                    this.repaint();
                }
                Thread.currentThread().sleep(MainFrame.interval);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBlocks(g, controller.currentX, controller.currentY);
        drawFix(g);
    }

    /**
     * 画墙
     * @param g
     */
    public void drawFix(Graphics g) {
        for (int j = 0; j <= 9; j++) {
            for (int k = 0; k <= 19; k++) {
                if (controller.fix[j][k] == 1) {
                    g.setColor(Color.black);
                    g.drawRect(20 * j, 20 * k, 19, 19);
                    g.setColor(Color.yellow);
                    g.fillRect(j * 20, k * 20, 18, 18);
                }
                if (controller.fix[j][k] == 2) {
                    g.setColor(Color.black);
                    g.drawRect(20 * j, 20 * k, 19, 19);
                    g.setColor(Color.blue);
                    g.fillRect(j * 20, k * 20, 18, 18);
                }
                if (controller.fix[j][k] == 3) {
                    g.setColor(Color.black);
                    g.drawRect(20 * j, 20 * k, 19, 19);
                    g.setColor(Color.green);
                    g.fillRect(j * 20, k * 20, 18, 18);
                }
                if (controller.fix[j][k] == 4) {
                    g.setColor(Color.black);
                    g.drawRect(20 * j, 20 * k, 19, 19);
                    g.setColor(Color.cyan);
                    g.fillRect(j * 20, k * 20, 18, 18);
                }
                if (controller.fix[j][k] == 5) {
                    g.setColor(Color.black);
                    g.drawRect(20 * j, 20 * k, 19, 19);
                    g.setColor(Color.pink);
                    g.fillRect(j * 20, k * 20, 18, 18);
                }
                if (controller.fix[j][k] == 6) {
                    g.setColor(Color.black);
                    g.drawRect(20 * j, 20 * k, 19, 19);
                    g.setColor(Color.white);
                    g.fillRect(j * 20, k * 20, 18, 18);
                }
                if (controller.fix[j][k] == 7) {
                    g.setColor(Color.black);
                    g.drawRect(20 * j, 20 * k, 19, 19);
                    g.setColor(Color.orange);
                    g.fillRect(j * 20, k * 20, 18, 18);
                }
                if (controller.fix[j][k] == 9) {
                    g.setColor(Color.lightGray);
                    g.fillRect(j * 20, k * 20, 19, 19);
                }
                if (controller.fix[j][k] == 10) {
                    g.setColor(Color.red);
                    g.fillRect(j * 20, k * 20, 19, 19);
                }
            }
        }
    }

    /**
     * 画格子
     * 同时判断结束
     * @param g
     * @param x
     * @param y
     */
    public void drawBlocks(Graphics g, int x, int y) {
        int[] shape = controller.getAShape().getCurrentBlocks();
        for (int i = 0; i < shape.length; i += 2) {
            if (controller.fix[shape[i] + x][shape[i + 1] + y] != 0) {
                for (int j = 0; j < shape.length; j += 2) {
                    controller.fix[shape[j] + x][shape[j + 1] + y]=10;
                }
                MainFrame.over();
                return;
            }
        }
        for (int i = 0; i < shape.length; i += 2) {
            g.setColor(Color.black);
            g.drawRect(20 * (shape[i] + x), 20 * (shape[i + 1] + y), 19, 19);
            g.setColor(controller.getAShape().getColor());
            g.fillRect(20 * (shape[i] + x), 20 * (shape[i + 1] + y), 18, 18);
        }
    }

    public void keyPressed(java.awt.event.KeyEvent evt) {
        if ((!MainFrame.isOver)&&(!MainFrame.isPause)) {
            switch (evt.getKeyCode()) {
                case 37:
                    controller.left();
                    break;
                case 39:
                    controller.right();
                    break;
                case 40:
                    controller.down();
                    break;
                case 38:
                    controller.turn();
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 255, 204));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
