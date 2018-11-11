package mytetris;

import socket.SocketUtil;

public class GameState {

    private Block currentBlock; // 当前形状
    private Block nextBlock; // 下一个形状
    private int count; // 总得分
    private int point; // 每次加分
    private int interval; // 时间间隔，影响速度

    public GameState() {
        this.nextBlock = new Block();
        SocketUtil.send(MainFrame.socket, "fBlock" + Integer.toString(nextBlock.getI()));
        this.interval = 1000;
        this.count = 0;
        this.point = 1;
    }

    /**
     * 产生一个新的形状
     */
    public void getNewBlock() {
        currentBlock = nextBlock;
        nextBlock = new Block();
        SocketUtil.send(MainFrame.socket, "nBlock" + Integer.toString(nextBlock.getI()));
    }

    public void setNewBlock(Block block) {
        this.currentBlock = this.nextBlock;
        this.nextBlock = block;
    }

    public void changeInterval() {
        if (MainFrame.mod != 2 && (1000 - count * 9) > 0) {
            interval = 1000 - count * 9;
        }
    }

    /**
     * @return the currentBlock
     */
    public Block getCurrentBlock() {
        return currentBlock;
    }

    /**
     * @param currentBlock the currentBlock to set
     */
    public void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    /**
     * @return the nextBlock
     */
    public Block getNextBlock() {
        return nextBlock;
    }

    /**
     * @param nextBlock the nextBlock to set
     */
    public void setNextBlock(Block nextBlock) {
        this.nextBlock = nextBlock;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the point
     */
    public int getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * @return the interval
     */
    public int getInterval() {
        return interval;
    }

    /**
     * @param interval the interval to set
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

}
