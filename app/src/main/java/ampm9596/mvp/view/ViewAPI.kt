package ampm9596.mvp.view

import android.database.Cursor

interface ViewAPI {
    fun saveOutput(msg:String)
    fun readOutput(c:Cursor)
}