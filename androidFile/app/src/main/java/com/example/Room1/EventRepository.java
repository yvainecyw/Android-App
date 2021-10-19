package com.example.Room1;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.example.shuafeia.MainActivity;

import java.util.List;

public class EventRepository {
    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvent;

    EventRepository(Application application){
        EventRoomDatabase db = EventRoomDatabase.getDatabase(application);
        mEventDao = db.eventDao();
        mAllEvent = mEventDao.getAllEvents();
    }

    LiveData<List<Event>> getAllEvent(){
        return mAllEvent;
    }

    public void insert(Event event){
        new insertAsyncTask(mEventDao).execute(event);
    }

    //LiveData<List<Event>> findEventByDate(String Date){return mEventDao.findEventByDate(Date);}
    LiveData<List<Event>> findEventByDate(String Date){return mEventDao.findEventByDate(Date);}

    private static class insertAsyncTask extends AsyncTask<Event,Void,Void> {
        private EventDao mAsyncTaskDao;

        insertAsyncTask(EventDao mEventDao) {
            mAsyncTaskDao = mEventDao;
        }

        @Override
        protected Void doInBackground(final Event... events) {
            mAsyncTaskDao.insert(events[0]);
            return null;
        }
    }
}
