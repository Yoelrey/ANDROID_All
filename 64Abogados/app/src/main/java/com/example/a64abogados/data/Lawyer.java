package com.example.a64abogados.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;


import java.util.UUID;

/**
 * Entidad "abogado"
 */
public class Lawyer {
    private String id;
    private String name;
    private String specialty;
    private String phoneNumber;
    private String bio;
    private String avatarUri;

    public Lawyer(String name,
                  String specialty, String phoneNumber,
                  String bio, String avatarUri) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.avatarUri = avatarUri;
    }

    @SuppressLint("Range")
    public Lawyer(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(LawyersContract.LawyerEntry.ID));
        name = cursor.getString(cursor.getColumnIndex(LawyersContract.LawyerEntry.NAME));
        specialty = cursor.getString(cursor.getColumnIndex(LawyersContract.LawyerEntry.SPECIALTY));
        phoneNumber = cursor.getString(cursor.getColumnIndex(LawyersContract.LawyerEntry.PHONE_NUMBER));
        bio = cursor.getString(cursor.getColumnIndex(LawyersContract.LawyerEntry.BIO));
        avatarUri = cursor.getString(cursor.getColumnIndex(LawyersContract.LawyerEntry.AVATAR_URI));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(LawyersContract.LawyerEntry.ID, id);
        values.put(LawyersContract.LawyerEntry.NAME, name);
        values.put(LawyersContract.LawyerEntry.SPECIALTY, specialty);
        values.put(LawyersContract.LawyerEntry.PHONE_NUMBER, phoneNumber);
        values.put(LawyersContract.LawyerEntry.BIO, bio);
        values.put(LawyersContract.LawyerEntry.AVATAR_URI, avatarUri);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public String getAvatarUri() {
        return avatarUri;
    }
}
