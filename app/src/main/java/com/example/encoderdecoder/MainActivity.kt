package com.example.encoderdecoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var Encode: EditText
    private lateinit var Decode: EditText
    private lateinit var EnButton: Button
    private lateinit var DeButton: Button

    private lateinit var RV: RecyclerView
    private lateinit var RVAdapter: RcyclerViewAdapter

    private lateinit var Phrasez: ArrayList<Phrase>

    private val alphabet = "abcdefghijklmnopqrstuvwxyz"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Phrasez = arrayListOf()

        Encode = findViewById(R.id.EncodeET)
        Decode = findViewById(R.id.DecodeET)

        EnButton = findViewById(R.id.EncodeBtn)
        EnButton.setOnClickListener {
            EncodPhrase() }
        DeButton = findViewById(R.id.DecodeBtn)
        EnButton.setOnClickListener {
            DecodePhrase() }

        RV = findViewById(R.id.RV)
        RVAdapter = RcyclerViewAdapter(Phrasez)
        RV.adapter = RVAdapter
        RV.layoutManager = LinearLayoutManager(this)



    }//end oncreate

    fun EncodPhrase(){
        var output = ""
        var pos = 0
        if(!Encode.text.isNullOrEmpty()){
            for(letter in Encode.text.toString()){
                if(alphabet.indexOf(letter) < 0){
                    if(alphabet.uppercase().indexOf(letter) < 0){
                        output += letter
                    }else{
                        pos = alphabet.uppercase().indexOf(letter) + 13
                        if(pos > 25){
                            pos -= 26
                        }
                        output += alphabet.uppercase()[pos]
                    }
                }else{
                    pos = alphabet.indexOf(letter) + 13
                    if(pos > 25){
                        pos -= 26
                    }
                    output += alphabet[pos]
                }
            }
            Phrasez.add(Phrase(Encode.text.toString(), true))
            Encode.text.clear()
        }else{
            Toast.makeText(this, "Phrase cannot be empty", Toast.LENGTH_LONG).show()
        }

        Phrasez.add(Phrase(output, false))
        RVAdapter.update()

    }// end encode

    fun DecodePhrase(){
        var OutPut = ""
        var Posetion = 0
        if(!Decode.text.isNullOrEmpty()){
            for(letter in Decode.text.toString()){
                if(alphabet.indexOf(letter) < 0){
                    if(alphabet.uppercase().indexOf(letter) < 0){
                        OutPut += letter
                    }else{
                        Posetion = alphabet.uppercase().indexOf(letter) - 13
                        if(Posetion < 0){
                            Posetion += 26
                        }
                        OutPut += alphabet.uppercase()[Posetion]
                    }
                }else{
                    Posetion = alphabet.indexOf(letter) - 13
                    if(Posetion < 0){
                        Posetion += 26
                    }
                    OutPut += alphabet[Posetion]
                }
            }
            Phrasez.add(Phrase(Decode.text.toString(), true))
            Decode.text.clear()
        }else{
            Toast.makeText(this, "Phrase cannot be empty", Toast.LENGTH_LONG).show()
        }

        Phrasez.add(Phrase(OutPut, false))
        RVAdapter.update()
    }// end decode


}//end class