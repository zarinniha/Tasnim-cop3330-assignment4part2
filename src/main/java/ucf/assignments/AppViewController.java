/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Zarin Tasninm
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.*;
import java.time.LocalDate;

public class AppViewController {

    String path="C:\\Users\\zarin\\IdeaProjects\\Tasnim-cop333-assignment4part2";
    String itemFile="\\list.txt";


    @FXML
    private DatePicker DueDate;  //for the due date

    @FXML
    private CheckBox ItemComplete;  //button to update an item as complete

    @FXML
    private CheckBox ItemIncomplete;   //button to update an item as incomplete

    @FXML
    private TextArea ItemDesc;    //text area where the item description is being written


    @FXML
    private ListView itemList;    //the list area where the items of the list is being shown


    ToDoList list = new ToDoList();   //the arraylist if ItemList
    ItemList item;                    //an object of the ItemList

    void clearView()
    {
        itemList.getItems().clear();
    }

    void refresh()
    {
        ItemDesc.clear();
        ItemComplete.setSelected(false);
        ItemIncomplete.setSelected(false);
        DueDate.setValue(LocalDate.now());
    }

    void DisplayList()
    {
        clearView();

        for(ItemList object:list.getList())
        {
            boolean result = object.getComplete();
            String status;

            if(!result)
            {
                status="incomplete";  //if the result is false, then the task in incomplete
            }else
            {
                status="complete";    //if the result is true, then the task in complete
            }

            //create a string  with the attributes of the item

            String textAreaString=String.format("%s %s %s\n",object.getDescription(),object.getDate(),status );
            itemList.getItems().add(textAreaString);

        }

    }




    @FXML
    void ClearList(ActionEvent event)
    {
        clearView();

    }

    @FXML
    void DisplayAll(ActionEvent event) {

          DisplayList();


    }

    @FXML
    void displayComplete(ActionEvent event) {


        clearView();

        for (ItemList object : list.getList()) {
            boolean result = object.getComplete();
            String status;

            if (result) {
                status = "complete";
                String textAreaString = String.format("%s %s %s\n", object.getDescription(), object.getDate(), status);
                itemList.getItems().add(textAreaString);
            }


        }
    }

    @FXML
    void displayIncomplete(ActionEvent event) {


        clearView();

        for (ItemList object : list.getList()) {
            boolean result = object.getComplete();
            String status;

            if (!result) {
                status = "incomplete";
                String textAreaString = String.format("%s %s %s\n", object.getDescription(), object.getDate(), status);
                itemList.getItems().add(textAreaString);
            }


        }

    }

    @FXML
    void AddItem(ActionEvent event) {

        if(ItemDesc.getText()!=null && DueDate.getValue()!=null)
        {
            list.addItem(ItemDesc.getText(), DueDate.getValue(), ItemComplete.isSelected());
            DisplayList();

        }
        refresh();


    }


    String getCurDesc()
    {
        String obj=itemList.getSelectionModel().getSelectedItem().toString();
        String[] parts = obj.split(" ");
        String curDesc=parts[0];
        return curDesc;
    }

    @FXML
    void EditDesc(ActionEvent event)
    {
        String currentDesc=getCurDesc();
        list.updateDesc(currentDesc,ItemDesc.getText());
        refresh();
        DisplayList();


    }

    @FXML
    void EditDate(ActionEvent event) {

        String currentDesc=getCurDesc();
        list.updateDate(currentDesc,DueDate.getValue());
        refresh();
        DisplayList();

    }

    @FXML
    void EditStatus(ActionEvent event)
    {
        boolean status =false;
        if(ItemComplete.isSelected()) //if the selected item's status is true
        {
            status=true;
        }
        String currentDesc=getCurDesc();
        System.out.print(currentDesc);
        list.updateStatus(currentDesc,status);
        refresh();
        clearView();
        DisplayList();
    }



    @FXML
    void DeletItem(ActionEvent event)throws IOException {

        String desc=getCurDesc();
        list.removeItem(desc);
        list.saveList(path,itemFile);
        DisplayList();

    }


    @FXML
    void EmptyList(ActionEvent event) throws FileNotFoundException{

        String itemPath= path+itemFile;
        list.clearItems(itemPath);
        clearView();

    }


    @FXML
    void LoadItemButton(ActionEvent event) throws FileNotFoundException
    {
        String itemPath= path+itemFile;
        clearView();

        try {

            File file = new File(itemPath);
            FileReader reader= new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while((line = br.readLine()) != null) {
                ItemList obj = new ItemList();
                String[] parts = line.split("----");


                //set description
                String[] Descriptionpart = parts[0].split(":");
                String description;
                description = Descriptionpart[1];

                //set date
                String[] Datepart = parts[1].split(":");
                String[] properDate = Datepart[1].split("-");
                //split the date into year,month and date
                LocalDate date = LocalDate.of(Integer.parseInt(properDate[0]),Integer.parseInt(properDate[1]),Integer.parseInt(properDate[2]));

                //set status
                String[] Completepart = parts[2].split(":");
                String result = "incomplete";
                boolean status =false;
                if(Completepart[1].equals("complete")) {
                    result = "complete";
                    status=true;
                }

                list.removeItem(description);
                list.addItem(description,date,status);

                //Add new item.
                String text=String.format("%s %s %s\n",description,date,result);
                itemList.getItems().add(text);
            }

            br.close();

        }catch(Exception e){

        }



    }

    @FXML
    void saveItemButton(ActionEvent event) {

        list.saveList(path,itemFile);

    }



    @FXML
    void RefreshList(ActionEvent event) {

      refresh();

    }





}
