package com.example.task3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private final ImageView avatarIV;

        private final TextView nameTV;
        private final TextView phoneTV;
        private final TextView emailTV;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarIV = itemView.findViewById(R.id.avatarTV);
            nameTV = itemView.findViewById(R.id.nameTV);
            phoneTV = itemView.findViewById(R.id.phoneTV);
            emailTV = itemView.findViewById(R.id.emailTV);

            itemView.setOnClickListener(v -> {
                if (onItemClick != null) {
                    int index = getAdapterPosition();
                    onItemClick.itemClick(dataFormContainerList.get(index), index);
                }
            });
        }

        public void setData(DataFormContainer data) {
            Glide.with(avatarIV.getContext())
                    .load(data.getAvatarHref())
                    .centerCrop ()
                    .placeholder ( R.drawable.ic_launcher_foreground)
                    .into(avatarIV);
            nameTV.setText(data.getName());
            phoneTV.setText(data.getPhone());
            emailTV.setText(data.getEmail());
        }
    }

    public interface OnItemClick {
        void itemClick(DataFormContainer dataFormContainer, int index);
    }

    OnItemClick onItemClick;

    public void setDataFormContainerList(LinkedList<DataFormContainer> dataFormContainerList) {
        this.dataFormContainerList = dataFormContainerList;
        notifyDataSetChanged();
    }

    LinkedList<DataFormContainer> dataFormContainerList;

    public LinkedList<DataFormContainer> getDataFormContainerList() {
        return dataFormContainerList;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        DataFormContainer curData = dataFormContainerList.get(position);
        if (curData != null) {
            holder.setData(curData);
        }
    }

    @Override
    public int getItemCount() {
        return dataFormContainerList == null ? 0 : dataFormContainerList.size();
    }

    public boolean deleteItem(int index) {
        if (dataFormContainerList.size() <= index || index < 0) {
            return false;
        } else {
            dataFormContainerList.remove(index);
            notifyItemRemoved(index);
            notifyItemRangeChanged(index, dataFormContainerList.size());
            return true;
        }
    }
}
