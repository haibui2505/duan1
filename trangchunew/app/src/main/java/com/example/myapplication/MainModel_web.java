package com.example.myapplication;

public class MainModel_web {
    Integer langLogo;
    String langName;


    public MainModel_web(Integer langLogo, String langName) {
        this.langLogo = langLogo;
        this.langName = langName;
    }

    public Integer getLangLogo() {
        return langLogo;
    }

    public void setLangLogo(Integer langLogo) {
        this.langLogo = langLogo;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }
}
