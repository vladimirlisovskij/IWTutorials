package com.example.task6

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClick {
    companion object {
        const val COLUMN_COUNT: Int = 2
        const val DB_VERSION: Int = 1
        const val FORM_KEY: String = "formKey"
        const val DB_NAME: String = "task6DataBase"
        const val DB_TABLE: String = "myTable"
        const val COL_NAME: String = "name"
        const val COL_EMAIL: String = "email"
        const val COL_PHONE: String = "phone"
        const val COL_ID: String = "id"
        const val COL_IMAGE: String = "image"
        const val LOG_TAG: String = "myLog"
    }

    private var mainRecycler: RecyclerView? = null
    private var adapter: RecyclerAdapter? = null
    private var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LOG_TAG, "читаем данные")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRecycler = findViewById(R.id.mainRecycler)
        initAdapter()
        dbHelper = DBHelper(this)
        try {
            val db: SQLiteDatabase = dbHelper!!.writableDatabase
            val c: Cursor = db.query(DB_TABLE, null, null, null, null, null, null, null)
            val res: ArrayList<Container> = arrayListOf();
            if (c.moveToFirst()) {
                do {
                    res.add(
                        Container(
                            c.getString(c.getColumnIndex(COL_IMAGE)),
                            c.getString(c.getColumnIndex(COL_NAME)),
                            c.getString(c.getColumnIndex(COL_PHONE)),
                            c.getString(c.getColumnIndex(COL_EMAIL)),
                            c.getString(c.getColumnIndex(COL_ID))
                        )
                    )
                } while (c.moveToNext())
            }
            adapter!!.containerList = res
            c.close()
        } catch (e: Exception) {
            Log.d(LOG_TAG, "ошибка " + e.message)
        } finally {
            dbHelper!!.close()
        }
        Log.d(LOG_TAG, "чтение.....ok\n")
    }

    private fun initAdapter() {
        adapter = RecyclerAdapter()
        adapter!!.onItemClick = this
        mainRecycler!!.layoutManager = GridLayoutManager(this, COLUMN_COUNT)
        mainRecycler!!.adapter = adapter
    }

    override fun itemClick(dataFormContainer: Container?, index: Int) {
        val manager = supportFragmentManager
        val myDialogFragment = Dialog()
        myDialogFragment.imgHref = dataFormContainer!!.avatarHref
        myDialogFragment.showListener = View.OnClickListener { _ ->
            Log.d(LOG_TAG, "показываем профиль")
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(FORM_KEY, dataFormContainer)
            startActivity(intent)
            myDialogFragment.dismiss()
            Log.d(LOG_TAG, "профиль.....ok\n")
        }
        myDialogFragment.deleteListener = View.OnClickListener { _ ->
            Log.d(LOG_TAG, "удаление строки")
            adapter!!.deleteItem(index)
            try {
                val db: SQLiteDatabase = dbHelper!!.writableDatabase;
                db.delete(DB_TABLE, "id = ?", arrayOf(dataFormContainer.id))
            } catch (e: Exception) {
                Log.d(LOG_TAG, "ошибка " + e.message)
            } finally {
                dbHelper!!.close();
                myDialogFragment.dismiss()
            }
            Log.d(LOG_TAG, "удаление.....ok\n")
        }
        myDialogFragment.show(manager, "myDialog")
    }

    inner class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
        override fun onCreate(db: SQLiteDatabase?) {
            Log.d(LOG_TAG, "создание базы")
            db!!.execSQL(
                "create table " + DB_TABLE  + " (" +
                        COL_ID + " integer primary key autoincrement," +
                        COL_NAME + " text," +
                        COL_EMAIL + " text," +
                        COL_PHONE + " text," +
                        COL_IMAGE + " text" +
                        ");"
            )
            Log.d(LOG_TAG, "создание.....ok\n")
            Log.d(LOG_TAG, "симулируем данные")
            val imgs: List<String> = listOf(
                "https://i.ibb.co/HTj14wR/cat1.jpg",
                "",
                "https://i.ibb.co/CtgyPtR/cat2.jpg",
                "https://i.ibb.co/D9dZTx4/cat3.jpg"
            )
            val name: List<String> = listOf(
                "Володя",
                "name",
                "вОлОдЯ",
                "Volodya"
            )
            val phone: List<String> = listOf(
                "1234567890",
                "phone",
                "9876543212",
                "987654345"
            )
            val email: List<String> = listOf(
                "володя@мир.ру",
                "email",
                "вОлОдЯ@мИр.Ру",
                "volodya@world.com"
            )
            val cv: ContentValues = ContentValues()
            for (i in 0..3) {
                for (j in 0..3) {
                    cv.clear()
                    cv.put(COL_IMAGE, imgs[j])
                    cv.put(COL_NAME, name[j])
                    cv.put(COL_PHONE, phone[j])
                    cv.put(COL_EMAIL, email[j])
                    db.insert(DB_TABLE, null, cv)
                }
            }
            Log.d(LOG_TAG, "симулирование.....ok\n")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        }

    }
}