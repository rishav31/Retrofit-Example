package com.rishav.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rishav.retrofitexample.adapters.MyAdapter;
import com.rishav.retrofitexample.model.SpiritualTeacher;

public class NewActivity extends AppCompatActivity {

    StringBuilder sb = null;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        adapter = new MyAdapter(this, getTeachers());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb = new StringBuilder();
                int i = 0;
                do {
                    SpiritualTeacher spiritualTeacher = adapter.checkedTeachers.get(i);
                    sb.append(spiritualTeacher.getName());
                    if (i != adapter.checkedTeachers.size() - 1) {
                        sb.append("n");
                    }
                    i++;

                } while (i < adapter.checkedTeachers.size());

                if (adapter.checkedTeachers.size() > 0) {
                    Toast.makeText(NewActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewActivity.this, "Please Check An Item First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //RECYCLER
        RecyclerView rv = (RecyclerView) findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //SET ADAPTER
        rv.setAdapter(adapter);

    }

    private SpiritualTeacher[] getTeachers() {
        SpiritualTeacher[] spiritualTeachers = {
                new SpiritualTeacher("Rumi", "Out beyond ideas of wrongdoing and rightdoing there is a field.I'll meet you there.", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Anthony De Mello", "Don't Carry Over Experiences from the past", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Eckhart Tolle", "Walk as if you are kissing the Earth with your feet.", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Meister Eckhart", "Man suffers only because he takes seriously what the gods made for fun.", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Mooji", "I have lived with several Zen masters -- all of them cats.", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Confucius", "I'm simply saying that there is a way to be sane. I'm saying that you ", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Francis Lucille", "The way out is through the door. Why is it that no one will use this method?", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Thich Nhat Hanh", "t is the power of the mind to be unconquerable.", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Dalai Lama", "It's like you took a bottle of ink and you threw it at a wall. Smash! ", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Jiddu Krishnamurti", "A student, filled with emotion and crying, implored, 'Why is there so much suffering?", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Osho", "Only the hand that erases can write the true thing.", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Sedata", "Many have died; you also will die. The drum of death is being beaten.", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Allan Watts", "Where there are humans, You'll find flies,And Buddhas.", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Leo Gura", "Silence is the language of Om. We need silence to be able to reach our Self.", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Rupert Spira", "One day in my shoes and a day for me in your shoes, the beauty of travel lies ", R.drawable.ic_launcher_foreground),
                new SpiritualTeacher("Sadhguru", "Like vanishing dew,a passing apparition or the sudden flashnof lightning", R.drawable.ic_launcher_foreground)
        };

        return spiritualTeachers;
    }

}