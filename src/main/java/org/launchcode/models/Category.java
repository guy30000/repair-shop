package org.launchcode.models;

// All from p1 unless specified
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity //this ias an annotation
public class Category {



    @Id //this ias an annotation
    @GeneratedValue
    private int id; //field

    @NotNull
    @Size(min=3, max=15)
    private String name; //property (intellejay calles it a field

    @OneToMany  //Each cat will have many cheese. I gues the cheese side will have a many to one
    @JoinColumn(name = "category_id")
    private List<Cheese> cheeses = new ArrayList<>();

    //I think these are constructorsv
    public Category(String name) {
        this.name = name;
    }

    public Category() { }
    //I think these are constructors^


    ///missing private list cheese

    public int getId() { //getters and setters are methods
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}