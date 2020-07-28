package com.minhvu.fruit.common.converter;

import com.minhvu.fruit.dto.InvoiceDTO;
import com.minhvu.fruit.dto.ItemDTO;
import com.minhvu.fruit.model.Address;
import com.minhvu.fruit.model.Invoice;
import com.minhvu.fruit.model.Item;

import java.util.ArrayList;
import java.util.List;

public class InvoiceConverter implements Converter<InvoiceDTO, Invoice> {
    private ItemConverter itemConverter = new ItemConverter();

    @Override
    public InvoiceDTO toDTO(Invoice invoice) {
        Address adr = invoice.getAddressByIdAddress();
        String address = adr.getDetailAddress() + ", " + adr.getWardByIdWard().getName()
                + ", " + adr.getWardByIdWard().getDistrictByIdDistrict().getName()
                + ", " + adr.getWardByIdWard().getDistrictByIdDistrict().getCityByIdCity().getName();
        List<ItemDTO> items = new ArrayList<>();
        List<Item> itemList = (List<Item>) invoice.getItemsById();
        for (Item item : itemList){
            items.add(itemConverter.toDTO(item));
        }
        InvoiceDTO invoiceDTO = new InvoiceDTO(invoice.getId(),invoice.getUserByIdUser().getId(),invoice.getDate(),address,invoice.getEmail(),invoice.getNumberphone(),invoice.getNameUser(),items,adr.getWardByIdWard().getId(),adr.getDetailAddress());
        return invoiceDTO;
    }

    @Override
    public Invoice toEntity(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice(invoiceDTO.getEmail(), invoiceDTO.getNumberphone(), invoiceDTO.getNameUser(), null, null, null);
        return invoice;
    }
}
