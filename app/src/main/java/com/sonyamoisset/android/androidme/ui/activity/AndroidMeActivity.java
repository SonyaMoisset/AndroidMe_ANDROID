package com.sonyamoisset.android.androidme.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sonyamoisset.android.androidme.R;
import com.sonyamoisset.android.androidme.data.AndroidImageAssets;
import com.sonyamoisset.android.androidme.ui.fragment.BodyPartFragment;

public class AndroidMeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null) {
            int headIndex = getIntent()
                    .getIntExtra(getString(R.string.head_index), 0);
            int bodyIndex = getIntent()
                    .getIntExtra(getString(R.string.body_index), 0);
            int legIndex = getIntent()
                    .getIntExtra(getString(R.string.leg_index), 0);

            BodyPartFragment headFragment = new BodyPartFragment();
            BodyPartFragment bodyFragment = new BodyPartFragment();
            BodyPartFragment legFragment = new BodyPartFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();

            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setListIndex(headIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.frame_layout_head, headFragment)
                    .commit();

            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setListIndex(bodyIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.frame_layout_body, bodyFragment)
                    .commit();

            legFragment.setImageIds(AndroidImageAssets.getLegs());
            bodyFragment.setListIndex(legIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.frame_layout_leg, legFragment)
                    .commit();
        }
    }
}
