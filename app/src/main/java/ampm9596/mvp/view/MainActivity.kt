package ampm9596.mvp.view

import ampm9596.mvp.R
import ampm9596.mvp.beans.DataClass
import ampm9596.mvp.model.DataClassModel
import ampm9596.mvp.presenter.PresenterAPI
import android.app.DatePickerDialog
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),ViewAPI {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datepicker.setOnClickListener {

            var cal = Calendar.getInstance()
            var listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    sd.setText(dayOfMonth.toString() + "/" + (month + 1).toString() + "/" + (year).toString())
                }
            }
            var dpd = DatePickerDialog(
                this@MainActivity, listener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

var api:PresenterAPI=DataClassModel(this)

save.setOnClickListener {
    var bean=DataClass(sd.text.toString(),money.text.toString(),name.text.toString(),
        description.text.toString(),type.selectedItem.toString())
    api.save(bean)
}

        read.setOnClickListener {
            api.read()
        }
    } //on create

        override fun saveOutput(msg: String) {
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
        }
var from= arrayOf("date","money","name","description","type")
    var to= intArrayOf(R.id.date,R.id.money,R.id.name,R.id.description,R.id.type)
        override fun readOutput(c: Cursor) {
var cadapter=SimpleCursorAdapter(this@MainActivity,R.layout.indiview,c,from,to,0)
            iview.adapter=cadapter
            var inc_sum=0
            var exp_sum=0
            while(c.moveToNext()) {
                if (c.getString(5).equals("income")) {
                    inc_sum = inc_sum + c.getInt(2)
                } else {
                    exp_sum = exp_sum + c.getInt(2)
                }
            }
            inc.setText("Income sum : $inc_sum");
            exp.setText("Expense Sum : $exp_sum")

        }
    }
