package com.example.final_project.ui.battleship

import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.R
import com.example.final_project.ui.battleship.view.BattleshipViewModel
import com.example.final_project.ui.battleship.view.GridView.Companion.Blue
import com.example.final_project.ui.battleship.view.GridView.Companion.Green
import com.example.final_project.ui.battleship.view.GridView.Companion.Red
import kotlin.random.Random


class BattleshipFragment : Fragment() {

    private lateinit var battleshipViewModel: BattleshipViewModel

    companion object
    {
        var path = Path()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        battleshipViewModel =
                ViewModelProvider(this).get(BattleshipViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val btn = root.findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            Toast.makeText(context, "Color", Toast.LENGTH_SHORT).show()
            val randomValues = List(3) { Random.nextInt(0, 100) }

            Red = randomValues[0]
            Green = randomValues[1]
            Blue = randomValues[2]

            path = Path()
        }

        return root
    }


}