package com.example.a062tablasqlit;
//al hacer clic corto sobre un elemento de la lista cambiamos la funcionalidad del toast a colocar
//word y definition en los editText


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class DictionaryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dictionary.db";
    private static final String TABLE_DICTIONARY = "dictionary";
    private static final String FIELD_WORD = "word";
    private static final String FIELD_DEFINITION = "definition";
    private static final int DATABASE_VERSION = 1;

    EditText mEditTextWord;
    EditText mEditTextDefinition;
    DictionaryDatabase mDB;
    ListView mListView;


    DictionaryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_DICTIONARY +
                "(_id integer PRIMARY KEY," +
                FIELD_WORD + " TEXT, " +
                FIELD_DEFINITION + " TEXT);");

        mDB = new DictionaryDatabase(this);
        mEditTextWord =findViewById(R.id.editTextText);
        mEditTextDefinition = findViewById(R.id.editTextText2);
        Button buttonAddUpdate = findViewById(R.id.button);
        buttonAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecord();
            }
        });
        mListView = findViewById(R.id.lista);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, mDB.getDefinition(id), Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Records deleted = " + mDB.deleteRecord(id), Toast.LENGTH_SHORT).show();
                updateWordList();
                return true;
            }
        });
        updateWordList();

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//Handle database upgrade as needed
    }


    public void saveRecord(String word, String definition) {
        long id = findWordID(word);
        if (id>0) {
            updateRecord(id, word,definition);
        } else {
            addRecord(word,definition);
        }
    }
    public long addRecord(String word, String definition) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_WORD, word);
        values.put(FIELD_DEFINITION, definition);
        return db.insert(TABLE_DICTIONARY, null, values);
    }
    public int updateRecord(long id, String word, String definition) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", id);
        values.put(FIELD_WORD, word);
        values.put(FIELD_DEFINITION, definition);
        return db.update(TABLE_DICTIONARY, values, "_id = ?", new String[]{String.valueOf(id)});
    }
    public int deleteRecord(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_DICTIONARY, "_id = ?", new String[]{String.valueOf(id)});
    }
    public long findWordID(String word) {
        long returnVal = -1;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT _id FROM " + TABLE_DICTIONARY + " WHERE " + FIELD_WORD + " = ?",
                new String[]{word});
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            returnVal = cursor.getInt(0);
        }
        return returnVal;
    }
    public String getDefinition(long id) {
        String returnVal = "";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT definition FROM " + TABLE_DICTIONARY + " WHERE _id = ?",
                new String[]{String.valueOf(id)});
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            returnVal = cursor.getString(0);
        }
        return returnVal;
    }
    public Cursor getWordList() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id, " + FIELD_WORD +
                " FROM " + TABLE_DICTIONARY + " ORDER BY " + FIELD_WORD +
                " ASC";
        return db.rawQuery(query, null);
    }
    private void saveRecord() {
        mDB.saveRecord(mEditTextWord.getText().toString(), mEditTextDefinition.getText().toString());
        mEditTextWord.setText("");
        mEditTextDefinition.setText("");
        updateWordList();
    }


    private void updateWordList() {
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                this,android.R.layout.simple_list_item_1,mDB.getWordList(),new String[]{"word"},new int[]{android.R.id.text1},0);
        mListView.setAdapter(simpleCursorAdapter);
    }

}
