package com.abir.roomlivedata.DATABASE;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CART_REPOSITORY {
    CART_DAO cartDao;
    LiveData<CART_MODEL> listMutableLiveData;
    CART_MODEL find_model;

    public CART_REPOSITORY(Application application) {
        ROOM_LIVEDATA_DATABASE db = ROOM_LIVEDATA_DATABASE.getDatabase(application);
        cartDao = db.cartDao();
        listMutableLiveData = cartDao.getAllWords();
    }

    public LiveData<CART_MODEL> getAllCart() {
        return listMutableLiveData;
    }

    public void insert(CART_MODEL word) {
        new insertAsyncTask(cartDao).execute(word);
    }

    public void update(CART_MODEL word) {
        new updateAsyncTask(cartDao).execute(word);
    }

    public void update_single(CART_MODEL word, String o, String n) {
        new update_single_AsyncTask(cartDao, o, n).execute(word);
    }

    public void delete(CART_MODEL word) {
        new deleteAsyncTask(cartDao).execute(word);
    }

    public void deletesingle(CART_MODEL w, String word) {
        new deletesingleAsyncTask(cartDao, word).execute(w);
    }

    public void deleteall(CART_MODEL word) {
        new deleteallAsyncTask(cartDao).execute(word);
    }

    public CART_MODEL find_single(CART_MODEL word) {
        return cartDao.findcart(word.getcartcount());
    }

    private static class insertAsyncTask extends AsyncTask<CART_MODEL, Void, Void> {

        private CART_DAO mAsyncTaskDao;

        insertAsyncTask(CART_DAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CART_MODEL... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<CART_MODEL, Void, Void> {
        private CART_DAO mAsyncTaskDao;

        updateAsyncTask(CART_DAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CART_MODEL... params) {
            mAsyncTaskDao.updatecart(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<CART_MODEL, Void, Void> {

        private CART_DAO mAsyncTaskDao;

        deleteAsyncTask(CART_DAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CART_MODEL... params) {
            mAsyncTaskDao.deletecart(params[0]);
            return null;
        }
    }

    private static class deletesingleAsyncTask extends AsyncTask<CART_MODEL, Void, Void> {

        private CART_DAO mAsyncTaskDao;
        String data;

        deletesingleAsyncTask(CART_DAO dao, String s) {
            mAsyncTaskDao = dao;
            data = s;
        }

        @Override
        protected Void doInBackground(final CART_MODEL... params) {
            mAsyncTaskDao.deletecart_single(data);
            return null;
        }
    }

    private static class deleteallAsyncTask extends AsyncTask<CART_MODEL, Void, Void> {

        private CART_DAO mAsyncTaskDao;

        deleteallAsyncTask(CART_DAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CART_MODEL... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class update_single_AsyncTask extends AsyncTask<CART_MODEL, Void, Void> {

        private CART_DAO mAsyncTaskDao;
        String old_data;
        String new_data;

        update_single_AsyncTask(CART_DAO dao, String o, String n) {
            mAsyncTaskDao = dao;
            old_data = o;
            new_data = n;
        }

        @Override
        protected Void doInBackground(final CART_MODEL... params) {
            mAsyncTaskDao.updatecartdata(old_data, new_data);
            return null;
        }
    }


    //get from database
    public CART_MODEL getuserData() throws ExecutionException, InterruptedException {

        Callable<CART_MODEL> callable = new Callable<CART_MODEL>() {
            @Override
            public CART_MODEL call() throws Exception {
                return cartDao.user_profile();
            }
        };

        Future<CART_MODEL> future = Executors.newSingleThreadExecutor().submit(callable);

        return future.get();
    }
}
