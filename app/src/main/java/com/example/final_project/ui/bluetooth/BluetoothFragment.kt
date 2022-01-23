package com.example.final_project.ui.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.adapter.RecyclerAdapter

class BluetoothFragment : Fragment() {

    private lateinit var bluetoothViewModel: BluetoothViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        bluetoothViewModel =
                ViewModelProvider(this).get(BluetoothViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bluetooth, container, false)

        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        val listDevices = root.findViewById<Button>(R.id.listDevices)
        val listView = root.findViewById<ListView>(R.id.listview)

        //var btArray: Array<BluetoothDevice?>

        val list = root.findViewById<RecyclerView>(R.id.rvResult)


        listDevices.setOnClickListener {
            val bt: Set<BluetoothDevice> = bluetoothAdapter.bondedDevices
            val strings = ArrayList<String>()
            //btArray = arrayOfNulls<BluetoothDevice>(bt.size)
            if (bt.isNotEmpty()) {
                for (device in bt) {

                    strings.add(device.name)
                }
                list.layoutManager = LinearLayoutManager(context)

                val itemAdapter = context?.let { it1 -> RecyclerAdapter(it1, strings) }

                list.adapter = itemAdapter
            }
        }

        return root
    }
}