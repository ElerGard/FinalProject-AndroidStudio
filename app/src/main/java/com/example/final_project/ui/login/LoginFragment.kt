package com.example.final_project.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.adapter.RecyclerAdapter
import com.example.final_project.data.Database
import com.example.final_project.data.User
import com.example.final_project.ui.home.PicturesFragment

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        val db = context?.let { Database(it) }
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(this, LoginFactory(db)).get(LoginViewModel::class.java)

        val username = view.findViewById<TextView>(R.id.username)
        val password =  view.findViewById<TextView>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login)
        val regButton = view.findViewById<Button>(R.id.registration)
        var errorText: TextView = view.findViewById<TextView>(R.id.errorText)


        loginViewModel.dataValid.observe(viewLifecycleOwner, Observer {
            if (it)
            {
                errorText.text = ""
                loginButton.isEnabled = true
                regButton.isEnabled = true
            }
            else
            {

                if (loginViewModel.error != null )
                {
                    errorText.text = loginViewModel.error!!.value
                }
                return@Observer
            }

        })

        loginViewModel.usernameToLogin.observe(viewLifecycleOwner, Observer {
            if (it != null )
            {
                val intent = Intent(context, PicturesFragment::class.java)
                this.startActivity(intent)
            }
            else
            {
                return@Observer
            }

        })

        regButton.setOnClickListener {

            if (db != null) {
                if (db.getUser(username.text.toString(), password.text.toString()) == null)
                    Toast.makeText(view.context, "No", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(view.context, "Yes", Toast.LENGTH_LONG).show()
            }
        }

        username.doAfterTextChanged {
            if (username.isFocused && password.didTouchFocusSelect())
                loginViewModel.isDataValid(username.text.toString(), password.text.toString())
        }

        password.doAfterTextChanged {
            if (password.isFocused && username.didTouchFocusSelect())
                loginViewModel.isDataValid(username.text.toString(), password.text.toString())
        }

        loginButton.setOnClickListener {
            loginViewModel.login(username.text.toString(), password.text.toString())
        }
    }



    private fun addRecord(view: View) {
        var username = view.findViewById<TextView>(R.id.username)
        var password =  view.findViewById<TextView>(R.id.password)
        val databaseHandler: Database = Database(view.context)
        if (!username.text.isEmpty() && !password.text.isEmpty()) {
            val status =
                    databaseHandler.addUser(User(username.text.toString(), password.text.toString()))
            if (status > -1) {
                Toast.makeText(view.context, "Record saved", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(
                    view.context,
                    "Name or Pass cannot be blank",
                    Toast.LENGTH_LONG
            ).show()
        }
    }


    private fun setupListofDataIntoRecyclerView(view: View) {
        var list = view.findViewById<RecyclerView>(R.id.rvResult)

        val databaseHandler: Database = Database(view.context)
        val usernames: MutableList<String> = databaseHandler.getUsernames()

        if (usernames.size > 0) {


            list.layoutManager = LinearLayoutManager(view.context)
            val itemAdapter = RecyclerAdapter(view.context, usernames)
            list.adapter = itemAdapter
        } else {
            list.visibility = View.GONE
        }
    }
}