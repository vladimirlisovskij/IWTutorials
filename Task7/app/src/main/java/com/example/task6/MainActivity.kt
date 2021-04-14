package com.example.task6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    companion object {
        const val COLUMN_COUNT: Int = 2
        const val FORM_KEY: String = "formKey"
        const val LOG_TAG: String = "myLog"
    }

    private lateinit var mainRecycler: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var employeeDAO: EmployeeDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LOG_TAG, "читаем данные")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRecycler = findViewById(R.id.mainRecycler)
        initAdapter()
        employeeDAO = AbstractDB.getDB(this).employeeDAO()
        val employeeList: List<Employee> = employeeDAO.getAll();
        val res: ArrayList<Container> = arrayListOf();
        if (employeeList.isEmpty()) {
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
            for (i in 0..3) {
                for (j in 0..3) {
                    val employee: Employee = Employee((i*3 + j + 1).toLong(), imgs[j], name[j], phone[j], email[j]);
                    res.add(Container(imgs[j], name[j], phone[j], email[j], (i*3 + j).toString()))
                    employeeDAO.insert(employee);
                }
            }
            Log.d(LOG_TAG, "симулирование.....ok\n")
        } else {
            employeeList.forEach {
                res.add(
                        Container(
                                it.image,
                                it.name,
                                it.phone,
                                it.email,
                                it.id.toString()
                        )
                )
            }
        }
        adapter.containerList = res
        Log.d(LOG_TAG, "чтение.....ok\n")
    }

    private fun initAdapter() {
        adapter = RecyclerAdapter()
        adapter.onItemClick = { dataFormContainer: Container, index: Int ->
            val manager = supportFragmentManager
            val myDialogFragment = Dialog()
            myDialogFragment.imgHref = dataFormContainer.imageLink
            myDialogFragment.showListener = { _ ->
                Log.d(LOG_TAG, "показываем профиль")
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra(FORM_KEY, dataFormContainer)
                startActivity(intent)
                myDialogFragment.dismiss()
                Log.d(LOG_TAG, "профиль.....ok\n")
            }
            myDialogFragment.deleteListener = { _ ->
                Log.d(LOG_TAG, "удаление строки")
                adapter.deleteItem(index)
                employeeDAO.deleteById(Integer.parseInt(dataFormContainer.id));
                myDialogFragment.dismiss()
                Log.d(LOG_TAG, "удаление.....ok\n")
            }
            myDialogFragment.show(manager, "myDialog")
        }
        mainRecycler.layoutManager = GridLayoutManager(this, COLUMN_COUNT)
        mainRecycler.adapter = adapter
    }
}