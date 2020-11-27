package com.example.demo.Model;

public class model_product {
    private String Id;
    private String Title;
    private String Address;
    private String Image;
    private String Money;
    private Integer Exchange;
    private String Kind;
    private String AgeOfPet;
    private String More;
    private String Date;

    public model_product() {
    }

    public model_product(String id, String title, String address, String image, String money, Integer exchange, String kind, String ageOfPet, String more, String date) {
        Id = id;
        Title = title;
        Address = address;
        Image = image;
        Money = money;
        Exchange = exchange;
        Kind = kind;
        AgeOfPet = ageOfPet;
        More = more;
        Date = date;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public Integer getExchange() {
        return Exchange;
    }

    public void setExchange(Integer exchange) {
        Exchange = exchange;
    }

    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public String getAgeOfPet() {
        return AgeOfPet;
    }

    public void setAgeOfPet(String ageOfPet) {
        AgeOfPet = ageOfPet;
    }

    public String getMore() {
        return More;
    }

    public void setMore(String more) {
        More = more;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
