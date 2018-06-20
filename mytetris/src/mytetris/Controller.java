package mytetris;

/**
 *
 * @author dmt
 */
public class Controller {
    int removeLog=0;//同时消行数量
    int currentX = 3;
    int currentY = 0;
    public static int[][] fix = new int[10][20];// 把整个界面分割成10*20
    Block currentShape;
    static Block nextShape = new Block();

    /**
     * 产生一个新的形状
     */
    public void getShape() {
        currentShape = nextShape;
        nextShape = new Block();
    }

    /**
     * 返回当前的形状
     *
     * @return
     */
    public Block getAShape() {
        return currentShape;
    }

    public static Block getNextShape() {
        return nextShape;
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
        int[] tempShape = getAShape().getCurrentBlocks();
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
    public void left() {
        if (isValid(currentX - 1, currentY)) {
            currentX--;
        }

    }

    public void right() {
        if (isValid(currentX + 1, currentY)) {
            currentX++;
        }

    }

    /**
     * 方块下落，落不下去的就死掉了
     */
    public void down() {
        if (isValid(currentX, currentY + 1)) {
            currentY++;
        } else {
            add(currentX, currentY);
        }
    }

    public void turn() {
        currentShape.next();
        if (!isValid(currentX, currentY)) {
            currentShape.forward();
        }
    }

    /**
     * 把死掉的方块变成墙；
     *
     * @param x
     * @param y
     */
    public void add(int x, int y) {
        int[] tempShape = currentShape.getCurrentBlocks();
        for (int i = 0; i < 8; i += 2) {
            fix[x + tempShape[i]][y + tempShape[i + 1]] = currentShape.getI() + 1;
        }
        remove();
        currentX = 3;
        currentY = 0;
        getShape();
        MainFrame.changeNext();
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
                MainFrame.count += (MainFrame.point+removeLog);
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
    static public void prop1() {
        for (int j = 0; j < 10; j++) {
            fix[j][19] = 0;
        }//消除这一行
        for (int k = 19; k > 0; k--) {
            for (int j = 0; j < 10; j++) {
                fix[j][k] = fix[j][k - 1];
            }
        }//其他行下移一行
        MainFrame.count += 10;
        MainFrame.changeCount();
        MainFrame.gp.repaint();
    }
}
