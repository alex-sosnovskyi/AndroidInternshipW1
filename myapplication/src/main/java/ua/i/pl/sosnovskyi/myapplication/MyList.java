package ua.i.pl.sosnovskyi.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Sosnovskyi on 24.10.2017.
 */

public class MyList extends ArrayList {
    private List<String> itemList;

    public MyList() {
        this.itemList = new ArrayList<>();
    }
    public MyList(List list){
        this.itemList=list;
    }
    public List<String> getItemList(){
        return new ArrayList<>(itemList);
    }
    public void addItem(String string){
        if (string.length()!=0) {
            itemList.add(string);
        }
    }
    public String getItem(int pos){
        if (pos<itemList.size()){
          return   itemList.get(pos);
        }
        return null;
    }
}
