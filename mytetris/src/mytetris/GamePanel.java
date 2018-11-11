package mytetris;

import java.awt.Color;
import java.awt.Graphics;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmt 整个gamepanel分为10*20（宽*高） 200 块 每一块根据fix[x][y]的值来区分颜色，其中若为0
 * 不单独添加颜色，相当于正常 10为红色，现在导致游戏over的那个形状会被画成红色，其他颜色均代表墙。 1-7代表7中颜色，9为不区分形状颜色时墙的颜色
 */
public class GamePanel extends javax.swing.JPanel implements Runnable {

    Controller controller = new Controller();

    public GamePanel(Boolean ifMy) {
        initComponents();
        controller.getNewShape();
        controller.inintFix();
        if (ifMy) {
            new Thread(this).start();
            System.out.println("123");
        }
    }

    @Override
    public void run() {
        System.out.println("456");
        while (!MainFrame.isOver) {
            try {
                if (!MainFrame.isPause) {
                    controller.down(true);
                    System.out.println("下" + controller.getState().getInterval());
                    this.repaint();
                }
                Thread.currentThread().sleep(controller.getState().getInterval());
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
     *
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
                // 红色警告
                if (controller.fix[j][k] == 10) {
                    g.setColor(Color.red);
                    g.fillRect(j * 20, k * 20, 19, 19);
                }
            }
        }
    }

    /**
     * 画形状 同时判断结束, 判断逻辑： 如果要画形状的时候当前位置已经有墙了，说明墙已经到了最上面， 因此游戏结束
     *
     * @param g
     * @param x
     * @param y
     */
    public void drawBlocks(Graphics g, int x, int y) {
        int[] shape = controller.getCurrentShape().getCurrentBlocks();
//        for (int i = 0; i < shape.length; i++) {
//            int j = shape[i];
//            System.out.print(j);
//        }
//        System.out.println("huanhang");
        for (int i = 0; i < shape.length; i += 2) {
            if (controller.fix[shape[i] + x][shape[i + 1] + y] != 0) {
                // 把结束的这个形状位置号标为10， 会化成红色
                for (int j = 0; j < shape.length; j += 2) {
                    controller.fix[shape[j] + x][shape[j + 1] + y] = 10;
                }
                MainFrame.over();
                return;
            }
        }
        for (int i = 0; i < shape.length; i += 2) {
            g.setColor(Color.black);
            g.drawRect(20 * (shape[i] + x), 20 * (shape[i + 1] + y), 19, 19);
            g.setColor(controller.getCurrentShape().getColor());
            g.fillRect(20 * (shape[i] + x), 20 * (shape[i + 1] + y), 18, 18);
        }
    }

    public Block getNextBlock() {
        return controller.getNextShape();
    }

    /**
     * 展示时设置第一个Block
     *
     * @param block
     */
    public void setFirstBlock(Block block) {
        getGameState().setNextBlock(block);
    }

    public void setNewBlock(Block block) {
        getGameState().setNewBlock(block);
    }

    public GameState getGameState() {
        return controller.getState();
    }

//    public void changeCount() {
//        controller.getState().changeCount();
//    }
    public void keyPressed(java.awt.event.KeyEvent evt) {
        if ((!MainFrame.isOver) && (!MainFrame.isPause)) {
            switch (evt.getKeyCode()) {
                case 37:
                    controller.left(true);
                    break;
                case 39:
                    controller.right(true);
                    break;
                case 40:
                    controller.down(true);
                    break;
                case 38:
                    controller.turn(true);
                    break;
                case 65:
                    controller.left(true);
                    break;
                case 68:
                    controller.right(true);
                    break;
                case 83:
                    controller.down(true);
                    break;
                case 87:
                    controller.turn(true);
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    public void getPressed(String code) {
        if ((!MainFrame.isOver) && (!MainFrame.isPause)) {
            if (code.equals("move37")) {
                controller.left(false);
            } else if (code.equals("move38")) {
                controller.turn(false);
            } else if (code.equals("move39")) {
                controller.right(false);
            } else if (code.equals("move40")) {
                controller.down(false);
            } else if (code.substring(0, 6).equals("fBlock")) {
                int i = Integer.valueOf(code.substring(6));
                Block block = new Block(i);
                setFirstBlock(block);
            } else if (code.substring(0, 6).equals("nBlock")) {
                int i = Integer.valueOf(code.substring(6));
                Block block = new Block(i);
                setNewBlock(block);
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
