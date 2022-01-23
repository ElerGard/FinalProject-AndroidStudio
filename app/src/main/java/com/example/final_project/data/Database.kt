package com.example.final_project.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "Painter"

        private val TABLE_USERS = "Users"

        private val KEY_NAME = "name"
        private val KEY_PASS = "pass"

        // for galery on PicturesFragment
        private val TABLE_PICTURES = "Users"
        private val KEY_PICTURE = "pictures"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USERS_TABLE = ("CREATE TABLE " + TABLE_USERS + "("
                + KEY_NAME + " TEXT PRIMARY KEY," + KEY_PASS + " TEXT"
                + ")")
        db?.execSQL(CREATE_USERS_TABLE)
        
// For galery
//        val CREATE_PICTURES_TABLE = ("CREATE TABLE " + TABLE_PICTURES + "("
//                + KEY_NAME + " TEXT PRIMARY KEY," + KEY_PICTURE + " TEXT"
//                + ")")
//        db?.execSQL(CREATE_PICTURES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }


    fun addUser(user: User): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, user.username)
        contentValues.put(KEY_PASS, user.password)


        val success = db.insert(TABLE_USERS, null, contentValues)

        db.close()
        return success
    }


    fun getUsernames(): MutableList<String> {

        val usernames = mutableListOf<String>()

        val selectQuery = "SELECT $KEY_NAME  FROM $TABLE_USERS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return mutableListOf()
        }

        var name: String

        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0)

                usernames.add(name)

            } while (cursor.moveToNext())
        }
        return usernames
    }

    fun getUsers(): MutableList<User> {

        val usernames = mutableListOf<User>()

        val selectQuery = "SELECT * FROM $TABLE_USERS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return mutableListOf()
        }

        var name: String
        var pass: String

        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0)
                pass = cursor.getString(1)

                val user = User(name, pass)
                usernames.add(user)

            } while (cursor.moveToNext())
        }
        return usernames
    }

    fun getUser(username: String, password: String): User?
    {

        val selectQuery = "SELECT * FROM $TABLE_USERS WHERE $KEY_NAME = '$username' AND $KEY_PASS = '$password'"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        var name: String
        var pass: String

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return null
        }

        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0)
                pass = cursor.getString(1)

                return User(name, pass)

            } while (cursor.moveToNext())
        }
        return null
    }
}