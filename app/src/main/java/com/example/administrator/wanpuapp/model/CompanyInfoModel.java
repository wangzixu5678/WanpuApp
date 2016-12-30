package com.example.administrator.wanpuapp.model;

/**
 * Created by Administrator on 2016/12/28.
 */

public class CompanyInfoModel{
    private String companyName;
    private String phoneNumber;
    private String companyDes;
    private boolean isVip;
    private String companyId;
    private String companyLogo;
    private String companyAddress;
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

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
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
