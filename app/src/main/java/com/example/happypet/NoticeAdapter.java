package com.example.happypet;

// NoticeAdapter.java

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private List<Notice> noticeList;
    private Context context;

    public NoticeAdapter(List<Notice> noticeList, Context context) {
        this.noticeList = noticeList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        Notice notice = noticeList.get(position);
        holder.textTitle.setText(notice.getTitle());
        holder.textContent.setText(notice.getContent());
        holder.textDate.setText(notice.getDate());

        // 아이템 클릭 이벤트 리스너 설정
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭한 공지사항의 내용을 다음 액티비티로 전달
                Intent intent = new Intent(context, NoticeDetailActivity.class);
                intent.putExtra("title", notice.getTitle());
                intent.putExtra("content", notice.getContent());
                intent.putExtra("date", notice.getDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        public TextView textTitle, textContent, textDate;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textContent = itemView.findViewById(R.id.textContent);
            textDate = itemView.findViewById(R.id.textDate);
        }
    }
}
