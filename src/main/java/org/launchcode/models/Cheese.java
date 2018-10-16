package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by LaunchCode
 */
// Many to many video. used this as it has much of the info
// https://www.youtube.com/watch?v=otuxIIbtznA p1
// https://www.youtube.com/watch?v=EeAMVMt2vMU p2 all time stamps after 8:00 are p2

@Entity
public class Cheese {



    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;



    //private CheeseType type;
    @ManyToOne //Many cheeses will be assigned to one cat
    private Category category;

    @ManyToMany(mappedBy = "cheeses")  //3:00 p1
    private List<Menu> menus; //Looks at cheeses property on the Menu class //one side has to have this //x
// Look on this page to see how this works https://education.launchcode.org/skills-back-end-java/studios/cheese-mvc-persistent/many-to-many/

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() { }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public CheeseType getType() {
//        return type;
//    }
//
//    public void setType(CheeseType type) {
//        this.type = type;
//    }
    public Category getCategory() { //added this without being told to(or overlooking it)
    return category;
}

    public void setCategory(Category category) {
        this.category = category;
    }
}
