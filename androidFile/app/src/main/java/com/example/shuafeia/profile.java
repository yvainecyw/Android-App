package com.example.shuafeia;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.Room1.Event;
import com.example.Room1.EventViewModel;
import java.util.List;

public class profile extends Fragment {

    private TextView Task_textView,Challange_textView,Reminded_textView;
    private EventViewModel mEventViewModel;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_profile, container, false);

        Task_textView = RootView.findViewById(R.id.friend_task_done_count_textview);
        Challange_textView = RootView.findViewById(R.id.friend_challange_complete_count_textview);
        Reminded_textView = RootView.findViewById(R.id.friend_reminded_count_textview);

        recyclerView = RootView.findViewById(R.id.profile_recyclerView);
        final profileAdapter adapter = new profileAdapter(this.getContext());
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.custom_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mEventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        mEventViewModel.getAllEvent().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable final List<Event> events) {
                // Update the cached copy of the words in the adapter.
                int i;
                int Task_Number=0,Task_Finish=0,Challange_Number=0,Challange_Finish=0,Reminded_Number=0,Remind_Finish=0;
                for (i = 0; i < events.size(); i++) {
                    Log.d("Profile", "i: " + i);
                    if (events.get(i).getType().equals("Task") && events.get(i).isFinish()) {
                        Task_Number++;
                        Task_Finish++;
                    } else if (events.get(i).getType().equals("Task")) {
                        Task_Number++;
                    }
                    if (events.get(i).getType().equals("Challange") && events.get(i).isFinish()) {
                        Challange_Number++;
                        Challange_Finish++;
                    } else if (events.get(i).getType().equals("Challenge")) {
                        Challange_Number++;
                    }
                    if (events.get(i).isRemind() && events.get(i).isRemind_friend()) {
                        Reminded_Number++;
                        Remind_Finish++;
                    } else if (events.get(i).isRemind()) {
                        Reminded_Number++;
                    }
                }
                Task_textView.setText(Integer.toString(Task_Finish) + "/" + Integer.toString(Task_Number));
                Challange_textView.setText(Integer.toString(Challange_Finish) + "/" + Integer.toString(Challange_Number));
                Reminded_textView.setText(Integer.toString(Remind_Finish) + "/" + Integer.toString(Reminded_Number));
                adapter.setEvent(events);
                Log.d("Profile","Task Number:"+Task_Finish + "/"+Task_Number + " Challange:"+Challange_Finish+"/"+Challange_Number+" Remind:"+Remind_Finish+"/"+Reminded_Number);
            }
        });

        recyclerView.setAdapter(adapter);

        return RootView;
    }
}
