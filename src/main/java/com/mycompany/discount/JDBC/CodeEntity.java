/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.discount.JDBC;

/**
 *
 * @author pedago
 */
public class CodeEntity {
    
    public String code;
    public float rate;
    
    public CodeEntity(String c, float r){
        this.code = c;
        this.rate = r;
    }
    
}
