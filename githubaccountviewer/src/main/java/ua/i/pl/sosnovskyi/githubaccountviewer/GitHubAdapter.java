package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


class GitHubAdapter extends ArrayAdapter<GitHubResponce> {

    GitHubAdapter(@NonNull Context context, @NonNull List<GitHubResponce> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull final ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GitHubResponce current = getItem(position);
        viewHolder.repoId.setText(String.valueOf(current.getId()));
        viewHolder.repoName.setText(current.getName());
        viewHolder.htmlUrl.setText(current.getHtmlUrl());
        viewHolder.deteTime.setText(String.valueOf(current.getCreatedAt()));
        return convertView;
    }

    private static class ViewHolder {
        private final TextView repoId;
        private final TextView repoName;
        private final TextView htmlUrl;
        private final TextView deteTime;

        private ViewHolder(View view) {
            this.repoId = (TextView) view.findViewById(R.id.item_id);
            this.repoName = (TextView) view.findViewById(R.id.text_item);
            this.htmlUrl = (TextView) view.findViewById(R.id.html_url);
            this.deteTime = (TextView) view.findViewById(R.id.date_time);
        }
    }
}
