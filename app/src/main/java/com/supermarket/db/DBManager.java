package com.supermarket.db;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.UnsavedRevision;
import com.couchbase.lite.android.AndroidContext;
import com.supermarket.utils.TTLog;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**  Couchbase Lite
 * Created by chenjunyu on 2016-07-08.
 */
public class DBManager {
    private static DBManager instance = null;
    private static Database database;
    private static final String DATABASE_NAME = "supermarket_db";

    private DBManager(Context context){
        Manager manager;
        try{
          manager = new Manager(new AndroidContext(context.getApplicationContext()), Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            TTLog.e("Cannot create Manager object", e);
            return;
        }

        try {
            if(null != manager) {
                database = manager.getDatabase(DATABASE_NAME);
            }
        } catch (CouchbaseLiteException e) {
            TTLog.e("Cannot get Database", e);
            return;
        }
        }

    public static DBManager getInstance(Context context){
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager(context);
                }
            }
        }

        return instance;
    }

    public static Database getDatabase(){
        return database;
    }

    public static void addDatas(){
        Document document = database.createDocument();
    }

    public static Document retrieveDatas(String documentID){
        Document retrievedDocument = database.getDocument(documentID);
        return retrievedDocument;
    }

    public static void updateDatas(Document retrievedDocument){
        Map<String, Object> updatedProperties = new HashMap<String, Object>();
        updatedProperties.putAll(retrievedDocument.getProperties());
        updatedProperties.put("new item", "adding new entry into existing document");
        updatedProperties.put("Database Used", "My database is Couchbase Mobile");

        try {
            retrievedDocument.putProperties(updatedProperties);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Document retrievedDocument){
        try {
            retrievedDocument.delete();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public static void deleteById(String id){
        try {
            database.deleteLocalDocument(id);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public static String createDocument() {
        // Create a new document and add data
        Document document = database.createDocument();
        String documentId = document.getId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Big Party");
        map.put("location", "My House");
        try {
            // Save the properties to the document
            document.putProperties(map);
        } catch (CouchbaseLiteException e) {
            TTLog.e("Error putting", e);
        }
        return documentId;
    }

    public static void updateDoc(Database database, String documentId) {
        Document document = database.getDocument(documentId);
        try {
            // Update the document with more data
            Map<String, Object> updatedProperties = new HashMap<String, Object>();
            updatedProperties.putAll(document.getProperties());
            updatedProperties.put("eventDescription", "Everyone is invited!");
            updatedProperties.put("address", "123 Elm St.");
            // Save to the Couchbase local Couchbase Lite DB
            document.putProperties(updatedProperties);
        } catch (CouchbaseLiteException e) {
            TTLog.e( "Error putting", e);
        }
    }

    public static void addAttachment(Database database, String documentId) {
        Document document = database.getDocument(documentId);
        try {
        /* Add an attachment with sample data as POC */
            ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] { 0, 0, 0, 0 });
            UnsavedRevision revision = document.getCurrentRevision().createRevision();
            revision.setAttachment("binaryData", "application/octet-stream",inputStream); //MIME type inputStream
        /* Save doc & attachment to the local DB */
            revision.save();
        } catch (CouchbaseLiteException e) {
            TTLog.e( "Error putting", e);
        }
    }
}
