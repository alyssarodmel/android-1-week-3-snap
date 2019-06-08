package com.ucsdextandroid1.snapapp.stories;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.snapapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjaylward on 2019-04-20
 */
public class StoriesAdapter extends RecyclerView.Adapter {

    private List<StoriesListItem> items = new ArrayList< >();
    private StoryCardViewHolder.StoryCardClickListener listener = null;

    public void setItems(Context context, List<Story> stories) {
        items.clear();

        //TODO add title item, using context.getString(R.string.stories)) to get the title
        items.add( new StoriesListItem(context.getString((R.string.stories))));

        //TODO add all of the story items to the list
        for (Story storyInList: stories) {
            items.add(new StoriesListItem(storyInList));
        }

        //TODO let the adapter know that  the data has changed
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO return the correct view holder for each viewType
        switch(viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            if ( viewType == StoriesListItem.TYPE_STORY ) {
                viewHolder = StoryCardViewHolder.inflate(parent);
                ((StoryCardViewHolder) viewHolder).setListener(listener);
            } else {
                viewHolder = StoriesSectionTitleViewHolder.inflate(parent);
            }
            return viewHolder;
        }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TODO bind the title or the story to the correct view holder
            if(holder instanceof StoryCardViewHolder) {
                ((StoryCardViewHolder) holder).bind( items.get(position).getStory());
            }
            if(holder instanceof StoriesSectionTitleViewHolder){
                ((StoriesSectionTitleViewHolder) holder).bind( items.get(position).getTitle() );
            }
        }

    @Override
    public int getItemCount() {
        // TODO return the correct item count
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        //TODO return the correct view type
            return  items.get(position).getType();
    }

    //TODO add a method that returns the correct span for each item type.
        public int getSpanSize(int position) {
            switch(getItemViewType(position)) {
                case StoriesListItem.TYPE_TITLE:
                    return 2;
                case StoriesListItem.TYPE_STORY:
                    return 1;
            }
            return 0;
        }

    //TODO add a custom interface called Callback that extends the click listener defined on the StoriesCardViewHolder

        public void setCallback(StoryCardViewHolder.StoryCardClickListener listener) {
            this.listener = listener;
        }


        private class StoriesListItem {

            public static final int TYPE_TITLE = 1;
            public static final int TYPE_STORY = 2;

            @Nullable private String title = null;
            @Nullable private Story story = null;
            final private int type;

            public StoriesListItem(@Nullable String title) {
                this.title = title;

                this.type = TYPE_TITLE;
            }

            public StoriesListItem(@NonNull Story story) {
                this.story = story;
                this.type = TYPE_STORY;
            }

            @Nullable
            public String getTitle() {
                return title;
            }

            @Nullable
            public Story getStory() {
                return story;
            }

            public int getType() {
                return type;
            }

        }

    }
