package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.common.converter.InvoiceConverter;
import com.minhvu.fruit.dao.implement.AddressDaoImpl;
import com.minhvu.fruit.dao.implement.InvoiceDaoImpl;
import com.minhvu.fruit.dao.implement.ProductDaoImpl;
import com.minhvu.fruit.dao.implement.UserDaoImpl;
import com.minhvu.fruit.dao.interfaces.AddressDao;
import com.minhvu.fruit.dao.interfaces.InvoiceDao;
import com.minhvu.fruit.dao.interfaces.ProductDao;
import com.minhvu.fruit.dao.interfaces.UserDao;
import com.minhvu.fruit.dto.InvoiceDTO;
import com.minhvu.fruit.dto.ItemDTO;
import com.minhvu.fruit.model.*;
import com.minhvu.fruit.service.interfaces.InvoiceService;
import com.minhvu.fruit.model.Item;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceDao invoiceDao = new InvoiceDaoImpl();
    private AddressDao addressDao = new AddressDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private InvoiceConverter invoiceConverter = new InvoiceConverter();

    @Override
    public List<InvoiceDTO> getByUserId(int id_user) {
        List<InvoiceDTO> invoices = new ArrayList<>();
        try {
            List<Invoice> invoiceList = invoiceDao.getByUserId(id_user);
            for (Invoice invoice : invoiceList){
                invoices.add(invoiceConverter.toDTO(invoice));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return invoices;
    }

    @Override
    public InvoiceDTO insert(InvoiceDTO invoiceDTO) {
        InvoiceDTO result = null;
        try {
            Invoice invoice = invoiceConverter.toEntity(invoiceDTO);
            Address address = new Address(invoiceDTO.getAddressDetail(), addressDao.getWardById(invoiceDTO.getIdWard()));
            List<Item> itemList = new ArrayList<>();
            List<ItemDTO> itemDTOS = invoiceDTO.getItems();
            for (ItemDTO itemDTO : itemDTOS){
                Product product = productDao.getById(itemDTO.getProduct().getId());
                itemList.add(new Item(itemDTO.getQuantity(), product.getPrice(), product));
            }
            User user = userDao.getById(invoiceDTO.getIdUser());
            invoice.setUserByIdUser(user);
            invoice.setDate(new Timestamp(System.currentTimeMillis()));
            invoice.setAddressByIdAddress(address);
            invoice.setItemsById(itemList);
            result = invoiceConverter.toDTO(invoiceDao.insert(invoice));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public InvoiceDTO update(InvoiceDTO invoiceDTO) {
        InvoiceDTO result = null;
        try {
            Invoice invoice = invoiceDao.getById(invoiceDTO.getId());
            Address address = new Address(invoiceDTO.getAddressDetail(), addressDao.getWardById(invoiceDTO.getIdWard()));
            List<Item> itemList = new ArrayList<>();
            List<ItemDTO> itemDTOS = invoiceDTO.getItems();
            for (ItemDTO itemDTO : itemDTOS){
                Product product = productDao.getById(itemDTO.getProduct().getId());
                itemList.add(new Item(itemDTO.getQuantity(), product.getPrice(), product));
            }
            invoice.setAddressByIdAddress(address);
            invoice.setItemsById(itemList);
            result = invoiceConverter.toDTO(invoiceDao.update(invoice));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean check = false;
        try {
            invoiceDao.delete(id);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public InvoiceDTO getById(int id) {
        InvoiceDTO result = null;
        try {
            result = invoiceConverter.toDTO(invoiceDao.getById(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<InvoiceDTO> getAll() {
        List<InvoiceDTO> invoices = new ArrayList<>();
        try {
            List<Invoice> invoiceList = invoiceDao.getAll();
            for (Invoice invoice : invoiceList){
                invoices.add(invoiceConverter.toDTO(invoice));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return invoices;
    }
}
