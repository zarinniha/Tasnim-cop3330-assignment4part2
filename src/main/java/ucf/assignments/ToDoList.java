/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Zarin Tasninm
 */

package ucf.assignments;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ToDoList {

    static ArrayList<ItemList> list = new ArrayList<>();   //ToDoList ArrayList that holds ItemList


    public static ArrayList<ItemList> getList()
    {
        return list;
    }


    public static ItemList getItem(String name) {

            for (ItemList object : list) {
                if (name.equals(object.getDescription())) {
                    return object;
                }
            }



    return null;

    }

    public static boolean validateDesc(String description) {
        if (description.length() < 257 && description != "") {
            return true;
        } else {
            return false;
        }
    }


    public static void addItem(String description, LocalDate dueDate, boolean iscomplete) {

        ItemList object = new ItemList();

        if (validateDesc(description)) {
            object.setDescription(description);
            object.setDate(dueDate);
            object.setComplete(iscomplete);
            list.add(object);


        }



    }


    public static void updateDesc(String desc,String newdescription) {
        /*
           ->find the item name from the list
           ->if we find it then set the  new Description
         */
          ItemList object=  getItem(desc);
            if (validateDesc(newdescription)&&newdescription!=null) {
                object.setDescription(newdescription);

            }


    }

    public static void updateDate(String desc,LocalDate newDate) {
        /*
           ->find the item name from the list
           ->if we find it then set the  new Description
         */
        ItemList object=  getItem(desc);
        object.setDate(newDate);

    }

    public static void updateStatus(String desc,boolean status) {
        /*
           ->find the item name from the list
           ->if we find it then set the  new Description
         */
        ItemList object=  getItem(desc);
        object.setComplete(status);

    }


    public static void removeItem(String desc)
    {


           ItemList object=  getItem(desc);
           list.remove(object);



    }

    public static void clearItems(String path) throws FileNotFoundException
    {

            list.clear();
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();


    }


    public static void saveList(String path,String itemPath)
    {
        try{
            File file = new File(path);
            FileWriter fw = new FileWriter(new File(path + itemPath));
            for (ItemList object : list) {
                String description = object.getDescription();
                String date = object.getDate().toString();
                String complete = "incomplete";
                if (object.getComplete()) {
                    complete = "complete";
                }

                String text = String.format("Description:%s----" + "Date:%s----" + "Status:%s%n", description, date, complete);


                fw.write(text);

            }

            fw.close();
        }catch(IOException e){

        }
    }



}






