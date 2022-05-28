package com.example.eduapp.ui.fragments.search;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.eduapp.R;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentProfileBinding;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ProfileFragment extends BaseDialogFragment<BaseViewModel, FragmentProfileBinding> {

  @Override
  public int idLayout() {
    return R.layout.fragment_profile;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);

    Glide.with(this).load(R.drawable.course_coding)
        .centerCrop()
        .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
        .into(binding.backgroundImage);
    }
}
