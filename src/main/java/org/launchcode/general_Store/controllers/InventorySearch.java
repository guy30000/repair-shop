package org.launchcode.general_Store.controllers;

import org.launchcode.general_Store.models.Inventory;
import java.util.ArrayList;

import org.launchcode.general_Store.models.Inventory;
import org.launchcode.general_Store.models.data.InventoryDao;
import org.launchcode.general_Store.models.forms.ReceiveInvForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

public class InventorySearch {

    @Autowired
    private InventoryDao inventoryDao;

    public void InventorySearch(String keyword) {

        ArrayList<Inventory> searchResults = new ArrayList<>();
        for (Inventory singleItem : inventoryDao.findAll()) {
            if (singleItem.getName().toLowerCase().contains(keyword.toLowerCase()) || singleItem.getVendor().toLowerCase().contains(keyword.toLowerCase()) || singleItem.getSku().equalsIgnoreCase(keyword)) {
                searchResults.add(singleItem);
            }
          //  return searchResults;
        }
    }
}
