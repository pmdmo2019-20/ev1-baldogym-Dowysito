package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import kotlinx.android.synthetic.main.schedule_activity.*
import kotlinx.android.synthetic.main.schedule_activity_item.*

class ScheduleActivity : AppCompatActivity() {

    // TODO

    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().also {
        it.setOnItemClickListener {position ->
            val trainingSession:TrainingSession = it.getItem(position)
        }
        it.setOnButtonClickListener {position ->
            val trainingSession:TrainingSession = it.getItem(position)
            viewModel.joinSession(trainingSession)
        }
    }
    private val localRepository = LocalRepository
    private val viewModel: ScheduleActivityViewModel by viewModels { ScheduleActivityViewModelFactory(localRepository,application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupRecyclerView()
        setUpViews()
        observe()
    }

    private fun observe() {
        viewModel.currentDayOptionSelected.observe(this){currentday(it)}
        viewModel.sessions.observe(this){showTasks(it)}
    }

    private fun setupRecyclerView() {
        lstSchedule.run{
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = listAdapter
        }
    }

    private fun setUpViews() {
        setUpdaybar()
    }


    private fun setUpdaybar() {
        lblMonday.setOnClickListener { viewModel.changeDay(WeekDay.MONDAY) }
        lblTuesday.setOnClickListener { viewModel.changeDay(WeekDay.TUESDAY) }
        lblWednesday.setOnClickListener { viewModel.changeDay(WeekDay.WEDNESDAY) }
        lblThursday.setOnClickListener { viewModel.changeDay(WeekDay.THURSDAY) }
        lblFriday.setOnClickListener { viewModel.changeDay(WeekDay.FRIDAY) }
        lblSaturday.setOnClickListener { viewModel.changeDay(WeekDay.SATURDAY) }
        lblSunday.setOnClickListener { viewModel.changeDay(WeekDay.SUNDAY) }
    }

    private fun currentday(day: WeekDay) {
        when(day){
            WeekDay.MONDAY -> {
                lblCurrentDay.text=getString(R.string.schedule_monday)
                resetWeekbar()
                lblMonday.setTextColor(resources.getColor(R.color.white))
            }
            WeekDay.TUESDAY -> {
                lblCurrentDay.text=getString(R.string.schedule_tuesday)
                resetWeekbar()
                lblTuesday.setTextColor(resources.getColor(R.color.white))
            }
            WeekDay.WEDNESDAY -> {
                lblCurrentDay.text=getString(R.string.schedule_wednesday)
                resetWeekbar()
                lblWednesday.setTextColor(resources.getColor(R.color.white))
            }
            WeekDay.THURSDAY -> {
                lblCurrentDay.text=getString(R.string.schedule_thursday)
                resetWeekbar()
                lblThursday.setTextColor(resources.getColor(R.color.white))
            }
            WeekDay.FRIDAY -> {
                lblCurrentDay.text=getString(R.string.schedule_friday)
                resetWeekbar()
                lblFriday.setTextColor(resources.getColor(R.color.white))
            }
            WeekDay.SATURDAY -> {
                lblCurrentDay.text=getString(R.string.schedule_saturday)
                resetWeekbar()
                lblSaturday.setTextColor(resources.getColor(R.color.white))
            }
            WeekDay.SUNDAY -> {
                lblCurrentDay.text=getString(R.string.schedule_sunday)
                resetWeekbar()
                lblSunday.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

    private fun resetWeekbar() {
        lblMonday.setTextColor(resources.getColor(R.color.white_semi))
        lblTuesday.setTextColor(resources.getColor(R.color.white_semi))
        lblWednesday.setTextColor(resources.getColor(R.color.white_semi))
        lblThursday.setTextColor(resources.getColor(R.color.white_semi))
        lblFriday.setTextColor(resources.getColor(R.color.white_semi))
        lblSaturday.setTextColor(resources.getColor(R.color.white_semi))
        lblSunday.setTextColor(resources.getColor(R.color.white_semi))
    }

    private fun showTasks(tasks: List<TrainingSession>) {
        lstSchedule.post {
            listAdapter.submitList(tasks)
            listAdapter.notifyDataSetChanged()
        }
    }

}
