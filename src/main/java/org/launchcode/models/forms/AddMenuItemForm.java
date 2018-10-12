package org.launchcode.models.forms;
// https://www.youtube.com/watch?v=EeAMVMt2vMU&t=29s p2 view all at 12:00
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {

//11:45
    private Menu menu;

    private Iterable<Cheese> cheeses;  //renders drop down
    @NotNull
    private int menuId;
    @NotNull
    private int cheeseId;
    //constructore v
    public AddMenuItemForm() { }

    public AddMenuItemForm(Iterable<Cheese> cheeses, Menu menu) {
        this.menu = menu;
        this.cheeses = cheeses;

    }
    //constructore ^

    //getters and setters (accessors)// I think maybe the getter is teh accessor
    public int getMenuId() {
        return menuId;
    } //x

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    } //x

    public int getCheeseId() {
        return cheeseId;
    } //x

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    } //x


    public Menu getMenu() {
        return menu;
    } //x

//    public void setMenu(Menu menu) {
//        this.menu = menu;
//    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    } //x

//    public void setCheeses(Iterable<Cheese> cheeses) {
//        this.cheeses = cheeses;
//    }

}