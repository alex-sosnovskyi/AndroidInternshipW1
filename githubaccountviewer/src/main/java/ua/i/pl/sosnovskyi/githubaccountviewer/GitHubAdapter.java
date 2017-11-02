package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


class GitHubAdapter extends ArrayAdapter<GitHubUserResponce> {

    GitHubAdapter(@NonNull Context context, @NonNull List<GitHubUserResponce> objects) {
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
        GitHubUserResponce current = getItem(position);
        viewHolder.repoId.setText(String.valueOf(current.getId()));
        viewHolder.repoName.setText(current.getName());
        viewHolder.login.setText(current.getLogin());
        viewHolder.deteTime.setText(String.valueOf(current.getCreatedAt()));
        return convertView;
    }

    private static class ViewHolder {
        private final TextView repoId;
        private final TextView repoName;
        private final TextView login;
        private final TextView deteTime;

        private ViewHolder(View view) {
            this.repoId = (TextView) view.findViewById(R.id.item_id);
            this.repoName = (TextView) view.findViewById(R.id.text_item);
            this.login = (TextView) view.findViewById(R.id.login);
            this.deteTime = (TextView) view.findViewById(R.id.date_time);
        }
    }
}
