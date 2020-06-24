package com.minhvu.fruit.dao.interfaces;

import com.minhvu.fruit.model.Invoice;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceDao extends BaseDao<Invoice> {
    List<Invoice> getByUserId(int id_user) throws SQLException;
}
