/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Zarin Tasninm
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {



    @Test
    void validateDesc() {

        ToDoList test1=new ToDoList();
        boolean result1= test1.validateDesc("work");
        boolean result2= test1.validateDesc("");

        assertEquals(true,result1);
        assertEquals(false,result2);


    }

    @Test
    void addItem() {

        ToDoList test2=new ToDoList();
        test2.addItem("work",LocalDate.now(),false);
        test2.addItem("school",LocalDate.now(),false);

        ArrayList<ItemList> list = test2.getList();
        boolean status=false;
        if(list.size()==2)
        {
            status=true;
        }

        assertEquals(true,status);
        list.clear();
    }

    @Test
    void updateDesc() {
        String result1=null;
        String result2=null;

        ToDoList test3=new ToDoList();
        test3.addItem("work",LocalDate.now(),false);
        ArrayList<ItemList> list = test3.getList();
        for(ItemList item : list){
           result1= item.getDescription();
        }

        test3.updateDesc("work","school");

        for(ItemList item : list){
            result2= item.getDescription();
        }

        assertEquals("work",result1);
        assertEquals("school",result2);

        list.clear();


    }

    @Test
    void updateDate() {

        LocalDate result1=null;
        LocalDate result2=null;

        ToDoList test4=new ToDoList();
        test4.addItem("work",LocalDate.now(),false);

        ArrayList<ItemList> list = test4.getList();

        for(ItemList item : list){
            result1= item.getDate();
        }

        test4.updateDate("work",LocalDate.of(2021, 1, 8));


        for(ItemList item : list){
            result2= item.getDate();
        }

        assertEquals(LocalDate.now(),result1);
        assertEquals(LocalDate.of(2021, 1, 8),result2);
        list.clear();
    }

    @Test
    void updateStatus() {

        boolean result1=false;
        boolean result2=false;

        ToDoList test5=new ToDoList();
        test5.addItem("work",LocalDate.now(),false);
        ArrayList<ItemList> list = test5.getList();
        for(ItemList item : list){
            result1= item.getComplete();
        }
        test5.updateStatus("work",true);
           for(ItemList item : list){
            result2= item.getComplete();
        }


        assertEquals(false,result1);
        assertEquals(true,result2);
        list.clear();
    }

    @Test
    void removeItem() {

        ToDoList test6=new ToDoList();
        test6.addItem("work",LocalDate.now(),false);
        test6.addItem("school",LocalDate.now(),false);

        ArrayList<ItemList> list = test6.getList();
        int result1=list.size();
        test6.removeItem("work");
        int result2=list.size();

        boolean status=false;
        if(result2==result1-1)
        {
            status=true;
        }

        assertEquals(true,status);
        list.clear();
    }


}