package app.phone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.phone.common.DBManager;
import app.phone.dto.Phone;

public class PhoneDao {
    
    public int insertPhone(Phone phone) {
        int ret = -1;
        String sql = "INSERT INTO phone VALUES(?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, phone.getPhoneId());
            pstmt.setString(2, phone.getPhoneName());
            pstmt.setInt(3, phone.getPhoneprice());
            pstmt.setString(4, phone.getPhonemaker());
            pstmt.setInt(5, phone.getPhoneremain());

            ret = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(pstmt, con);
        }

        return ret;
    }

    public Phone detailPhone(int phoneId) {
        Phone phone = new Phone();
        String sql = "SELECT * FROM phone WHERE phoneid = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, phoneId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                phone.setPhoneId(rs.getInt("phoneid"));
                phone.setPhoneName(rs.getString("phonename"));
                phone.setPhoneprice(rs.getInt("phoneprice"));
                phone.setPhonemaker(rs.getString("phonemaker"));
                phone.setPhoneremain(rs.getInt("phoneremain"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(pstmt, con);
        }

        return phone;
    }

    public List<Phone> listPhone() {
        List<Phone> list = new ArrayList<>();

        String sql = "SELECT * FROM phone;";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setPhoneId(rs.getInt("phoneid"));
                phone.setPhoneName(rs.getString("phonename"));
                phone.setPhoneprice(rs.getInt("phoneprice"));
                phone.setPhonemaker(rs.getString("phonemaker"));
                phone.setPhoneremain(rs.getInt("phoneremain"));
                list.add(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(pstmt, con);
        }

        return list;
    }


    public int updatePhone(Phone phone) {
        int ret = -1;
        String sql = "UPDATE phone SET phonename = ?, phoneprice = ?, phonemaker = ?, phoneremain = ? WHERE phoneid = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, phone.getPhoneName());
            pstmt.setInt(2, phone.getPhoneprice());
            pstmt.setString(3, phone.getPhonemaker());
            pstmt.setInt(4, phone.getPhoneremain());
            pstmt.setInt(5, phone.getPhoneId());

            ret = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(pstmt, con);
        }

        return ret;
    }
}
