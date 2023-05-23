package com.example.happypet;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.StringTokenizer;

public class FragmentHome extends Fragment {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    ImageButton home_user;
    TextView[] info_ino_textView = new TextView[4];
    TextView[] info_title_textView = new TextView[4];
    TextView[] info_id_textView = new TextView[4];
    ImageView[] home_img = new ImageView[4];
    TextView moreInfo;
    LinearLayout[] infoLayout = new LinearLayout[4];
    RelativeLayout medicine, dentist, surgery, ophthalmology;
    RelativeLayout realtime_consulting, comprehensive_care;
    LinearLayout subject_entire, free_consulting_entire;
    ScrollView free_consulting_sroll;

    ImageView free_consulting_reset;
    ListView free_consulting_list;
    FreeConsultingAdapter adapter;

    int[] info_ino    = { R.id.ino1, R.id.ino2, R.id.ino3, R.id.ino4 };
    int[] info_id     = { R.id.info_id1, R.id.info_id2, R.id.info_id3, R.id.info_id4 };
    int[] info_title  = { R.id.info_title1, R.id.info_title2, R.id.info_title3, R.id.info_title4 };
    int[] info_layout = { R.id.infoLayout1, R.id.infoLayout2, R.id.infoLayout3, R.id.infoLayout4 };
    int[] info_image  = { R.id.home_img1, R.id.home_img2, R.id.home_img3, R.id.home_img4, };
    int[] image       = { R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1, };

    String[] review_content = {
            "친절하게 설명해주셨어요~",
            "빠르고 정확하게 진단해주셨습니다.",
            "꼼꼼하게 알려주시고 신청하고 바로연락주셔서 좋았습니다.",
            "친절하고 세심하게 봐주셨어요",
            "빠른시간내에 연락주시고 친절하셨습니다.",
            "늦은시간에 너무 감사합니다",
            "전화로 친절하게 알려주셨어요",
            "신속하게 친절하게 해주셨습니다.",
            "늦은 시간에도 감사합니다!! :)",
            "새벽에도 추가적인 증상 어떠냐 물어봐 주셨어요"
    };

    String[] subject_test = { "medicine", "dentist", "surgery", "ophthalmology" };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        dbHelper = DatabaseHelper.getDatabaseHelper(getContext());
        database = dbHelper.getWritableDatabase();

//        String test = "select * from information";
//        Cursor cursor = database.rawQuery(test, null);
//        cursor.moveToNext();
//        Log.d("asdfg", cursor.getString(4));

//        String insert_sql1 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'캣타워가생겼어요', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
//
//        String insert_sql2 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'우리거북이가아파요', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
//
//        String insert_sql3 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'ㅎㅎ', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
//
//        String insert_sql4 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'강아지+고양이', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
//
//        database.execSQL(insert_sql1);
//        database.execSQL(insert_sql2);
//        database.execSQL(insert_sql3);
//        database.execSQL(insert_sql4);

        free_consulting_sroll = rootView.findViewById(R.id.free_consulting_sroll);
        free_consulting_sroll.post(new Runnable() {
            @Override
            public void run() {
                free_consulting_sroll.smoothScrollBy(0, 800);
            }
        });

        init(rootView);
        info_input_test();
        selectAllInfo();
        setListener();

        doctor_input_test();
        doctor_review_input_test();
        free_consulting_input_test();
        select_free_consulting();
        free_consulting_comment_input_test();

        return rootView;
    }

    private void init(ViewGroup rootView) {
        for(int i=0; i<info_ino_textView.length; i++) {
            info_ino_textView[i] = rootView.findViewById(info_ino[i]);
        }

        for(int i=0; i<info_id_textView.length; i++) {
            info_id_textView[i] = rootView.findViewById(info_id[i]);
        }

        for(int i=0; i<info_title_textView.length; i++) {
            info_title_textView[i] = rootView.findViewById(info_title[i]);
        }

        for(int i=0; i<infoLayout.length; i++) {
            infoLayout[i] = rootView.findViewById(info_layout[i]);
        }

        for(int i=0; i<info_image.length; i++) {
            home_img[i] = rootView.findViewById(info_image[i]);
        }

        home_user = rootView.findViewById(R.id.home_user);
        moreInfo = rootView.findViewById(R.id.moreInfo);

        medicine = rootView.findViewById(R.id.medicine);
        dentist = rootView.findViewById(R.id.dentist);
        surgery = rootView.findViewById(R.id.surgery);
        ophthalmology = rootView.findViewById(R.id.ophthalmology);

        realtime_consulting = rootView.findViewById(R.id.realtime_consulting);
        comprehensive_care = rootView.findViewById(R.id.comprehensive_care);
        free_consulting_reset = rootView.findViewById(R.id.free_consulting_reset);
        free_consulting_list = rootView.findViewById(R.id.free_consulting_list);
        free_consulting_entire = rootView.findViewById(R.id.free_consulting_entire);

        subject_entire = rootView.findViewById(R.id.subject_entire);

        adapter = new FreeConsultingAdapter(database);
        free_consulting_list.setAdapter(adapter);
    }

    public void setListener() {
        home_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SaveSharedPreference.getUserName(getContext()).length() == 0) {
                    Intent intent = new Intent(getContext(), LoginFormActivity.class);
                    getContext().startActivity(intent);
                }

            }
        });

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InfoListActivity.class);
                getContext().startActivity(intent);
            }
        });

        for(int i=0; i<infoLayout.length; i++) {
            String ino = info_ino_textView[i].getText().toString();

            infoLayout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), InfoDetailActivity.class);
                    intent.putExtra("ino", ino);
                    getContext().startActivity(intent);
                }
            });
        }

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectActivity.class);
                intent.putExtra("subject1", "내과");
                intent.putExtra("subject2", "medicine");
                getContext().startActivity(intent);
            }
        });

        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectActivity.class);
                intent.putExtra("subject1", "치과");
                intent.putExtra("subject2", "dentist");
                getContext().startActivity(intent);
            }
        });

        surgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectActivity.class);
                intent.putExtra("subject1", "외과");
                intent.putExtra("subject2", "surgery");
                getContext().startActivity(intent);
            }
        });

        ophthalmology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectActivity.class);
                intent.putExtra("subject1", "안과");
                intent.putExtra("subject2", "ophthalmology");
                getContext().startActivity(intent);
            }
        });

        subject_entire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        realtime_consulting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SaveSharedPreference.getUserName(getContext()).length() == 0) {
                    Intent intent = new Intent(getContext(), LoginFormActivity.class);
                    startActivity(intent);
                    return;
                }

                Intent intent = new Intent(getContext(), RealtimeConsultingActivity.class);
                getContext().startActivity(intent);
            }
        });

        comprehensive_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ComprehensiveActivity.class);
                startActivity(intent);
            }
        });

        free_consulting_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.rotate));
                select_free_consulting();
                free_consulting_list.smoothScrollToPosition(0);
            }
        });

        free_consulting_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                free_consulting_sroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        free_consulting_entire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FreeConsultingListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void info_input_test() {
        String delete_info = "delete from information";
        database.execSQL(delete_info);


        String info_input1 = "insert into information"
                + "(p_id, title, content, category, img) "
                + " values "
                + " ('" + "냥집사', " + "'사료 추천드려요', " + "'건식사료 적극추천', '" + "cat" + "', '" +"aaa" + "')";
        String info_input2 = "insert into information"
                + "(p_id, title, content, category, img) "
                + " values "
                + " ('" + "초코', " + "'강아지 안경 ㅎㅎ', " + "'요즘 강아지 스타일~', '" + "dog" + "', '" +"aaa" + "')";
        String info_input3 = "insert into information"
                + "(p_id, title, content, category, img) "
                + " values "
                + " ('" + "정글리안', " + "'잠들려는 햄스터', " + "'톱밥을 바꾸더니 편안해짐', '" + "etc" + "', '" +"aaa" + "')";
        String info_input4 = "insert into information"
                + "(p_id, title, content, category, img) "
                + " values "
                + " ('" + "냥집사', " + "'캣타워가 생겼어요~', " + "'한살인생 처음으로 캣타워가 생겼어요!', '" + "cat" + "', '" +"aaa" + "')";
        database.execSQL(info_input1);
        database.execSQL(info_input2);
        database.execSQL(info_input3);
        database.execSQL(info_input4);
    }

    public void selectAllInfo() {
        String selectAllInfo = "select * from information LIMIT 4";

        Cursor cursor = database.rawQuery(selectAllInfo, null);
        Log.d("sssss", cursor.getCount() + "");
        int i=0;

        while(cursor.moveToNext()) {
            info_ino_textView[i].setText(cursor.getInt(0) + "");
            info_id_textView[i].setText(cursor.getString(1));
            info_title_textView[i].setText(cursor.getString(2));
            Log.d("aaa", cursor.getInt(0) + "");
            i++;
        }
    }

    public void select_free_consulting() {
        adapter.clear();

        String selectFreeConsulting = "select * from free_consulting order by reg_date desc";
        Cursor cursor = database.rawQuery(selectFreeConsulting, null);

        while(cursor.moveToNext()) {
            int fno = cursor.getInt(0);
            String p_id = cursor.getString(1);
            String title = cursor.getString(2);
            String question = cursor.getString(3);
            String category = cursor.getString(4);
            String image = cursor.getString(5);
            String reg_date = cursor.getString(6);
            int idx = reg_date.indexOf(' ');
            reg_date = reg_date.substring(0, idx);

            FreeConsultingData freeConsultingData = new FreeConsultingData(fno, p_id, title, question, category, image, reg_date);
            adapter.addItem(freeConsultingData);
        }

        adapter.notifyDataSetChanged();
    }

    void doctor_input_test() {
        String doctor_delete = "delete from doctor";

        database.execSQL(doctor_delete);

        for(int i=0; i<20; i++) {
            String star_rating = String.valueOf(((float)(int)(Math.random() * 10) + 40) / 10);
            int idx = (int)(Math.random() * subject_test.length);

            String doctor_insert = "insert into " + "doctor"
                    + "(name, star_rating, subject, hospital, customer_num) "
                    + " values "
                    + " ('" + "이상문', '" + star_rating +"', " + "'"+ subject_test[idx] + "', " + "'원주시내병원', " + 100 +")";

            database.execSQL(doctor_insert);
        }

//        String insert_sql4 = "insert into " + "information"
//                + "(p_id, title, content) "
//                + " values "
//                + " ('" + "aaaa', " + "'강아지+고양이', " + "'ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ')";
    }

    void doctor_review_input_test() {
        String doctor_review_delete = "delete from doctor_review";

        database.execSQL(doctor_review_delete);

        for(int i=0; i<100; i++) {
            String star_rating = String.valueOf(((float)(int)(Math.random() * 10) + 40) / 10);
            int dno = (int)(Math.random()*20) + 1;
            int idx = (int)(Math.random()*review_content.length);

            String doctor_review_insert = "insert into " + "doctor_review"
                    + "(dno, star_rating, content) "
                    + " values "
                    + " (" + dno + ", '" + star_rating +"', '" + review_content[idx] + "')";
            database.execSQL(doctor_review_insert);
        }
    }

    void free_consulting_input_test() {
        String free_consulting_delete = "delete from free_consulting";
        database.execSQL(free_consulting_delete);

        String free_consulting_insert2 = "insert into " + "free_consulting"
                + "(p_id, title, question, category, image) "
                + " values "
                + " ('" + "ㅇㅇ" + "', '" + "자주토해요" +"', '" + "고양이가 헤어블을 자주 토해요. 어떡할까요?" + "', '" + "medicine" + "', '" + "asdasd" + "')";
        String free_consulting_insert3 = "insert into " + "free_consulting"
                + "(p_id, title, question, category, image) "
                + " values "
                + " ('" + "미니니" + "', '" + "강아지 건강검진" +"', '" + "강아지 건강검진 비용이 얼마나 들까요?" + "', '" + "etc" + "', '" + "asdasd" + "')";
        String free_consulting_insert4 = "insert into " + "free_consulting"
                + "(p_id, title, question, category, image) "
                + " values "
                + " ('" + "호두맘" + "', '" + "슬개골 탈골" +"', '" + "강아지 슬개골 탈구 인 것 같아요. ㅠㅠ" + "', '" + "surgery" + "', '" + "asdasd" + "')";

        database.execSQL(free_consulting_insert2);
        database.execSQL(free_consulting_insert3);
        database.execSQL(free_consulting_insert4);

        for(int i=0; i<10; i++) {
            int idx = (int)(Math.random() * subject_test.length);

            String free_consulting_insert = "insert into " + "free_consulting"
                    + "(p_id, title, question, category, image) "
                    + " values "
                    + " ('" + "aaa" + "', '" + "궁금해요" +"', '" + "~가 궁금해서 물어봅니다." + "', '" + subject_test[idx] + "', '" + "asdasd" + "')";

            database.execSQL(free_consulting_insert);
        }


    }

    void free_consulting_comment_input_test() {
        String free_consulting_comment_delete = "delete from free_consulting_comment";
        database.execSQL(free_consulting_comment_delete);

        String free_consulting_comment_insert = "insert into " + "free_consulting_comment"
                + "(fno, comment, commenter) "
                + " values "
                + " (" + 1 + ", '" + "그러면 안되요~" +"', '" + "이상문" + "')";

        database.execSQL(free_consulting_comment_insert);
    }

    @Override
    public void onResume() {
        super.onResume();

        selectAllInfo();
    }
}
