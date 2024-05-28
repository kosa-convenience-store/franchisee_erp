package main.java.com.ouibak.erp.domain.inventory;

import main.java.com.ouibak.erp.dao.DBDaoImpl;
import main.java.com.ouibak.erp.dao.Query;
import main.java.com.ouibak.erp.domain.franchisee.FranchiseeVO;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoImpl extends DBDaoImpl implements InventoryDao {

    @Override
    public List<InventoryVO> getInventory(int pageNumber, int pageSize) throws SQLException {
        List<InventoryVO> invenList = new ArrayList<>();
        CallableStatement cstmt = getCStmt(Query.getQuery("getInventory"));
        cstmt.setInt(1, FranchiseeVO.getFranchiseeId());
        cstmt.setInt(2, pageNumber);
        cstmt.setInt(3, pageSize);
        cstmt.registerOutParameter(4, OracleTypes.CURSOR);

        cstmt.execute();

        ResultSet rs = (ResultSet) cstmt.getObject(4);
        while (rs.next()) {
            InventoryVO vo = new InventoryVO();
            vo.setProductName(rs.getString(1));
            vo.setCount(rs.getInt(2));
            invenList.add(vo);
        }
        return invenList;
    }
}
