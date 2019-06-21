package org.launchcode.repair_shop.models.data;
import org.launchcode.repair_shop.models.data.PeopleDao;
import org.launchcode.repair_shop.models.forms.NewPeople;

import java.util.ArrayList;

public class PeopleData {
    private PeopleDao peopleDao;

    public ArrayList<NewPeople> searchPeople(String value) {

     ArrayList<NewPeople> searchResults = new ArrayList<>();

        for (NewPeople singlePerson : peopleDao.findAll()){
            System.out.println("Searching cx");

            if (singlePerson.getFirstName().toLowerCase().contains(value.toLowerCase()) ||
                    singlePerson.getLastName().toLowerCase().contains(value.toLowerCase()) ||
                    singlePerson.getEmail().toLowerCase().contains(value.toLowerCase()) ||
                    singlePerson.getPhoneNumber().contains(value)){
                System.out.println("Match ");
                searchResults.add(singlePerson);
            }
        }
        return searchResults;
    }



}
