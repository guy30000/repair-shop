package org.launchcode.repair_shop.models.forms;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Ticket {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, max=50)
    private String itemName;

    private String itemDescription;

    @NotNull
    @Size(min=1, max=1000)
    private String itemIssue;

    private List<String> itemNotes;

}
