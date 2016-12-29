package com.example.administrator.wanpuapp.model;

/**
 * Created by Administrator on 2016/12/28.
 */

public class CompanyInfoModel{
    private String companyName;
    private String phoneNumber;
    private String password;
    private String companyDes;
    private boolean isVip;
    private String companyId;
    private static CompanyInfoModel instance = null;
    private CompanyInfoModel(){
        companyName="未设置";
        phoneNumber="未设置";
        companyId="未设置";

    }

    public static synchronized CompanyInfoModel getNewInstance(){
        if (instance==null){
            instance = new CompanyInfoModel();
        }
        return instance;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyDes() {
        return companyDes;
    }

    public void setCompanyDes(String companyDes) {
        this.companyDes = companyDes;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
