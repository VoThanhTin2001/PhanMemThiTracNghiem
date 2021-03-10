/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Business.BaiThiInfo;
import Business.ComboItem;
import Business.NoiDungCauHoi;
import Business.ThongTinSV;
import DataAccess.ThucHienQuery;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author nmthe
 */
public class FormClient_Play extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form FormClient_Play
     */
    Connection con = null;
    String maBaithi = "";
    int cauHientai = -1;
    int soCau = 0;
    Timer timer = null;
    int time = 0;
    FormClient_Image formHinh = null;
    
    ArrayList<NoiDungCauHoi> listCauhoi = null;
    ArrayList<String> cauChon = null;
    
    public FormClient_Play() {
        initComponents();
        listCauhoi = new ArrayList();
        cauChon = new ArrayList();
        formHinh = new FormClient_Image();
        this.setLocationRelativeTo(null);
        timer = new Timer(1000, this);
        timer.start();
        
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
     // Add code here to execute periodically
        if(time < 1){
            System.out.println("Cau ke tiep!");
            cauHoiKeTiep();
        }else{
            time--;
            jLabel6.setText(Integer.toString(time));
        }
       
    }
    private void tinhSoCauDung(){
        int cau=0;
        for(int i=0; i<soCau; i++){
            String dapan = listCauhoi.get(i).getDapAn();
            String chon = cauChon.get(i)+"    ";
            System.out.println("So sanh: "+dapan+"@"+chon);
            if(chon.equals(dapan)){
                cau++;
            }else{
                System.out.println("khong dung");
            }
        }
        BaiThiInfo.soCauDung = cau;
    }
    private void resetTimer(){
        time = BaiThiInfo.thoiGian;
        jLabel6.setText(Integer.toString(BaiThiInfo.thoiGian));
    }
    private void cauHoiKeTiep(){
        cauHientai++;
        formHinh.setVisible(false);
        if(cauHientai>0){
            if(cauAbtn.isSelected()){
                cauChon.add("a");
                System.out.println("a");
            }else if(cauBbtn.isSelected()){
                cauChon.add("b");
                System.out.println("b");
            }else if(cauCbtn.isSelected()){
                cauChon.add("c");
                System.out.println("c");
            }else if(cauDbtn.isSelected()){
                cauChon.add("d");
                System.out.println("d");
            }else{
                cauChon.add("e");
                System.out.println("e");
            } 
        }
        
        if(cauHientai < soCau){
            NoiDungCauHoi t = listCauhoi.get(cauHientai);
            cauhoiTextarea.setText(t.getNoidung());
            cauAbtn.setText(t.getCauA());
            cauBbtn.setText(t.getCauB());
            cauCbtn.setText(t.getCauC());
            cauDbtn.setText(t.getCauD());
            buttonGroup.clearSelection();
            jLabel7.setText("<html>"+(cauHientai+1)+"/<b style=\"color:red\">"+soCau+"</b><hmtl>");
            if(t.getHinh() == true){
                
                formHinh.setHinhanh("D://"+(cauHientai+1)+".JPG");
                formHinh.setVisible(true);
            }
        }
        else{
            timer.stop();
            JOptionPane.showMessageDialog(null, ThongTinSV.hoten+", bạn đã hoàn thành bài thi!");
            tinhSoCauDung();
            for(NoiDungCauHoi t : listCauhoi){
                if(t.getHinh()){
                    File f = new File("D://"+t.getCauso()+".JPG");
                    f.delete();
                }
            }
            FormClient_Result f = new FormClient_Result();
            f.setVisible(true);
        }
        resetTimer();
    }
    
    private void loadCauhoi(){
        ThucHienQuery t = new ThucHienQuery();
        ResultSet re = t.ChayQuery(con, "SELECT * FROM Cauhoi WHERE idBaithi="+BaiThiInfo.idBaithi);
        try{
            while(re.next()){
                NoiDungCauHoi x = new NoiDungCauHoi();
                x.setCauso((re.getInt("causo")));
                x.setNoidung(re.getString("noidung"));
                x.setCauA(re.getString("cauA"));
                x.setCauB(re.getString("cauB"));
                x.setCauC(re.getString("cauC"));
                x.setCauD(re.getString("cauD"));
                x.setDapAn(re.getString("dapAn"));
                if(re.getBytes("hinhanh") != null){
                    byte[] fileBytes;
                    fileBytes = re.getBytes("hinhanh");
                    OutputStream targetFile = new FileOutputStream("D://"+(re.getInt("causo"))+".JPG");
                    targetFile.write(fileBytes);
                    targetFile.close();
                    x.setHinh(true);
                }else{
                    x.setHinh(false);
                }
                
                listCauhoi.add(x);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        soCau = listCauhoi.size();
        System.out.println("So cau: " + soCau);
    }
    
    public void setForm(Connection con, String maBaithi){
        this.con = con;
        this.maBaithi = maBaithi;
        jLabel3.setText("<html>Thí sinh: <b style='color:red'>" + ThongTinSV.hoten + "</b> - MSSV: <b style='color:red'>"+ThongTinSV.mssv+"</b></html>");
        System.out.println("Ma bai thi: " + maBaithi);
        loadCauhoi();
        cauHoiKeTiep();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        cauhoiTextarea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        cauAbtn = new javax.swing.JRadioButton();
        cauBbtn = new javax.swing.JRadioButton();
        cauCbtn = new javax.swing.JRadioButton();
        cauDbtn = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        cauhoiTextarea.setColumns(20);
        cauhoiTextarea.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cauhoiTextarea.setRows(5);
        cauhoiTextarea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cauhoiTextarea.setDoubleBuffered(true);
        cauhoiTextarea.setEnabled(false);
        jScrollPane1.setViewportView(cauhoiTextarea);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nội dung câu hỏi:");

        buttonGroup.add(cauAbtn);
        cauAbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cauAbtn.setText("jRadioButton1");

        buttonGroup.add(cauBbtn);
        cauBbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cauBbtn.setText("jRadioButton2");

        buttonGroup.add(cauCbtn);
        cauCbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cauCbtn.setText("jRadioButton3");

        buttonGroup.add(cauDbtn);
        cauDbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cauDbtn.setText("jRadioButton4");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Đáp án:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Thời gian mỗi câu:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("9");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/play45.png"))); // NOI18N
        jButton1.setText("Câu kế tiếp");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("thongtinsv");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Số câu:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cauCbtn)
                    .addComponent(cauBbtn)
                    .addComponent(cauAbtn)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(142, 142, 142))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cauDbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauAbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cauBbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauCbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauDbtn)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cauHoiKeTiep();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FormClient_Play.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormClient_Play.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormClient_Play.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormClient_Play.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClient_Play().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JRadioButton cauAbtn;
    private javax.swing.JRadioButton cauBbtn;
    private javax.swing.JRadioButton cauCbtn;
    private javax.swing.JRadioButton cauDbtn;
    private javax.swing.JTextArea cauhoiTextarea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
