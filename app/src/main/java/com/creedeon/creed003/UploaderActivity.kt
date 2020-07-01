package com.creedeon.creed003

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_uploader.*
import java.text.SimpleDateFormat
import java.util.*

class UploaderActivity : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRefCircular = database.getReference("Circular")
    val myRef= database.getReference("Teacher Circular")
    var selectedFIle:Uri? = null


    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploader)

        progressDialog = ProgressDialog(this@UploaderActivity)

        show_choser.setOnClickListener {
            showChoser()
        }

        bt.setOnClickListener {

            if(uploader_name.text.toString()==""){
                uploader_name.error = "Enter the Circular"
            }
            else {
                progressDialog.setTitle("Uploading...")
                progressDialog.show()
                storeToDataBase(selectedFIle)
            }
        }
        teacher_admin_circular_upload_bt.setOnClickListener {

            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            if(teacher_admin_circular_edit_text.text.toString()==""){
                progressDialog.dismiss()
                teacher_admin_circular_edit_text.error = "Enter the circular"

            }
            else{

                progressDialog.dismiss()

                var postId =myRef.push().key
                var date = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date())
                var circular = TeacherAdminCircularItem(teacher_admin_circular_edit_text.text.toString(),date,postId.toString())

                myRef.child(postId.toString()).setValue(circular)

                    teacher_admin_circular_edit_text.setText("")
                Toast.makeText(this@UploaderActivity,"Circular added successfully", Toast.LENGTH_SHORT).show()
            }

        }
    }
    val REQUEST_CODE=6384
    private fun showChoser(){
        var intent: Intent = Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent,"Select a file"),REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var selectedFIle:Uri? = null
        if (requestCode==REQUEST_CODE&&resultCode== Activity.RESULT_OK){
            selectedFIle = data?.data

        }
        bt.setOnClickListener {

            if(uploader_name.text.toString()==""){
                uploader_name.error = "Enter the Circular"
            }
            else {
                storeToDataBase(selectedFIle)
            }
        }

    }
    private fun storeToDataBase(selectedPath: Uri?) {



        if (selectedPath==null) {

            myRefCircular.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    //To change body of created functions use File | Settings | File Templates.
                    var postId = myRefCircular.push().key
                    var date = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date())
                    var file = FileHolderClass(uploader_name.text.toString(), "", date,postId.toString())
                    myRefCircular.child(postId.toString()).setValue(file)
                    progressDialog.dismiss()
                    uploader_name.setText("")
                    Toast.makeText(this@UploaderActivity, "Successfully uploaded", Toast.LENGTH_SHORT).show()
                }

            })

        } else {
            progressDialog.setTitle("Uploading...")
            progressDialog.show()
            var filename = UUID.randomUUID().toString()
            var ref = FirebaseStorage.getInstance().getReference("/Images/$filename")
            if (uploader_name.text.toString()=="") {
                progressDialog.dismiss()
                uploader_name.error = "Enter the file name"
            } else {

                var uploadTask = ref.putFile(selectedPath)
                uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                    if (!task.isSuccessful) {

                        task.exception?.let {
                            throw it
                        }
                    }
                    return@Continuation ref.downloadUrl
                }).addOnCompleteListener { task ->

                    if (task.isSuccessful) {


                        var downloadUri = task.result
                        var postId = myRefCircular.push().key
                        var date = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date())
                        var file = FileHolderClass(uploader_name.text.toString(), downloadUri.toString(), date,postId.toString())
                        myRefCircular.child(postId.toString()).setValue(file)
                        progressDialog.dismiss()
                        uploader_name.setText("")
                        Toast.makeText(this, "Circular added successfully", Toast.LENGTH_SHORT).show()

                    } else {
                        progressDialog.dismiss()

                        // Handle failures
                        // ...
                    }


                }

            }
        }
    }

}
