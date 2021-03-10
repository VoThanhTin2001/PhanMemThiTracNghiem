/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author nmthe
 */
public class NoiDungCauHoi {
    private int causo;
    private String noidung;
    private String cauA;
    private String cauB;
    private String cauC;
    private String cauD;
    private String dapAn;
    private boolean hinh;
    
    public boolean getHinh(){
        return hinh;
    }
    
    public void setHinh(boolean hinh){
        this.hinh = hinh;
    }

    public int getCauso() {
        return causo;
    }

    public void setCauso(int causo) {
        this.causo = causo;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getCauA() {
        return cauA;
    }

    public void setCauA(String cauA) {
        this.cauA = cauA;
    }

    public String getCauB() {
        return cauB;
    }

    public void setCauB(String cauB) {
        this.cauB = cauB;
    }

    public String getCauC() {
        return cauC;
    }

    public void setCauC(String cauC) {
        this.cauC = cauC;
    }

    public String getCauD() {
        return cauD;
    }

    public void setCauD(String cauD) {
        this.cauD = cauD;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

}
