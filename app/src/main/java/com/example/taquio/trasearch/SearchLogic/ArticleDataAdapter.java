package com.example.taquio.trasearch.SearchLogic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import com.example.taquio.trasearch.R;

/**
 * Created by User on 23/03/2018.
 */

public class ArticleDataAdapter extends RecyclerView.Adapter<ArticleDataAdapter.MyViewHolder> {
    private List<ArticleData> articleDataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, articleBody;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.articleListName);
            description = (TextView) view.findViewById(R.id.articleListDesc);
            articleBody = (TextView) view.findViewById(R.id.articleListBody);
        }
    }

    public ArticleDataAdapter(List<ArticleData> articleDataList) {
        this.articleDataList = articleDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ArticleData article = articleDataList.get(position);
        holder.name.setText(article.getName());
        holder.description.setText(article.getDescription());
        holder.articleBody.setText(article.getArticleBody());
    }

    @Override
    public int getItemCount() {
        return articleDataList.size();
    }
}
