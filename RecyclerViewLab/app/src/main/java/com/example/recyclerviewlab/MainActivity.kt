package com.example.recyclerviewlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var contacts: ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup RecyclerView in the activity layout
        val rvContacts = findViewById<RecyclerView>(R.id.rvContacts)

        // Initialize contacts
        contacts = Contact.createContactsList(20)

        // Create adapter passing in the contact data
        val adapter = ContactsAdapter(contacts)

        // Attach the adapter to the RecyclerView
        rvContacts.adapter = adapter

        // Set layout manager to position the items
        rvContacts.layoutManager = LinearLayoutManager(this)
    }
}
