package com.example.Room1;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private EventRepository mRepository;
    private LiveData<List<Event>> mAllEvent;

    public EventViewModel(Application application) {
        super(application);
        mRepository = new EventRepository(application);
        mAllEvent = mRepository.getAllEvent();
    }

    public LiveData<List<Event>> getAllEvent(){return mAllEvent;}

    public void insert(Event event){mRepository.insert(event);}

    public LiveData<List<Event>> findEventByDate(String date){return mRepository.findEventByDate(date);}
}
