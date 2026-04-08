/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.serviceinfo;

/**
 *
 * @author palma
 */
public class ServiceInfo {
    private String name;
    private String url;
    private String method;
    private String p1;
    private String p2;
    private char symbol;
    
    public ServiceInfo(String name, String url, String method, String p1, String p2, char symbol) {
        this.name = name;
        this.url = url;
        this.method = method;
        this.p1 = p1;
        this.p2 = p2;
        this.symbol=symbol;
    }

    public ServiceInfo(){
        this.name = null;
        this.url = null;
        this.method = null;
        this.p1 = null;
        this.p2 = null;
        symbol=0;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    
    
    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "OpService{" + "url=" + url + ", method=" + method + ", p1=" + p1 + ", p2=" + p2 + '}';
    }
    
    
    
}
