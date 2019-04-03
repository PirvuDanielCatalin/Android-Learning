package com.example.tema_1;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {
    private AppDatabase appDatabase;

    public UserRepository(Context context) {
        appDatabase = ApplicationController.getAppDatabase();
    }

    public void insertTask(final User user, final OnUserRepositoryActionListener listener) {
        new InsertTask(listener).execute(user);
    }
    public List<User> getTask(final OnUserRepositoryActionListener listener) {
        try {
            return new GetTask(listener).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public User getUserTask(String fName, String lName,final OnUserRepositoryActionListener listener) {
        try {
            return new GetUserTask(listener).execute(fName,lName).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public void deleteTask(final User user, final OnUserRepositoryActionListener listener) {
        new DeleteTask(listener).execute(user);
    }

    public User getUserByName(String firstName, String lastName) {
        return appDatabase.userDao().findByName(firstName, lastName);
    }


    // DO NOT PERFORM OPERATION ON MAIN THREAD AS APP WILL CRASH
    // See more details about AsyncTask in the next chapter
    private class InsertTask extends AsyncTask<User, Void, Void> {
        OnUserRepositoryActionListener listener;

        InsertTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            appDatabase.userDao().insertTask(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }
    private class GetTask extends AsyncTask<Void, Void , List <User> > {
        OnUserRepositoryActionListener listener;

        GetTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<User> doInBackground(Void... url) {
            return appDatabase.userDao().getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            listener.actionSuccess();
        }
    }
    private class GetUserTask extends AsyncTask<String, Void , User > {
        OnUserRepositoryActionListener listener;

        GetUserTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected User doInBackground(String... name) {
            return appDatabase.userDao().findByName(name[0],name[1]);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            listener.actionSuccess();
        }
    }
    private class DeleteTask extends AsyncTask<User, Void, Void> {
        OnUserRepositoryActionListener listener;

        DeleteTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            appDatabase.userDao().delete(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }
}