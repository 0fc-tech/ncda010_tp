package com.example.enishop.dao

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.enishop.dao.memory.ArticleDaoMemoryImpl

abstract class DaoFactory {
    companion object{
        fun getDao(type: DaoType) = when(type){
            DaoType.MEMORY -> ArticleDaoMemoryImpl()
            DaoType.NETWORK -> ArticleDaoMemoryImpl()
        }
    }

}