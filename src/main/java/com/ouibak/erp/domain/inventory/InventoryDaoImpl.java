package main.java.com.ouibak.erp.domain.inventory;

import main.java.com.ouibak.erp.common.dao.DBDao;
import main.java.com.ouibak.erp.common.query.Query;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoImpl extends DBDao implements InventoryDao {

    @Override
    public List<InventoryDto> getInventory(int pageNumber, int pageSize, int franchiseeIdx) throws Exception {
        List<InventoryDto> list = new ArrayList<>();

        CallableStatement cstmt = getCallableStatement(Query.getQuery("getInventory"));
        cstmt.setInt(1, franchiseeIdx);
        cstmt.setInt(2, pageNumber);
        cstmt.setInt(3, pageSize);
        cstmt.registerOutParameter(4, OracleTypes.CURSOR);
        cstmt.execute();

        ResultSet rs = (ResultSet) cstmt.getObject(4);
        while (rs.next()) {
            list.add(new InventoryDto(rs.getString(1), rs.getInt(2)));
        }
        return list;
    }
}
