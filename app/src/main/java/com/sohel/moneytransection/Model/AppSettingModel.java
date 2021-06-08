package com.sohel.moneytransection.Model;

public class AppSettingModel {
        private double versionNo;
        private int priority,cashOut,sendMoney,convertBalance,referBonus;

        public AppSettingModel(){

        }

    public AppSettingModel(double versionNo, int priority, int cashOut, int sendMoney, int convertBalance, int referBonus) {
        this.versionNo = versionNo;
        this.priority = priority;
        this.cashOut = cashOut;
        this.sendMoney = sendMoney;
        this.convertBalance = convertBalance;
        this.referBonus = referBonus;
    }

    public double getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(double versionNo) {
        this.versionNo = versionNo;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCashOut() {
        return cashOut;
    }

    public void setCashOut(int cashOut) {
        this.cashOut = cashOut;
    }

    public int getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(int sendMoney) {
        this.sendMoney = sendMoney;
    }

    public int getConvertBalance() {
        return convertBalance;
    }

    public void setConvertBalance(int convertBalance) {
        this.convertBalance = convertBalance;
    }

    public int getReferBonus() {
        return referBonus;
    }

    public void setReferBonus(int referBonus) {
        this.referBonus = referBonus;
    }
}
