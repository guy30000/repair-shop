package org.launchcode.models;

// Many to many video. used this as it has much of the info
// https://www.youtube.com/watch?v=otuxIIbtznA p1 (view this entire sheet at begining of video (2:00)
// https://www.youtube.com/watch?v=EeAMVMt2vMU p2 all time stamps after 8:00 are p2
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity  //this ias an annotation
public class Menu {


    @NotNull
    @Size(min=3, max=15)
    private String name;

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany  //2:00 p1
    private List<Cheese> cheeses;
    //private List<Cheese> Cheeses = new ArrayList<>();   //It may be this
//Constructors
    public Menu(String name) {
        this.name = name;
    }

    public Menu() { }


    //not sure about this thing
    //public void addItem(Cheese item){ }  //this is givin in the instructions
    public void addItem(Cheese item){ cheeses.add(item);} //On my prior project and youtube this is the line

    ///Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

//    public void setCheese(List<org.launchcode.models.Cheese> cheese) {
//        Cheeses = cheese;
//    }   //this is not to have getter and setter





}
