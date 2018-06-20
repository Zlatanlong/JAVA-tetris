package mytetris;

public class MainFrame extends javax.swing.JFrame {
    static int interval=1000;
    static int mod;
    static boolean isOver = true;
    static boolean isPause = false;
    static int count=0;//总分
    static int point;//每次加分
    static GamePanel gp;
    static CountPanel cp;
    /**
     * 改变分数显示同时改变速度
     */
    static  public void changeCount(){
        cp.count.setText( Integer.toString(MainFrame.count));
        if (mod!=2&&(1000-count*9)>0) {
            interval=1000-count*9;
        }
    }
    static public void over(){
        isOver=true;
        isPause=false;
        cp.showOver();
    }
    static public void changeNext(){
        cp.repaint();
        changeCount();
    }
    public MainFrame() {
        //规定画板大小
        initComponents();
        start.setVisible(false);
        pause.setVisible(false);
        cp=new CountPanel();
        cp.setSize(100,600);
        cp.setLocation(254, 100);
        cp.setVisible(false);
        this.getContentPane().add(cp);
        this.setSize(400, 600);
    }
    
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        go = new javax.swing.JButton();
        start = new javax.swing.JButton();
        pause = new javax.swing.JButton();
        msg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        model = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        go.setBackground(new java.awt.Color(255, 255, 204));
        go.setFont(new java.awt.Font("Lucida Grande", 1, 64)); // NOI18N
        go.setForeground(new java.awt.Color(102, 255, 102));
        go.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/巴西.png"))); // NOI18N
        go.setToolTipText("");
        go.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goActionPerformed(evt);
            }
        });

        start.setText("重新开始");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        pause.setText("暂停");
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        msg.setText("点击巴西国旗，为巴西队加油即可开始游戏！");

        jLabel1.setText("模式选择：");

        model.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "简约模式", "炫彩模式", "极速模式" }));
        model.setFocusable(false);
        model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelActionPerformed(evt);
            }
        });

        msg2.setColumns(20);
        msg2.setRows(5);
        msg2.setText("游戏说明：\n键盘←→控制方向，↑旋转俄罗斯方块，↓加速下落\n简约模式，炫彩模式每消除一行加1分；\n极速模式每消除一行加10分；\n道具说明：\n使用道具后将在最低端消除一行，\n每局游戏只有5次使用道具的机会！");
        msg2.setBorder(null);
        msg2.setFocusable(false);
        jScrollPane1.setViewportView(msg2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pause)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(go, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pause, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(go, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        //System.out.println(evt.getKeyCode());
        try {
            gp.keyPressed(evt);
        } catch (NullPointerException e) {
            System.out.println("还没点开始");
        }
    }//GEN-LAST:event_formKeyPressed

    private void goActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goActionPerformed
        // 开始游戏按钮:
        msg.setVisible(false);
        msg2.setVisible(false);
        jScrollPane1.setVisible(false);
        start.setVisible(true);
        pause.setVisible(true);
        cp.setVisible(true);
        MainFrame.isOver=false;
        gp=new GamePanel();
        gp.setSize(200,400);
        gp.setLocation(50, 100);
        this.getContentPane().add(gp);
        go.setVisible(false);
        this.requestFocusInWindow();
    }//GEN-LAST:event_goActionPerformed

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed
        // 暂停按钮:
        if (isPause) {
            isPause=false;
            pause.setText("暂停");
        }else{
            isPause=true;
            pause.setText("继续");
        }
        this.requestFocusInWindow();
    }//GEN-LAST:event_pauseActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // 重新开始按钮:
        try {
            over();
            Thread.sleep(1000);//等待GamePanel线程死亡
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainFrame.isOver=false;
        gp=new GamePanel();
        gp.setSize(200,400);
        gp.setLocation(50, 100);
        this.getContentPane().add(gp);
        count=0;
        changeCount();
        cp.delOver();
        cp.setProp1Count(5);
        this.requestFocusInWindow();
    }//GEN-LAST:event_startActionPerformed

    private void modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelActionPerformed
        // 模式切换
        String mo=(String)model.getSelectedItem();
        switch (mo) {
            case "简约模式":
                mod=0;
                point=1;
                break;
            case "炫彩模式":
                mod=1;
                point=1;
                break;
            case "极速模式":
                mod=2;
                interval=100;
                point=10;
                break;
            default:
                break;
        }
    }//GEN-LAST:event_modelActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame mf =new MainFrame();
                mf.setVisible(true);
                mf.requestFocusInWindow();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton go;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> model;
    private javax.swing.JLabel msg;
    private javax.swing.JTextArea msg2;
    private javax.swing.JButton pause;
    private javax.swing.JButton start;
    // End of variables declaration//GEN-END:variables
}
