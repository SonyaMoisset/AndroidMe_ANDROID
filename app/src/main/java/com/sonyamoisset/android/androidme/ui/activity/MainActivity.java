package com.sonyamoisset.android.androidme.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.sonyamoisset.android.androidme.R;
import com.sonyamoisset.android.androidme.data.AndroidImageAssets;
import com.sonyamoisset.android.androidme.ui.fragment.BodyPartFragment;
import com.sonyamoisset.android.androidme.ui.fragment.MasterListFragment;

public class MainActivity extends AppCompatActivity
        implements MasterListFragment.OnImageClickListener {
    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    private boolean isTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.linear_layout_android_me) != null) {
            isTwoPane = true;

            GridView gridView = findViewById(R.id.grid_view_images);
            gridView.setNumColumns(2);

            Button nextButton = findViewById(R.id.button_next);
            nextButton.setVisibility(View.GONE);

            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                BodyPartFragment headFragment = new BodyPartFragment();
                BodyPartFragment bodyFragment = new BodyPartFragment();
                BodyPartFragment legFragment = new BodyPartFragment();

                headFragment.setImageIds(AndroidImageAssets.getHeads());
                fragmentManager.beginTransaction()
                        .add(R.id.frame_layout_head, headFragment)
                        .commit();

                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction()
                        .add(R.id.frame_layout_body, bodyFragment)
                        .commit();

                legFragment.setImageIds(AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction()
                        .add(R.id.frame_layout_leg, legFragment)
                        .commit();
            }
        } else {
            isTwoPane = false;
        }
    }

    public void onImageSelected(int position) {
        int bodyPartNumber = position / 12;
        int listIndex = position - 12 * bodyPartNumber;

        if (isTwoPane) {
            BodyPartFragment bodyPartFragment = new BodyPartFragment();

            switch (bodyPartNumber) {
                case 0:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout_head, bodyPartFragment)
                            .commit();
                    break;

                case 1:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());
                    bodyPartFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout_body, bodyPartFragment)
                            .commit();
                    break;

                case 2:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getLegs());
                    bodyPartFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout_leg, bodyPartFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;

                case 1:
                    bodyIndex = listIndex;
                    break;

                case 2:
                    legIndex = listIndex;
                    break;

                default:
                    break;
            }

            Bundle bundle = new Bundle();
            bundle.putInt(getString(R.string.head_index), headIndex);
            bundle.putInt(getString(R.string.body_index), bodyIndex);
            bundle.putInt(getString(R.string.leg_index), legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(bundle);

            Button nextButton = findViewById(R.id.button_next);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
}
