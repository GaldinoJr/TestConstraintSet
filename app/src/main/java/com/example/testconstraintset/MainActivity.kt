package com.example.testconstraintset

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.transition.Visibility
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mConstraintSet: ConstraintSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btChangeStatus.setOnClickListener(this)
        mConstraintSet = ConstraintSet()
        mConstraintSet.clone(clGokStatus)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            btChangeStatus.id->{
                changeGokuStatus(ivGoku.visibility == View.VISIBLE)
            }
        }
    }

    private fun changeGokuStatus(isHide: Boolean) {
        if(isHide){
            tvGokuStatus.setText(R.string.goku_is_not_here)
            mConstraintSet.setVisibility(ivGoku.id, ConstraintSet.GONE)
        }
        else{
            tvGokuStatus.setText(R.string.goku_is_here)
            mConstraintSet.setVisibility(ivGoku.id, ConstraintSet.VISIBLE)
        }

        applyAnimation()
    }

    private fun applyAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(clGokStatus)
        }
        mConstraintSet.applyTo(clGokStatus)
    }
}
