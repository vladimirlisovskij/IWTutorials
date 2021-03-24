package com.example.task3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;

public class Dialog extends DialogFragment {

    private ImageView avatar;
    private Button exitBut, deleteBut, showBut;
    private View.OnClickListener showListener, deleteListener;
    private String imgHref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, null);
        avatar = v.findViewById(R.id.dialogAvatar);
        deleteBut = v.findViewById(R.id.dialogDeleteBut);
        exitBut = v.findViewById(R.id.dialogExitBut);
        showBut = v.findViewById(R.id.dialogProfileBut);

        exitBut.setOnClickListener(view -> {
            dismiss();
        });
        deleteBut.setOnClickListener(deleteListener);
        showBut.setOnClickListener(showListener);
        Glide.with(avatar.getContext())
                .load(imgHref)
                .centerCrop ()
                .placeholder ( R.drawable.ic_launcher_foreground)
                .into(avatar);
        return v;
    }

    public void setShowListener(View.OnClickListener showListener) {
        this.showListener = showListener;
    }

    public void setDeleteListener(View.OnClickListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void setImgHref(String imgHref) {
        this.imgHref = imgHref;
    }
}
