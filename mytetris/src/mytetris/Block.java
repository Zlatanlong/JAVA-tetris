/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mytetris;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Zlatan
 */
public class Block {
    List<int[][]> allBlocks = new ArrayList<>();
    List<Color> colors = new ArrayList<>();
    int i;
    public Block() {
        allBlocks.add(tblocks);
        colors.add(Color.yellow);
        allBlocks.add(lblocks);
        colors.add(Color.blue);
        allBlocks.add(iblocks);
        colors.add(Color.green);
        allBlocks.add(flblocks);
        colors.add(Color.cyan);
        allBlocks.add(zblocks);
        colors.add(Color.pink);
        allBlocks.add(fzblocks);
        colors.add(Color.white);
        allBlocks.add(oblocks);
        colors.add(Color.orange);
        i=(int)(0+Math.random()*(6-0+1));//(数据类型)(最小值+Math.random()*(最大值-最小值+1))
    }
    
    int state=0;//当前的数
    //格式:(第一块，X轴，Y轴；第二块......)
    //shape of T
    int[][] tblocks={
        {1,0,0,1,1,1,2,1},
        {1,0,1,1,2,1,1,2},
        {1,2,0,1,1,1,2,1},
        {1,0,0,1,1,1,1,2}            
    };
    //shape of L
    int[][] lblocks={
        {1,0,1,1,1,2,2,2},
        {0,1,1,1,2,1,0,2},
        {0,0,1,0,1,1,1,2},
        {2,0,0,1,1,1,2,1}            
    };
    //shape of I
    int[][]iblocks={
        {0,0,1,0,2,0,3,0},
        {2,0,2,1,2,2,2,3},
        {0,0,1,0,2,0,3,0},
        {2,0,2,1,2,2,2,3}
    };
    int[][]flblocks={
        {1,0,1,1,1,2,0,2},
        {0,0,0,1,1,1,2,1},
        {1,0,2,0,1,1,1,2},
        {0,1,1,1,2,1,2,2}   
    };
    int[][]zblocks={
        {1,0,2,0,1,1,0,1},
        {1,0,1,1,2,1,2,2},
        {1,0,2,0,1,1,0,1},
        {1,0,1,1,2,1,2,2}  
    };
    int[][]fzblocks={
        {0,0,1,0,1,1,2,1},
        {1,0,1,1,0,1,0,2},
        {0,0,1,0,1,1,2,1},
        {1,0,1,1,0,1,0,2} 
    };
    int[][]oblocks={
        {1,0,2,0,1,1,2,1},
        {1,0,2,0,1,1,2,1},
        {1,0,2,0,1,1,2,1},
        {1,0,2,0,1,1,2,1}  
    };
    public void next() {
        state = state == 3 ? 0 : state + 1;
    }
    /**
     * 
     */
    public void forward() {
        state = state == 0 ? 3 : state - 1;
    }

    /**
     * 返回当前tblocks
     * @return 
     */
    public int[] getCurrentBlocks() {
            return allBlocks.get(i)[state];
    }
    
    public Color getColor(){
        return colors.get(i);
    }
    
    public int getI(){
        if (MainFrame.mod==0) {
            return 8;
        }
        return i;
    }
}
