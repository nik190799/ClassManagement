package com.creedeon.creed003

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_uploader.*
import java.text.SimpleDateFormat
import java.util.*

// Create a storage reference from our app



class AdminTest : androidx.fragment.app.Fragment() {

    val database = FirebaseDatabase.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_uploader,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show_choser.setOnClickListener {
            showChooser()
        }

    }
    private val requestCode=6384

    private fun showChooser(){
        val intent: Intent = Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent,"Select a file"),requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var selectedFIle :Uri? = null
        if (requestCode==requestCode&&resultCode== Activity.RESULT_OK){
            selectedFIle = data?.data
        }
        bt.setOnClickListener {
            storeToDataBase(selectedFIle)
        }
    }

    private fun storeToDataBase(selectedPath:Uri?){
        if (selectedPath==null)return
        val filename = UUID.randomUUID().toString()
        val ref= FirebaseStorage.getInstance().getReference("/Images/$filename")
        if(uploader_name.text.toString()== ""){
            uploader_name.error = "Enter the file name"
        }
        else {
            val uploadTask = ref.putFile(selectedPath)
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task ->
                val myRef = database.getReference("Circular")
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val postId = myRef.push().key
                    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                    val file = FileHolderClass(uploader_name.text.toString(),downloadUri.toString(),date,postId.toString())
                    myRef.child(postId.toString()).setValue(file)
                } else {
                    // Handle failures
                    // ...
                }
            }

        }
    }



}
