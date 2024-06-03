package main.java.com.ouibak.erp.domain.franchisee;

import java.sql.SQLException;

public class FranchiseeController{
    FranchiseeService service = new FranchiseeService();

    public int validateLogin(String loginId, String password) {
        try {
            return service.validateLogin(loginId, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFranchiseeName(int franchiseeIdx) {
        try {
            return service.getFranchiseeName(franchiseeIdx);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getFranchiseeInfo(int franchiseeIdx) {
        try {
            return service.getFranchiseeInfo(franchiseeIdx);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object[][] getFranchiseeProductRank(int franchiseeIdx) {
        try {
            return service.getFranchiseeRank(franchiseeIdx);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getOrderErrorRate(int franchiseeIdx) {
        try {
            return service.getOrderErrorRate(franchiseeIdx);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrderErrorRate(int franchiseeIdx, String orderErrorRate) {
        try {
            service.updateOrderErrorRate(franchiseeIdx, orderErrorRate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
