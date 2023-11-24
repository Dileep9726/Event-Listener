package com.example.eventlistener;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserActions {

    private static final String DB_NAME = "events";
    private final AppDatabase userDB;
    private ExecutorService executorService;

    public UserActions(Context context) {
        userDB = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public LiveData<List<DataModel>> getLiveUsers() {
        return userDB.queryDao().fetchAllUsers();
    }

    public List<DataModel> getAllUsers() throws ExecutionException, InterruptedException {
        return new getAllAsyncTask(userDB).execute().get();
    }


    private static class getAllAsyncTask extends
            android.os.AsyncTask<Void, Void, List<DataModel>> {

        private final AppDatabase userDB;

        getAllAsyncTask(AppDatabase dao) {
            userDB = dao;
        }

        @Override
        protected List<DataModel> doInBackground(Void... voids) {
            return userDB.queryDao().getAllUsersList();
        }
    }

    public void insertTask(String event, String desc, String time) {

        DataModel data = new DataModel();
        data.setAction(event);
        data.setDescription(desc);
        data.setTime(time);

        insert(data);
    }

    public void insert(final DataModel dataModel) {
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> userDB.queryDao().insertTask(dataModel));
    }

    public void delete(final int id) {
        final LiveData<DataModel> user = getUser(id);
        if (user != null) {
            executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> userDB.queryDao().deleteSpecific(id));
        }
    }

    public void update(final DataModel userData) {
        final LiveData<DataModel> user = getUser(userData.getId());
        if (user != null) {
            executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> userDB.queryDao().updateEvents(userData));
        }
    }

    public void deleteAll() {
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> userDB.queryDao().deleteAll());
    }

    public LiveData<DataModel> getUser(int id) {
        return userDB.queryDao().getUser(id);
    }
}
