/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * We couldn't come up with a good name for this class. Then, we realized
 * that this lesson is about RecyclerView.
 *
 * RecyclerView... Recycling... Saving the planet? Being green? Anyone?
 * #crickets
 *
 * Avoid unnecessary garbage collection by using RecyclerView and ViewHolders.
 *
 * If you don't like our puns, we named this Adapter GreenAdapter because its
 * contents are green.
 */
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = GreenAdapter.class.getSimpleName();
    private final ListItemClickListener mOnClickListener;
    // TODO(3) Create a final private ListItemClickListener called mOnClickListener

    /*
     * The number of ViewHolders that have been created. Typically, you can figure out how many
     * there should be by determining how many list items fit on your screen at once and add 2 to 4
     * to that number. That isn't the exact formula, but will give you an idea of how many
     * ViewHolders have been created to display any given RecyclerView.
     *
     * Here's some ASCII art to hopefully help you understand:
     *
     *    ViewHolders on screen:
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 0 |
     *        *-----------------------------*
     *        |         ViewHolder index: 1 |
     *        *-----------------------------*
     *        |         ViewHolder index: 2 |
     *        *-----------------------------*
     *        |         ViewHolder index: 3 |
     *        *-----------------------------*
     *        |         ViewHolder index: 4 |
     *        *-----------------------------*
     *        |         ViewHolder index: 5 |
     *        *-----------------------------*
     *        |         ViewHolder index: 6 |
     *        *-----------------------------*
     *        |         ViewHolder index: 7 |
     *        *-----------------------------*
     *
     *    Extra ViewHolders (off screen)
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 8 |
     *        *-----------------------------*
     *        |         ViewHolder index: 9 |
     *        *-----------------------------*
     *        |         ViewHolder index: 10|
     *        *-----------------------------*
     *        |         ViewHolder index: 11|
     *        *-----------------------------*
     *
     *    Total number of ViewHolders = 11
     */
    private static int viewHolderCount;

    private int mNumberItems;

    // TODO (1) Add an interface called ListItemClickListener
    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }
    //  TODO(2) Within that interface, define a void method called onListItemClick that takes an int as a parameter

    // TODO(4) Add a ListItemClickListener as a parameter to the constructor and store it in mOnClickListener
    /**
     * Constructor for GreenAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberOfItems Number of items to display in list
     */
    public GreenAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }


    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);

        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    //  TODO(5) Implement OnClickListener in the NumberViewHolder class

    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;
        // Will display which ViewHolder is displaying this data
        TextView viewHolderIndex;


        public NumberViewHolder(View itemView) {
            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            itemView.setOnClickListener(this);
            // TODO (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
        }


        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }
        // TODO(6) Override onClick, passing the clicked item's position (getAdapterPosition()) to mOnClickListener via its onListItemClick method
        @Override
        public void onClick(View view){
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }


    }
}