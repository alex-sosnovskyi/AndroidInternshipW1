package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class SearchAdapter extends ArrayAdapter<GitHubUserResponce>{
    public SearchAdapter(@NonNull Context context, List<GitHubUserResponce> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final SearchAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.search_item, null);
            viewHolder = new SearchAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SearchAdapter.ViewHolder) convertView.getTag();
        }
        GitHubUserResponce current = getItem(position);

        viewHolder.login.setText(current.getLogin());

        return convertView;
    }

    private static class ViewHolder {

        private final TextView login;

        private ViewHolder(View view) {
            this.login = (TextView) view.findViewById(R.id.search_result_string);

        }
    }
}
