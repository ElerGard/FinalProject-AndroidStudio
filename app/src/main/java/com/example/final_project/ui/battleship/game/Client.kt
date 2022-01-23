package com.example.final_project.ui.battleship.game

import android.annotation.SuppressLint
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.R
import com.example.final_project.databinding.FragmentDashboardBinding
import com.example.final_project.ui.battleship.view.BattleshipViewModel
import com.example.final_project.ui.battleship.view.GridView

class Client {

//    val host: Button? = null
//    val join: Button? = null
//    val btn: Button? = null
//    val nickname: TextView? = null
//    val yourIp: TextView? = null
//    var ipToJoin: TextView? = null
//    var connect: TextView? = null
//    val textView: TextView? = null



//        val join: Button = root.findViewById(R.id.button2)
        //val btn: Button = root.findViewById(R.id.button3)
//        val nickname: TextView = root.findViewById(R.id.nick)
//        val yourIp: TextView = root.findViewById(R.id.ip)
//        var ipToJoin: TextView = root.findViewById(R.id.ipToJoin)
//        var connect: TextView = root.findViewById(R.id.connect)
//        val textView: TextView = root.findViewById(R.id.tip)
//
//
//        val exec = Executors.newCachedThreadPool()
//        val selector = ActorSelectorManager(exec.asCoroutineDispatcher())
//        val tcpSocketBuilder = aSocket(selector).tcp()
//
//
//
//        battleshipViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        ipToJoin.visibility = View.GONE

//        join.setOnClickListener {
//            battleshipViewModel.but.observe(viewLifecycleOwner, Observer {
//                host.visibility = it
//            })
//            ipToJoin.visibility = View.VISIBLE
//
//            if (ipToJoin.text == null)
//            {
//
//            }
//            else
//            {
//                val server: Socket = Socket()
//
//            }
//        }

        //lateinit var mSocket: ServerSocket

//        host.setOnClickListener {
//            battleshipViewModel.but.observe(viewLifecycleOwner, Observer {
//                join.visibility = it
//
//            })
//            connect.setText("Waiting for opponent")
//            val context = requireContext().applicationContext
//            val wm = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
//            val ip = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
//            yourIp.setText("Your ip address for connection: $ip")
//
//            CoroutineScope(IO).launch {
//                server(ip, 9999)
//            }
//
//        }



//    private  suspend fun server(tp: String, port: Int) {
//        val s = ServerSocket(9999)
//        val server = Socket(tp, 9999)
//
//        var flag = true
//        while (flag)
//        {
//            textView?.setText("asd")
//            val reader = Scanner(server.getInputStream())
//            val writer = server.getOutputStream()
//            writer.write(1)
//            if (textView?.text == "asd")
//            {
//                flag = false
//            }
//        }
//
//    }

//    private  suspend fun client(tp: String, port: Int) {
//        val socket = Socket(tp, 9999)
//
//        if (socket.isConnected)
//        {
//            textView?.setText("Ugu")
////                        val reader = Scanner(socket.getInputStream())
////                        val writer = socket.getOutputStream()
////
////                        var input = reader.nextInt()
////
////                        if (input == 1)
////                        {
////                            textView.text = "YEFASFASsaFASF"
////                        }
//        }
//        else
//        {
//            textView?.setText("not Ugu")
//        }
//
//    }
}