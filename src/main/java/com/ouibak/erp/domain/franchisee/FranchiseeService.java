package main.java.com.ouibak.erp.domain.franchisee;

import main.java.com.ouibak.erp.domain.franchisee.dto.*;

import java.sql.SQLException;
import java.util.List;

class FranchiseeService {
    FranchiseeDao dao = new FranchiseeDaoImpl();
    int validateLogin(String loginId, String password) throws SQLException {
        FranchiseeDto dto = dao.validateLogin(loginId, password);
        return dto.getFranchiseeIdx();
    }

    //나중에 생각해보고 수정하기
    String getFranchiseeName(int franchiseeIdx) throws SQLException {
        FranchiseeInfoDto dto = dao.getFranchiseeInfo(franchiseeIdx);
        return dto.getFranchiseeName();
    }

    String[] getFranchiseeInfo(int franchiseeIdx) throws SQLException {
        FranchiseeInfoDto dto = dao.getFranchiseeInfo(franchiseeIdx);
        return new String[]{dto.getFranchiseeName(), dto.getFranchiseeAddress(), dto.getFranchiseeBusinessNum(), dto.getFranchiseeTel()};
    }

    int getOrderErrorRate(int franchiseeIdx) throws SQLException {
        FranchieeOrderRateDto dto = dao.getFranchieeOrderRate(franchiseeIdx);
        return dto.getRate();
    }

    void updateOrderErrorRate(int franchiseeIdx, String errorRate) throws SQLException {
        dao.updateOrderRate(franchiseeIdx, Integer.parseInt(errorRate));
    }

    Object[][] getFranchiseeRank(int franchiseeIdx) throws SQLException {
        List<FranchiseeProductRankingDto> dto = dao.getFranchiseeRank(franchiseeIdx);
        Object[][] arr = new Object[dto.size()][2];
        for (int i = 0; i < dto.size(); i++) {
            arr[i][0] = dto.get(i).getProductName();
            arr[i][1] = dto.get(i).getCount();
        }
        return arr;
    }
}
