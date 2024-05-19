package com.example.demodeks.entities;

public class HotelBill {
    private int id;
    private static int autoId =0;
    private int roomNumber;
    private double donGia;
    private int soNgay;
    private String HoTen;

    public HotelBill( int roomNumber, double donGia, int soNgay, String HoTen) {
        this.id = ++autoId;
        this.roomNumber = roomNumber;
        this.donGia = donGia;
        this.soNgay = soNgay;
        this.HoTen = HoTen;
    }

    public HotelBill(int id, int roomNumber, double donGia, int soNgay, String hoTen) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.donGia = donGia;
        this.soNgay = soNgay;
        HoTen = hoTen;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double total() {
        return donGia*soNgay;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }
}
