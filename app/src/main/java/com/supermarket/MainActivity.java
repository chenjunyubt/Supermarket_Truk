package com.supermarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.supermarket.db.DBManager;
import com.supermarket.hprose.HproseActivity;
import com.supermarket.utils.TTLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @Bind(R.id.input)
    EditText inputEt;
    @Bind(R.id.add)
    Button  btnAdd;
    @Bind(R.id.select)
    public Button btnSelectd;
    private String mDocumentId;

    @OnClick({R.id.add,R.id.select,R.id.goto_hprose})
    public void onClickListener(View view){
        switch(view.getId()){
            case R.id.goto_hprose:
                startActivity(new Intent(MainActivity.this, HproseActivity.class));
                break;
            case R.id.add:
                mDocumentId =  DBManager.getInstance(this).createDocument();
                TTLog.e("---onclick--mDocumentId = " + mDocumentId);
                break;
            case R.id.select:
                Document retrievedDocument = DBManager.getInstance(this).retrieveDatas(mDocumentId);

                TTLog.e("-----retrievedDocument = " + String.valueOf(retrievedDocument.getProperties()));

                new Thread( new Runnable(){
                    @Override
                    public void run() {
                        Database db  = DBManager.getInstance(MainActivity.this).getDatabase();
                        Query query = db.createAllDocumentsQuery();
                        QueryEnumerator erator = query.toLiveQuery().getRows();
                        int count = erator.getCount();
                        TTLog.e("-----count ="+count);
                        for(int i = 0 ;i<erator.getCount();i++){
                            Document document =  erator.getRow(i).getDocument();
                            TTLog.e(i+"-----document = " + String.valueOf(document.getProperties()));
                        }
                    }
                }).start();

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mDocumentId =  DBManager.getInstance(this).createDocument();
        TTLog.e("-----mDocumentId = " + mDocumentId);
    }

}
