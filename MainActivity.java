package com.cookandroid.smart_umbrella;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.Socket;
import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);//리사이클러뷰 성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();//유저 객체를 담음

        database = FirebaseDatabase.getInstance();//파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("User");//DB테이블 연결 작업
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어 베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear();//기존 배열 초기화, 방지 차원
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //반복문으로 데이터 리스트를 추출
                    User user = snapshot.getValue(User.class);//만들어 뒀던 유저 객체에 데이터를 담는다
                    arrayList.add(user);//담은 데이터들을 배열 리스트에 넣고 리사클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //DB를 가져오던중 에러 발생시 실행
                // Log.e("MainActivity",databaseError.toException());

            }
        });

        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);//리사이클러뷰에 어댑터 연결
    }
}