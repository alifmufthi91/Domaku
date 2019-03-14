package com.example.kienz.domaku;

import java.util.ArrayList;

public class kategori {
    String title;
    ArrayList<donasi> eventList;

    public kategori(String title, ArrayList<donasi> eventList) {
        this.title = title;
        this.eventList = eventList;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<donasi> getEventList() {
        return eventList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEventList(ArrayList<donasi> eventList) {
        this.eventList = eventList;
    }

    public void add(donasi event){
        this.eventList.add(event);
        if(this.eventList.size() > 6)
            this.eventList.remove(eventList.size() - 1);
    }

    public void add(int pos, donasi event){
        this.eventList.add(pos,event);
        if(this.eventList.size() > 6)
            this.eventList.remove(eventList.size() - 1);
    }


}
