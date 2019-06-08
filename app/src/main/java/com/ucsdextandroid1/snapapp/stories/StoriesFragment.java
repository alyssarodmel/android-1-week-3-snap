package com.ucsdextandroid1.snapapp.stories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.snapapp.DataSources;
import com.ucsdextandroid1.snapapp.R;
import com.ucsdextandroid1.snapapp.chat.Chat;
import com.ucsdextandroid1.snapapp.chat.ChatItemViewHolder;
import com.ucsdextandroid1.snapapp.util.WindowUtil;

import java.util.List;

import javax.sql.DataSource;

/**
 * Created by rjaylward on 4/15/19
 */
public class StoriesFragment extends Fragment {

    public static StoriesFragment create() {
        return new StoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_story, container, false);

        View background = root.findViewById(R.id.fs_background);
        RecyclerView recyclerView = root.findViewById(R.id.fs_recycler_view);
        recyclerView.setClipToPadding(false);

        // this just adds padding to top of the views so they are not drawn under the status bar
        WindowUtil.doOnApplyWindowInsetsToMargins(background, true, false);
        WindowUtil.doOnApplyWindowInsetsToPadding(recyclerView, true, true);

        //TODO create a adapter
        StoriesAdapter adapter = new StoriesAdapter();

        //TODO create a grid layout manager with default span of 2 and the SpanSizeLookup for each type
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getSpanAtIndex(position);
            }
        });

        //TODO set up the recyclerView with the layoutManager and adapter
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //TODO add a callback to the adapter that calls the method onStoryClicked when the user clicks on the list item
        adapter.setOnItemClickCallback(new StoryCardViewHolder.StoryCardClickListener() {
            @Override
            public void onStoryCardItemClicked(Story story) {
                onStoryClicked(story);
            }
        });

        DataSources.getInstance().getStoryCards(new DataSources.Callback<List<Story>>() {
            @Override
            public void onDataFetched(List<Story> data) {
                //TODO set the data from the DataSource to the adapter
                adapter.setItems(getContext(), data);
            }
        });

        return root;
    }

    private void onStoryClicked(Story story) {
        Toast.makeText(getContext(), story.getStoryLink(), Toast.LENGTH_SHORT).show();

    }

}
