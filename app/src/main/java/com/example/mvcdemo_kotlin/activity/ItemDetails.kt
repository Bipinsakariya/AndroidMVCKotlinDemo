package com.example.mvcdemo_kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvcdemo_kotlin.Adapter.ItemAdapter
import com.example.mvcdemo_kotlin.R
import com.example.mvcdemo_kotlin.model.Item
import com.example.mvcdemo_kotlin.model.Response
import com.example.mvcdemo_kotlin.service.ServiceConstants
import com.example.mvcdemo_kotlin.service.Services
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_item_details.*

class ItemDetails : AppCompatActivity(), Services.ServerOperationCompletion {
    private var itemAdapter: ItemAdapter? = null
    val itemlist: ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        init()
        fetchitemsdata()
    }

    private fun fetchitemsdata() {

        val headerMap = HashMap<String, String>()
        val params = HashMap<String, String>()
        Services().getInstance()
            .calloperation(this, headerMap, params, this, ServiceConstants.ItemEndPoint)

    }

    private fun init() {

        recycler_items.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        itemAdapter = ItemAdapter(this, itemlist)
        recycler_items.adapter = itemAdapter


    }

    override fun onResponseReceived(responseData: JsonObject?, callType: String) {
        val gson = Gson()
        val ItemListResponse =
            gson.fromJson<Response>(responseData, Response::class.java)
        Log.println(
            Log.ASSERT,
            "ItemsListResponce",
            ItemListResponse.toString() + "CallType_________" + callType
        )

        if(callType.equals(ServiceConstants.ItemEndPoint)){

            if(ItemListResponse.items!=null && ItemListResponse.items.size!=0){
                itemlist.addAll(ItemListResponse.items)
                itemAdapter!!.notifyDataSetChanged()
            }

        }


    }

    override fun onError(errorMessage: String, callType: String) {
        Log.println(
            Log.ASSERT,
            "error_message______ ",
            errorMessage + "CallType_________" + callType
        )

    }
}