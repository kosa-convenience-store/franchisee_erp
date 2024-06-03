package main.java.com.ouibak.erp.domain.franchisee;

import main.java.com.ouibak.erp.domain.franchisee.dto.FranchieeOrderRateDto;
import main.java.com.ouibak.erp.domain.franchisee.dto.FranchiseeDto;
import main.java.com.ouibak.erp.domain.franchisee.dto.FranchiseeInfoDto;
import main.java.com.ouibak.erp.domain.franchisee.dto.FranchiseeProductRankingDto;

import java.sql.SQLException;
import java.util.List;

public interface FranchiseeDao {
    FranchiseeDto validateLogin(String loginId, String password) throws SQLException;

    FranchiseeInfoDto getFranchiseeInfo(int franchiseeIdx) throws SQLException;

    List<FranchiseeProductRankingDto> getFranchiseeRank(int franchiseeIdx) throws SQLException;

    FranchieeOrderRateDto getFranchieeOrderRate(int franchiseeIdx) throws SQLException;

    void updateOrderRate(int franchiseeIdx, int orderRate) throws SQLException;
}
