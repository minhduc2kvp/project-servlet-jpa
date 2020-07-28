package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService extends BaseService<InvoiceDTO> {
    List<InvoiceDTO> getByUserId(int id_user);
}
