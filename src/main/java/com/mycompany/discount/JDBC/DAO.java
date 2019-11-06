/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.discount.JDBC;

import com.mycompany.discount.JDBC.CodeEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author pedago
 */
public class DAO {
    private final DataSource myDataSource;
    
    public DAO(DataSource ds){
        this.myDataSource = ds;
    }
    
    public List<CodeEntity> listingCode() throws SQLException{
        List<CodeEntity> ce = new ArrayList<>();
        String sql = "SELECT * FROM DISCOUNT_CODE";
        try(Connection co = myDataSource.getConnection();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);){
            while(rs.next()){
                String dsc = rs.getString("DISCOUNT_CODE");
                float remise = rs.getFloat("RATE");
                ce.add(new CodeEntity(dsc, remise));
            }
        return ce;
        }
    }
    
    public void addCode(String code, Float rate) throws SQLException{
        String sql = "INSERT INTO DISCOUNT_CODE(DISCOUNT_CODE, RATE) VALUES (?,?)";
        try(Connection co = myDataSource.getConnection();
            PreparedStatement stm = co.prepareStatement(sql);){
            stm.setString(1,code);
            stm.setFloat(2,rate);
            stm.executeQuery();
        }
    }
    
    public void deleteCode(String code) throws SQLException{
        String sql = "DELETE FROM APP.DISCOUNT_CODE WHERE DISCOUNT_CODE.DISCOUNT_CODE = ?";
        try(Connection co = myDataSource.getConnection();
            PreparedStatement stm = co.prepareStatement(sql);){
            stm.setString(1, code);
            stm.executeQuery();
        }
    }
}
