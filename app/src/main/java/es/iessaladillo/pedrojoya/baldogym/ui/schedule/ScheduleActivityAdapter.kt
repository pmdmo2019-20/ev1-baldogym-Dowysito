package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.schedule_activity_item.*
import kotlinx.android.synthetic.main.schedule_activity_item.view.*

class ScheduleActivityAdapter : RecyclerView.Adapter<ScheduleActivityAdapter.ViewHolder>() {

    private var data: List<TrainingSession> = LocalRepository.querydayList(getCurrentWeekDay())
    private  var onItemClickListener: ((Int)->Unit)? = null
    private  var onButtonClickListeter: ((Int)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.schedule_activity_item, parent, false)
        return ViewHolder(itemView, onItemClickListener, onButtonClickListeter)
    }

    fun setOnItemClickListener(listener: (Int) -> Unit){
        onItemClickListener = listener
    }

    fun setOnButtonClickListener(listener: (Int) -> Unit){
        onButtonClickListeter = listener
    }

    fun submitList(tasks: List<TrainingSession>) {
        data=tasks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun getItem(position: Int): TrainingSession {
        return data[position]
    }


    inner class ViewHolder(override val containerView: View, onItemClickListener: ((Int)->Unit)?, onButtonClickListener: ((Int)->Unit)?) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {


        init {
            containerView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
            btn_join.setOnClickListener {
                onButtonClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(session: TrainingSession) {
            session.run {
                containerView.lbl_sessionName.text = name
                containerView.lbl_sessionTrainer.text = trainer
                containerView.lbl_session_hour.text = time
                containerView.img_Session.setImageResource(photoResId)
                containerView.lbl_sessionPlace.text = room

                if (!userJoined){
                    containerView.lblParticipants.text = lblParticipants.context.resources.getQuantityString(R.plurals.schedule_item_participants,participants,participants)
                    btn_join.setTextColor(btn_join.context.resources.getColor(R.color.black))
                    btn_join.background= btn_join.context.getDrawable(R.drawable.schedule_btn_join_background)
                    btn_join.text=btn_join.context.getString(R.string.schedule_item_join)
                }
                else{
                    btn_join.text=btn_join.context.getString(R.string.schedule_item_quit)
                    btn_join.background= btn_join.context.getDrawable(R.drawable.schedule_btn_quit_background)
                    btn_join.setTextColor(btn_join.context.resources.getColor(R.color.white))
                    containerView.lblParticipants.text = lblParticipants.context.resources.getQuantityString(R.plurals.schedule_item_participants,participants+1, participants+1)
                }
            }
        }
    }
}
