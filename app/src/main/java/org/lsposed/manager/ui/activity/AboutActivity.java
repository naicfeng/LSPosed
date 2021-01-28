package org.lsposed.manager.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;

import com.bumptech.glide.Glide;

import org.lsposed.manager.BuildConfig;
import org.lsposed.manager.R;
import org.lsposed.manager.databinding.ActivityAboutBinding;
import org.lsposed.manager.util.GlideHelper;
import org.lsposed.manager.util.NavUtil;

public class AboutActivity extends BaseActivity {
    ActivityAboutBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        binding.appVersion.setText(BuildConfig.VERSION_NAME);

        setupView(binding.installerSupportView, R.string.group_telegram_channel_link);
        setupView(binding.sourceCodeView, R.string.about_source);
        setupView(binding.tgChannelView, R.string.group_telegram_channel_link);

        Glide.with(binding.appIcon)
                .load(GlideHelper.wrapApplicationInfoForIconLoader(getApplicationInfo()))
                .into(binding.appIcon);
    }

    void setupView(View v, final int url) {
        v.setOnClickListener(v1 -> NavUtil.startURL(this, getString(url)));
    }
}
