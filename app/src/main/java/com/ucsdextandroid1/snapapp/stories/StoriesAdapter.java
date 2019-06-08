package com.ucsdextandroid1.snapapp.stories;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.snapapp.R;
import com.ucsdextandroid1.snapapp.chat.ChatItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjaylward on 2019-04-20
 */
public class StoriesAdapter extends RecyclerView.Adapter {

    private List<StoriesListItem> items = new ArrayList<>();

    public void setItems(Context context, List<Story> stories) {
        items.clear();

        //TODO add title item, using context.getString(R.string.stories)) to get the title
        items.add(new StoriesListItem(context.getString(R.string.stories)));
        for (Story story : stories) {
            items.add(new StoriesListItem(story));
        }

        //TODO add all of the story items to the list

        //TODO let the adapter know that  the data has changed
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO return the correct view holder for each viewType
        switch(viewType) {
            case StoriesListItem.TYPE_Story:
                StoryCardViewHolder viewHolder = StoryCardViewHolder.inflate(parent);
                viewHolder.setStoryItemClickCallback(listener);
                return viewHolder;

            case StoriesListItem.TYPE_TITLE:
                return StoriesSectionTitleViewHolder.inflate(parent);
            default:
                return StoryCardViewHolder.inflate(parent);
        }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TODO bind the title or the story to the correct view holder
            if(holder instanceof StoryCardViewHolder)
                ((StoryCardViewHolder) holder).bind(items.get(position).getStory());
            else if (holder instanceof StoriesSectionTitleViewHolder)
                ((StoriesSectionTitleViewHolder) holder).bind(items.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        // TODO return the correct item count
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        //TODO return the correct view type
     int type = items.get(position).getType();
        return type;
    }

    //TODO add a method that returns the correct span for each item type.
        public int getSpanAtIndex(int position){
            switch(getItemViewType(position)){
                case StoriesListItem.TYPE_Story:
                    return 1;
                case StoriesListItem.TYPE_TITLE:
                    return 2;
                default :
                    return 0;

            }
        }

    //TODO add a custom interface called Callback that extends the click listener defined on the StoriesCardViewHolder

    private class StoriesListItem {

        public static final int TYPE_TITLE = 1;
        public static final int TYPE_CARD = 2;
        private String title;
        private Story story;
        private int type;

        public StoriesListItem(String title) {
            this.title = title;
            this.story = null;
            this.type = TYPE_TITLE;
        }

        public StoriesListItem(Story story) {
            this.title = null;
            this.story = story;
            this.type = TYPE_Story;
        }

        public int getType() {
            return type;
        }

        public Story getStory() {
            return story;
        }

        public String getTitle() {
            return title;
        }

    }
        public void setOnItemClickCallback(StoryCardViewHolder.StoryCardClickListener listener) {
            this.listener = listener;

    }

}
