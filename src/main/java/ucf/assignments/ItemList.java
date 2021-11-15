/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Zarin Tasninm
 */

package ucf.assignments;

import java.time.LocalDate;

public class ItemList {

    String description;    //the description of the item
    LocalDate date;        //the due date of the item
    boolean isComplete;     //the boolean of the item whether it is completed or not

    //set the date of the item

    public void setDate(LocalDate newDate) {

        date = newDate;
    }

    //set the description of the item

    public void setDescription(String newDescription) {

        description = newDescription;

    }

   //set the status of the item

    public void setComplete(boolean status) {
        isComplete = status;
    }


    //get the date of the item

    public LocalDate getDate() {
        return date;
    }

    //get the description of the item

    public String getDescription() {

        return description;
    }


    //get the status of the item

    public boolean getComplete() {
        return isComplete;
    }

}