package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import kotlinx.android.synthetic.main.training_session_activity.*

import java.lang.RuntimeException

class TrainingSessionActivity : AppCompatActivity() {

    // TODO

    private val viewModel: TrainingSessionViewModel by viewModels { TrainingSessionViewModelFactory(localRepository,application) }
    private val localRepository= LocalRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_session_activity)
        getIntentData()
        observe()
        setUpViews()
    }

    private fun observe() {
        viewModel.session.observe(this){updateButton(it)}
    }

    private fun setUpViews() {
        img_sessionDetail.setImageResource(viewModel.session.value!!.photoResId)
        lbl_detailName.text= viewModel.session.value!!.name
        lbl_detailTrainer.text = viewModel.session.value!!.trainer
        lbl_detailDay.text = viewModel.session.value!!.weekDay.name
        lbl_detailInfo.text = viewModel.session.value!!.description
        lbl_detailHour.text = viewModel.session.value!!.time
        lbl_detailPlace.text =viewModel.session.value!!.room
        btn_detailJoin.setOnClickListener {
            if (viewModel.session.value!!.userJoined){
                viewModel.quitSession()
            }
            else{
                viewModel.joinSession()
            }
        }
        updateButton(viewModel.session.value!!)
    }

    private fun updateButton(session:TrainingSession) {
        if (!session.userJoined) {
            lbl_detailParticipants.text =
                lbl_detailParticipants.context.resources.getQuantityString(
                    R.plurals.schedule_item_participants,
                    session.participants,
                    session.participants
                )
            btn_detailJoin.setTextColor(btn_detailJoin.context.resources.getColor(R.color.white))
            btn_detailJoin.background =
                btn_detailJoin.context.getDrawable(R.drawable.session_btn_join_background)
            btn_detailJoin.text = btn_detailJoin.context.getString(R.string.schedule_item_join)
        } else {
            lbl_detailParticipants.text =
                lbl_detailParticipants.context.resources.getQuantityString(
                    R.plurals.schedule_item_participants,
                    session.participants + 1,
                    session.participants + 1
                )
            btn_detailJoin.setTextColor(btn_detailJoin.context.resources.getColor(R.color.black))
            btn_detailJoin.background =
                btn_detailJoin.context.getDrawable(R.drawable.session_btn_quit_background)
            btn_detailJoin.text = btn_detailJoin.context.getString(R.string.schedule_item_quit)
        }
    }

    private fun getIntentData() {
        if (intent == null||!intent.hasExtra("EXTRA_ID")){
            throw RuntimeException("DetailActivity needs an Id set on extras")
        }
        viewModel.searchSession(intent.getLongExtra("EXTRA_ID",0))
    }

    companion object {
        const val  EXTRA_ID = "EXTRA_ID"

        fun newIntent(context: Context,id:Long)= Intent(context,TrainingSessionActivity::class.java).putExtra(
            EXTRA_ID,id)
    }
}
