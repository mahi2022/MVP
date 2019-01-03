package ampm9596.mvp.model

import ampm9596.mvp.beans.DataClass
import ampm9596.mvp.presenter.PresenterAPI
import ampm9596.mvp.view.MainActivity
import ampm9596.mvp.view.ViewAPI
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.view.View

class DataClassModel:PresenterAPI{
    var dBase:SQLiteDatabase?=null
    var mActivity:MainActivity?=null

    constructor(view_api: ViewAPI){

        mActivity=view_api  as MainActivity

        dBase=mActivity!!.openOrCreateDatabase("RoomExpDB",Context.MODE_PRIVATE,null)

        dBase!!.execSQL("create table if not exists Roomexpense(_id integer primary key autoincrement, date varchar (20),money integer ,name integer,description varchar(500),type varchar(50))")

    }

    override fun save(bean: DataClass) {
        var cv=ContentValues()
        cv.put("date",bean.date)
        cv.put("money",bean.money)
        cv.put("name",bean.name)
        cv.put("description",bean.description)
        cv.put("type",bean.type)

        var status=dBase!!.insert("Roomexpense",null,cv)

        if (status!=1L){
            mActivity!!.saveOutput("Record is Inserted")
        }else{
            mActivity!!.saveOutput("Record Insertion is Failed...")
        }
    }

    override fun read() {

var c=dBase!!.query("Roomexpense",null,null,null,null,null,null)
  mActivity!!.readOutput(c)
    }
}