package com.example.enishop.dao

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.enishop.dao.db.ShopDB
import com.example.enishop.dao.memory.ArticleDaoMemoryImpl
import javax.inject.Inject

class DaoFactory @Inject constructor(val dbInst : ShopDB) {
    fun getDao(type: DaoType) = when(type){
        DaoType.MEMORY -> ArticleDaoMemoryImpl()
        DaoType.NETWORK -> ArticleDaoMemoryImpl()
        DaoType.DATABASE -> dbInst.articleDao()
    }
}