package com.example.final_project.ui.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
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
import com.example.final_project.ui.FragmentController


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    private var mListener: FragmentController? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        return root
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mListener = try {
            activity as FragmentController
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString()
                    + " must implement FragmentController")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
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
        var flag: Boolean = false


        loginViewModel.dataValid.observe(viewLifecycleOwner, Observer {
            if (it) {
                errorText.text = ""
                loginButton.isEnabled = true
                regButton.isEnabled = true
            } else {

                if (loginViewModel.error != null) {
                    errorText.text = loginViewModel.error!!.value
                    loginButton.isEnabled = false
                    regButton.isEnabled = false
                }
                return@Observer
            }

        })

        loginViewModel.usernameToLogin.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                flag = true

            } else {
                return@Observer
            }

        })


        regButton.setOnClickListener {
            loginViewModel.login(username.text.toString(), password.text.toString())
            if (loginViewModel._usernameToLogin.value != null)
            {
                addRecord(view)
//                val intent = Intent(context, PicturesFragment::class.java)
//                this.startActivity(intent)
                username.visibility = View.GONE
                password.visibility = View.GONE
                loginButton.visibility = View.GONE
                errorText.setTextColor(Color.argb(255,63,137,188))
                errorText.setTextSize(TypedValue.COMPLEX_UNIT_SP,34F)
                errorText.text = "Hello ${username.text.toString()}. Choose action!"
                regButton.visibility = View.GONE
                mListener!!.changeFragment(1)
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
            if (loginViewModel._usernameToLogin.value != null)
            {
//                val intent = Intent(context, PicturesFragment::class.java)
//                this.startActivity(intent)
                //mListener!!.changeFragment(1)
                username.visibility = View.GONE
                password.visibility = View.GONE
                loginButton.visibility = View.GONE
                errorText.setTextColor(Color.argb(255,63,137,188))
                errorText.setTextSize(TypedValue.COMPLEX_UNIT_SP,34F)
                errorText.text = "Hello ${username.text.toString()}. Choose action!"
                regButton.visibility = View.GONE
                mListener!!.changeFragment(1)
            }
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
                Toast.makeText(view.context, "${username.text.toString()} login success", Toast.LENGTH_LONG).show()
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