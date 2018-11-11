package mytetris;

import java.net.Socket;
import socket.SocketUtil;

public class Controller {

    int removeLog = 0;//同时消行数量
    /**
     * 初始化状态
     */
    int currentX = 3;
    int currentY = 0;
    public int[][] fix = new int[10][20];// 把整个界面分割成10*20
    private GameState state = new GameState();

    /**
     * 产生一个新的形状
     */
    public void getNewShape() {
        getState().getNewBlock();
    }

    public Block getCurrentShape() {
        return getState().getCurrentBlock();
    }

    public Block getNextShape() {
        return getState().getNextBlock();
    }

    /**
     * 初始化界面数组
     */
    public void inintFix() {
        for (int j = 0; j <= 9; j++) {
            for (int k = 0; k <= 19; k++) {
                fix[j][k] = 0;
            }
        }
    }

    /**
     * 判断是否出界
     *
     * @param x X轴偏移量
     * @param y Y轴偏移量
     * @return
     */
    public boolean isValid(int x, int y) {
        int[] tempShape = getCurrentShape().getCurrentBlocks();
        for (int i = 0; i < 8; i += 2) {
            if ((tempShape[i + 1] + y) < 0 || (tempShape[i + 1] + y) > 19) {
                return false;
            }
            if ((tempShape[i] + x) < 0 || (tempShape[i] + x) > 9) {
                return false;
            }
            if (fix[tempShape[i] + x][tempShape[i + 1] + y] != 0) {
                return false;
            }
        }
        return true;
    }

    // 上下左右和翻转 先判断是否出界
    public void left(Boolean ifSend) {
        if (isValid(currentX - 1, currentY)) {
            currentX--;
            if (ifSend) {
                SocketUtil.send(MainFrame.socket, "move37");

            }
        }

    }

    public void right(Boolean ifSend) {
        if (isValid(currentX + 1, currentY)) {
            currentX++;
            if (ifSend) {
                SocketUtil.send(MainFrame.socket, "move39");
            }
        }

    }

    /**
     * 方块下落，落不下去的就死掉了
     */
    public void down(Boolean ifSend) {
        if (ifSend) {
            SocketUtil.send(MainFrame.socket, "move40");
        }
        if (isValid(currentX, currentY + 1)) {
            currentY++;
        } else {
            add(currentX, currentY, ifSend);
        }
    }

    public void turn(Boolean ifSend) {
        getState().getCurrentBlock().next();
        if (!isValid(currentX, currentY)) {
            getState().getCurrentBlock().forward();
        } else {
            if (ifSend) {
                SocketUtil.send(MainFrame.socket, "move38");
            }
        }
    }

    /**
     * 把死掉的方块变成墙；
     *
     * @param x
     * @param y
     */
    public void add(int x, int y, Boolean ifMy) {
        int[] tempShape = getState().getCurrentBlock().getCurrentBlocks();
        for (int i = 0; i < 8; i += 2) {
            fix[x + tempShape[i]][y + tempShape[i + 1]] = getState().getCurrentBlock().getIColor() + 1;
        }
        remove();
        currentX = 3;
        currentY = 0;
        if (ifMy) {
            getNewShape();
            MainFrame.changeNext();
        }
    }

    /**
     * 消除可消的一行
     */
    public void remove() {
        for (int i = 19; i > 0; i--) {
            //i是一共20行
            int flag = 0;
            for (int j = 0; j < 10; j++) {
                if (fix[j][i] == 0) {
                    flag = 1;//一行已经满了
                }
            }
            if (flag == 0) {
                state.setCount(state.getCount() + state.getPoint() + removeLog);
                state.changeInterval();
                MainFrame.changeCount();
                for (int j = 0; j < 10; j++) {
                    fix[j][i] = 0;
                }//消除这一行
                for (int k = i; k > 0; k--) {
                    for (int j = 0; j < 10; j++) {
                        fix[j][k] = fix[j][k - 1];
                    }
                }//其他行下移一行
                removeLog++;
                remove();
            }
        }
        //判断第一行是否要被消除
        int flag0 = 0;
        for (int j = 0; j < 10; j++) {
            if (fix[j][0] == 0) {
                flag0 = 1;
            }
        }
        if (flag0 == 0) {
            for (int j = 0; j < 10; j++) {
                fix[j][0] = 0;
            }
        }
        removeLog = 1;
    }

    /**
     * 道具1
     */
    public void prop1() {
        for (int j = 0; j < 10; j++) {
            fix[j][19] = 0;
        }//消除这一行
        for (int k = 19; k > 0; k--) {
            for (int j = 0; j < 10; j++) {
                fix[j][k] = fix[j][k - 1];
            }
        }//其他行下移一行
        state.setCount(state.getCount() + 10);

        state.changeInterval();
        MainFrame.changeCount();
        MainFrame.gp.repaint();
    }

    /**
     * @return the state
     */
    public GameState getState() {
        return state;
    }

}
